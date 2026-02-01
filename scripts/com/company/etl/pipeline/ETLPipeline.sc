package com.company.etl.pipeline 
import com.company.etl.models.{Employee, EmployeeStats} 
import com.company.etl.readers.CsvEmployeeReader 
import com.company.etl.transformers.EmployeeAnalyzer 
import com.company.etl.writers.ReportWriter

class ETLPipeline { 
private val reader = new CsvEmployeeReader() 
private val analyzer = new EmployeeAnalyzer() 
private val writer = new ReportWriter() 

def execute(inputPath: String, outputPath: String): Unit = 
{ println("Starting ETL Pipeline...") 
// Extract 
println("Step 1: Extracting data...") 
val employees = reader.read(inputPath) 
println(s"Extracted ${employees.size} employees") 
// Validate println("Step 2: Validating data...") 
if (!reader.validate(employees)) { 
throw new IllegalStateException("Data validation failed!") 
} 
println("Data validated successfully") 
// Transform println("Step 3: Transforming data...") 
val stats = analyzer.transform(employees) 
println(s"Generated statistics for ${stats.size} departments") 
// Load println("Step 4: Writing report...") 
writer.write(stats, outputPath) 
println(s"Report written to: $outputPath") 
println("ETL Pipeline completed successfully!") } 
// Functional programming demonstration 
def executeWithFilters( inputPath: String, outputPath: String, filters: Seq[Employee => Boolean] ): Unit = 
{ val employees = reader.read(inputPath) 
// Apply multiple filters functionally val filteredEmployees = filters.foldLeft(employees) 
{ (data, filterFunc) => analyzer.filter(data, filterFunc) 
} 
val stats = analyzer.transform(filteredEmployees) 
writer.write(stats, outputPath) 
} 
}
