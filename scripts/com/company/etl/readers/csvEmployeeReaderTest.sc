package com.company.etl.readers
import com.company.etl.models.Employee
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import java.io.PrintWriter
class CsvEmployeeReaderTest extends AnyFlatSpec with Matchers { 
val reader = new CsvEmployeeReader() 
"CsvEmployeeReader" should "validate correct employee data" in { 
val validData = Seq( Employee(1, "Alice", "Engineering", 80000, 5, "alice@company.com"),
                     Employee(2, "Bob", "Sales", 60000, 3, "bob@company.com") )
                     
reader.validate(validData) shouldBe true 

} 
it should "reject data with negative salary" in { 
val invalidData = Seq( Employee(1, "Alice", "Engineering", -1000, 5, "alice@company.com")) 
reader.validate(invalidData) shouldBe false 
} 
it should "reject data with negative experience" in { 
val invalidData = Seq( Employee(1, "Alice", "Engineering", 80000, -1, "alice@company.com") ) 
reader.validate(invalidData) shouldBe false 
} 
it should "reject empty data" in { 
reader.validate(Seq.empty) shouldBe false } 
}
