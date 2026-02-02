package com.company.etl.utils
import com.company.etl.models.Employee
import java.io.PrintWriter
object DataGenerator { 
private val departments = Vector("Engineering", "Sales", "Marketing", "HR", "Finance")
private val names = Vector( "Alice Johnson", "Bob Smith", "Carol Williams", "David Brown", "Emma Davis", "Frank Miller", "Grace Wilson", "Henry Moore", "Ivy Taylor", "Jack Anderson" )
def generateSampleData(count: Int): Seq[Employee] = { 
(1 to count).map { id => 
  Employee( id = id,
            name = names(scala.util.Random.nextInt(names.size)),
            department = departments(scala.util.Random.nextInt(departments.size)),
            salary = 40000 + scala.util.Random.nextInt(100000),
            yearsOfExperience = scala.util.Random.nextInt(20), email = s"employee$id@company.com" )
            } 
}
def writeToCsv(employees: Seq[Employee], filePath: String): Unit = { 
val writer = new PrintWriter(filePath) 
try { 
      writer.println("id,name,department,salary,yearsOfExperience,email") 
      employees.foreach { emp => writer.println(s"${emp.id},${emp.name},${emp.department},${emp.salary},${emp.yearsOfExperience},${emp.email}") 
                         } 
    } finally { 
    writer.close() 
    } 
  } 
}
