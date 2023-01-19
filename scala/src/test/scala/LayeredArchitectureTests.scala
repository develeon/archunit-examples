import LayeredArchitectureTests._
import com.tngtech.archunit.base.DescribedPredicate
import com.tngtech.archunit.base.DescribedPredicate.alwaysTrue
import com.tngtech.archunit.core.domain.{ JavaClass, JavaMember }
import com.tngtech.archunit.core.domain.properties.HasName.AndFullName.Predicates.fullNameMatching
import com.tngtech.archunit.core.domain.JavaClass.Functions.GET_SIMPLE_NAME
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.library.Architectures.layeredArchitecture
import layered.controller.SuperHeroController
import layered.model.SuperHero
import layered.repository.SuperHeroRepository

class LayeredArchitectureTests
    extends ArchUnitFunSpec(
      "Layered architecture",
      "..layered..",
      `layered architecture is respected`,
      `layered architecture is respected - except ignored`
    )

object LayeredArchitectureTests {
  private val controller = "Controller"
  private val service    = "Service"
  private val model      = "Model"
  private val dal        = "DAL"

  private val `layered architecture is respected`: ArchRule =
    layeredArchitecture()
      .consideringOnlyDependenciesInLayers()
      .layer(controller)
      .definedBy("..controller..")
      .layer(service)
      .definedBy("..service..")
      .layer(model)
      .definedBy("..model..")
      .layer(dal)
      .definedBy("..repository..")
      .whereLayer(controller)
      .mayNotBeAccessedByAnyLayer()
      .whereLayer(service)
      .mayOnlyBeAccessedByLayers(controller)
      .whereLayer(dal)
      .mayOnlyBeAccessedByLayers(service)
      .as("We should respect our Layer definition")

  import layered.service.SuperHeroService

  private val `layered architecture is respected - except ignored`: ArchRule =
    layeredArchitecture()
      .consideringOnlyDependenciesInLayers()
      .layer(controller)
      .definedBy("..layered.controller..")
      .layer(service)
      .definedBy("..layered.service..")
      .layer(model)
      .definedBy("..layered.model..")
      .layer(dal)
      .definedBy("..layered.repository..")
      .ensureAllClassesAreContainedInArchitecture()
      .whereLayer(controller)
      .mayNotBeAccessedByAnyLayer()
      .whereLayer(service)
      .mayOnlyBeAccessedByLayers(controller)
      .whereLayer(dal)
      .mayNotAccessAnyLayer()
      .whereLayer(dal)
      .mayOnlyBeAccessedByLayers(
        service
      )
      .ignoreDependency(
        classOf[SuperHeroController].getName,
        classOf[SuperHeroRepository].getName
      )
      .as("We should respect our Layer definition, except ignored once")

  private def startsWith(
    prefix: String,
  ): DescribedPredicate[JavaClass] =
    new DescribedPredicate[JavaClass]("starts with '%s'", prefix) {
      override def test(input: JavaClass): Boolean = {
        println(s"Checking ${input.getName}")
        input.getName.toLowerCase().startsWith(prefix.toLowerCase)
      }
    }
}
