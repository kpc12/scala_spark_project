package com.company.etl.traits 
trait DataReader[T] { 
def read(source: String): Seq[T] 
def validate(data: Seq[T]): Boolean 
}
