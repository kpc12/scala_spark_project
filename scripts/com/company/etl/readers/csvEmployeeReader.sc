package com.company.etl.readers 

import com.company.etl.models.Employee 
import com.company.etl.traits.DataReader 
class CsvEmployeeReader extends DataReader[Employee] { 
override def read(source: String): Seq[Employee] = { 
val lines = scala.io.Source.fromFile(source).getLines().toSeq 
val dataLines = lines.tail // Skip header 
dataLines.flatMap { line => parseLine(line)
} } 
private def parseLine(line: String): Option[Employee] = { 
try { 
val parts = line.split(",").map(_.trim) 
Some(Employee( id = parts(0).toInt, 
name = parts(1), 
department = parts(2), 
salary = parts(3).toDouble, 
yearsOfExperience = parts(4).toInt, 
email = parts(5) )) 
} catch {
case _: Exception => None 
} } 
override def validate(data: Seq[Employee]): Boolean = { 
data.nonEmpty && 
data.forall(e => e.salary > 0 && e.yearsOfExperience >= 0) 
} 
}
