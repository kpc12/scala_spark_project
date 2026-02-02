package com.company.etl
import com.company.etl.pipeline.ETLPipeline
import com.company.etl.utils.DataGenerator 
object Main extends App { 
val inputFile = "employees.csv" val outputFile = "employee_report.txt"
// Generate sample data
println("Generating sample employee data...") 
val sampleEmployees = DataGenerator.generateSampleData(50)
DataGenerator.writeToCsv(sampleEmployees, inputFile)
println(s"Sample data written to: $inputFile\n") // Run ETL pipeline
val pipeline = new ETLPipeline()
pipeline.execute(inputFile, outputFile)
println("\n" + "=" * 80)
println("Project demonstrates:")
println("Packaging (com.company.etl package structure)")
println("Traits (DataReader, DataWriter, DataTransformer)")
println("Case Classes (Employee, EmployeeStats)")
println("Collections (Seq, Vector)")
println("Higher-order functions (map, filter, foldLeft)")
println("Functional programming (immutable data, pure functions)")
println("Modular design (separate readers, transformers, writers)")
println("ETL Pipeline (Extract, Transform, Load)") println("✓ Testing-ready architecture") println("=" * 80) 
}
