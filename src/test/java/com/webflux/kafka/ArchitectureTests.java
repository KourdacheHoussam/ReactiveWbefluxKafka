package com.webflux.kafka;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;


/**
 * @author : Houssam KOURDACHE
 */
public class ArchitectureTests {

    private static final String ROOT_PACKAGE = "com.webflux.kafka.delivery_system..";
    private static final String REST_PACKAGE = "com.webflux.kafka.delivery_system.rest..";
    private static final String APP_PACKAGE = "com.webflux.kafka.delivery_system.application..";
    private static final String ENTITY_PACKAGE = "com.webflux.kafka.delivery_system.entity..";
    private static final String REPO_PACKAGE = "com.webflux.kafka.delivery_system.repository..";
    private static final String INFRA_PACKAGE = "com.webflux.kafka.delivery_system.infrastructure..";
    private static final String SPRING_PACKAGE = "org.springframework..";
    private static final String JAVA_PACKAGE = "java..";


    JavaClasses REST_PACKAGE_CLASSES = new ClassFileImporter().importPackages(REST_PACKAGE);
    JavaClasses APP_PACKAGE_CLASSES = new ClassFileImporter().importPackages(APP_PACKAGE);
    JavaClasses ENTITY_PACKAGE_CLASSES = new ClassFileImporter().importPackages(ENTITY_PACKAGE);
    JavaClasses REPO_PACKAGE_CLASSES = new ClassFileImporter().importPackages(REPO_PACKAGE);
    JavaClasses INFRA_PACKAGE_CLASSES = new ClassFileImporter().importPackages(INFRA_PACKAGE);


    @Test
    public void api_package_have_access_only_to_application_package() {
        ArchRule api_application_access_rule =
                classes().that().resideInAPackage(REST_PACKAGE)
                        .should().onlyAccessClassesThat()
                        .resideInAnyPackage(APP_PACKAGE,
                                SPRING_PACKAGE,
                                JAVA_PACKAGE,
                                "org.springframework.cloud..")
                        .allowEmptyShould(true);
        api_application_access_rule.check(REST_PACKAGE_CLASSES);
    }

    @Test
    public void application_package_should_not_use_infrastructure_repository_package() {
        ArchRule application_not_access_infrastructure_repository =
                classes().that().resideInAPackage(APP_PACKAGE).should().onlyAccessClassesThat()
                        .resideInAnyPackage(APP_PACKAGE, REPO_PACKAGE, ENTITY_PACKAGE, SPRING_PACKAGE, JAVA_PACKAGE)
                        .allowEmptyShould(true);
        application_not_access_infrastructure_repository.check(APP_PACKAGE_CLASSES);
    }

    @Test
    public void infra_package_should_access_only_repository_package() {
        ArchRule infra_access_only_repository_package = classes().that().resideInAnyPackage(INFRA_PACKAGE)
                .should().onlyAccessClassesThat()
                .resideInAnyPackage(REPO_PACKAGE, INFRA_PACKAGE, SPRING_PACKAGE, JAVA_PACKAGE, "reactor..");
        infra_access_only_repository_package.check(INFRA_PACKAGE_CLASSES);
    }

    @Test
    public void annotation_rest_controller_should_be_used_only_in_rest_package() {
        ArchRule rest_controller_annotation_rule = classes().that().areAnnotatedWith(RestController.class)
                .should().resideInAPackage(REST_PACKAGE);
        rest_controller_annotation_rule.check(new ClassFileImporter().importPackages(ROOT_PACKAGE));
    }

    @Test
    public void annotation_repository_should_be_used_only_in_infra_package() {
        ArchRule rest_controller_annotation_rule = classes().that().areAnnotatedWith(Repository.class)
                .should().resideInAPackage(INFRA_PACKAGE);
        rest_controller_annotation_rule.check(new ClassFileImporter().importPackages(ROOT_PACKAGE));
    }

    @Test
    public void annotation_document_table_should_reside_in_entity_package() {
        ArchRule document_table_annotation_rule = classes().that()
                .areAnnotatedWith(Document.class)
                .should().resideInAPackage(ENTITY_PACKAGE);
        document_table_annotation_rule.check(new ClassFileImporter().importPackages(ROOT_PACKAGE));
    }

    @Test
    public void entities_should_not_be_used_in_rest_package() {
        ArchRule no_entities_in_rest_layer = classes()
                .that().resideInAPackage(REST_PACKAGE)
                .should().onlyAccessClassesThat()
                .areNotAnnotatedWith(Document.class);
        no_entities_in_rest_layer.check(REST_PACKAGE_CLASSES);
    }

    @Test
    public void infra_repository_package_should_contains_classes_annotated_repository() {
        String rPackage = "com.webflux.kafka.delivery_system.infrastructure.repository.impl";
        JavaClasses classes = new ClassFileImporter().importPackages(rPackage);
        classes().that().resideInAPackage(rPackage).should().beAnnotatedWith(Repository.class).check(classes);
    }

    @Test
    public void transactional_annotation_only_available_in_application_package() {
        classes().that().areAnnotatedWith(Transactional.class).should()
                .resideInAPackage("com.webflux.kafka.delivery_system.application.service")
                .allowEmptyShould(true)
                .check(new ClassFileImporter().importPackages(ROOT_PACKAGE));
    }

    @Test
    public void swagger_documentation_mandatory_on_rest_methods() {
        methods().that()
                .areAnnotatedWith(GetMapping.class)
                .or().areAnnotatedWith(PostMapping.class)
                .or().areAnnotatedWith(DeleteMapping.class)
                .or().areAnnotatedWith(PatchMapping.class)
                .or().areAnnotatedWith(PutMapping.class)
                .should().beAnnotatedWith(Operation.class)
                .andShould().beAnnotatedWith(ApiResponses.class)
                .check(REST_PACKAGE_CLASSES);
    }

    @Test
    public void dto_object_should_be_records() {
        classes().that().resideInAPackage("com.webflux.kafka.delivery_system.application.dto")
                .should().beRecords().check(APP_PACKAGE_CLASSES);
    }
}
