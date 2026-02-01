package com.company.etl.transformers

import com.company.etl.models.{Employee, EmployeeStats} 
import com.company.etl.traits.DataTransformer

class EmployeeAnalyzer extends DataTransformer[Employee, EmployeeStats] 
{ override def transform(data: Seq[Employee]): Seq[EmployeeStats] = 
  { data.groupBy(_.department)
        .map { case (dept, employees) => 
        calculateStats(dept, employees) 
        }.toSeq
         .sortBy(_.department) 
        } 
        
        private def calculateStats(department: String, employees: Seq[Employee]): 
        EmployeeStats = { 
        val salaries = employees.map(_.salary) 
        EmployeeStats( 
        department = department, 
        averageSalary = salaries.sum/salaries.size, 
        totalEmployees = employees.size, 
        maxSalary = salaries.max, 
        minSalary = salaries.min ) 
} 

override def filter(data: Seq[Employee], predicate: Employee => Boolean): 
Seq[Employee] = { data.filter(predicate) } 

// Higher-order function examples 

def filterBySalaryRange(data: Seq[Employee])(min: Double, max: Double): Seq[Employee] = { 
filter(data, e => e.salary >= min && e.salary <= max) 
} 

def mapToUpperDepartment(data: Seq[Employee]): Seq[Employee] = 
{ data.map(e => e.copy(department = e.department.toUpperCase)) 
} 
def findTopEarners(data: Seq[Employee], n: Int): Seq[Employee] = { 
data.sortBy(_.salary)(Ordering[Double].reverse).take(n) 
} }
