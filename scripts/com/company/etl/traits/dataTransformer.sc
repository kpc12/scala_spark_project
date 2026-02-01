package com.company.etl.traits
trait DataTransformer[T, R] { 
def transform(data: Seq[T]): Seq[R] 
def filter(data: Seq[T], predicate: T => Boolean): Seq[T] 
}
