package com.company.etl.writers

import com.company.etl.models.EmployeeStats 
import com.company.etl.traits.DataWriter 
import java.io.PrintWriter

class ReportWriter extends DataWriter[EmployeeStats] { 
  override def write(data: Seq[EmployeeStats], destination: String): Unit = 
  { val writer = new PrintWriter(destination) 
     try { 
       writer.write(formatOutput(data)) 
     } 
     finally { 
       writer.close() 
     } 
  } 
  override def formatOutput(data: Seq[EmployeeStats]): String = 
  { val header = "Department Statistics Report\n" + "=" * 80 + "\n\n" 
   val body = data.map(formatStat).mkString("\n") 
   val footer = "\n" + "=" * 80 + "\n" header + body + footer 
  } 
  private def formatStat(stat: EmployeeStats): String = 
  { s"""Department: ${stat.department} 
   | Total Employees: ${stat.totalEmployees} 
   | Average Salary: $$${stat.averageSalary}%.2f 
   | Max Salary: $$${stat.maxSalary}%.2f 
   | Min Salary: $$${stat.minSalary}%.2f |""".stripMargin } 
}
