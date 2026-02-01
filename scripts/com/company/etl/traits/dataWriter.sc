package com.company.etl.traits trait DataWriter[T] { 
def write(data: Seq[T], destination: String): Unit 
def formatOutput(data: Seq[T]): String 
}
