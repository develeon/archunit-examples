import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.junit.{AnalyzeClasses, ArchTest}
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.library.Architectures.layeredArchitecture
import layered.controller.SuperHeroController
import layered.repository.SuperHeroRepository

@AnalyzeClasses(
  packages = Array("layered"),
  importOptions = Array(classOf[ImportOption.DoNotIncludeArchives], classOf[ImportOption.DoNotIncludeTests])
)
object LayeredArchitectureTests {
  private val NAMESPACE = "layered."

  @ArchTest val layersShouldBeRespected: ArchRule =
    layeredArchitecture()
      .consideringAllDependencies()
      .layer("Controller").definedBy(NAMESPACE + "controller..")
      .layer("Service").definedBy(NAMESPACE + "service..")
      .layer("Model").definedBy(NAMESPACE + "model..")
      .layer("Repository").definedBy(NAMESPACE + "repository..")
      .whereLayer("Controller").mayNotBeAccessedByAnyLayer
      .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
      .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service")
      .ignoreDependency(classOf[SuperHeroController], classOf[SuperHeroRepository]);
}
