package com.company.etl.transformers
import com.company.etl.models.Employee
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
class EmployeeAnalyzerTest extends AnyFlatSpec with Matchers { 
val analyzer = new EmployeeAnalyzer()
val sampleEmployees = Seq( Employee(1, "Alice", "Engineering", 80000, 5, "alice@company.com"),
                           Employee(2, "Bob", "Engineering", 90000, 7, "bob@company.com"),
                           Employee(3, "Carol", "Sales", 60000, 3, "carol@company.com"),
                           Employee(4, "David", "Sales", 70000, 5, "david@company.com") ) 
"EmployeeAnalyzer" should "transform employees to department statistics" in { 
val stats = analyzer.transform(sampleEmployees)
stats.size shouldBe 2
stats.map(_.department).toSet shouldBe Set("Engineering", "Sales") 
} 
it should "calculate correct average salary" in { 
val stats = analyzer.transform(sampleEmployees) 
val engStats = stats.find(_.department == "Engineering").get
engStats.averageSalary shouldBe 85000.0
engStats.totalEmployees shouldBe 2 } 
it should "filter employees by predicate" in { 
val highEarners = analyzer.filter(sampleEmployees, _.salary > 70000)
highEarners.size shouldBe 2
highEarners.map(_.name).toSet shouldBe Set("Alice", "Bob") 
} 
it should "find top N earners" in { 
val top2 = analyzer.findTopEarners(sampleEmployees, 2)
top2.size shouldBe 2
top2.head.name shouldBe "Bob"
top2.last.name shouldBe "Alice" 
} 
it should "filter by salary range" in { 
val midRange = analyzer.filterBySalaryRange(sampleEmployees)(65000, 85000)
midRange.size shouldBe 2
midRange.map(_.name).toSet shouldBe Set("Alice", "David") 
} 
}
