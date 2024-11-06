package kr.co._29cm.boilerplate

import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices

@AnalyzeClasses(packagesOf = [BoilerplateArchitectureTest::class])
class BoilerplateArchitectureTest {
    @ArchTest
    val `순환참조는 허용하지 않는다` = slices().matching("kr.co._29cm.(*)..").should().beFreeOfCycles()
}