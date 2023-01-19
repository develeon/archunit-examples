package rules;

import com.java.layered.controller.SuperVillainController;
import com.java.layered.repository.SuperVillainRepository;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;


@AnalyzeClasses(packages = "com.java.layered",
        importOptions = {
                ImportOption.DoNotIncludeArchives.class,
                ImportOption.DoNotIncludeTests.class
        }

)
public class RulesJavaTests {
    final static String NAMESPACE = "com.java.layered.";

    @ArchTest
    public static final ArchRule layersShouldBeRespected = layeredArchitecture()
            .layer("Controller").definedBy(NAMESPACE+"controller..")
            .layer("Service").definedBy(NAMESPACE+"service..")
            .layer("Model").definedBy(NAMESPACE+"model..")
            .layer("Repository").definedBy(NAMESPACE+"repository..")
            .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
            .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
            .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service")
            .ignoreDependency(SuperVillainController.class, SuperVillainRepository.class);

}
