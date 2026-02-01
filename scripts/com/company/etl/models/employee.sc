package com.company.etl.models
case class Employee( id: Int, name: String, department: String, salary: Double, yearsOfExperience: Int, email: String )
case class EmployeeStats( department: String, averageSalary: Double, totalEmployees: Int, maxSalary: Double, minSalary: Double )
