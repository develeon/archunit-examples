import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.lang.ArchRule
import org.specs2.matcher.Scope
import org.specs2.mutable.Specification

abstract class ArchUnitFunSpec(name: String, packages: String, rules: ArchRule*)
    extends Specification {

  lazy val classes: JavaClasses =
    new ClassFileImporter().importPackages(packages)
  //
  //  class BaseModel extends Scope {
  //  }

  rules foreach { rule =>
    rule.getDescription in new Scope {
      rule.check(classes)
    }
  }
}
