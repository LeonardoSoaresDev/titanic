package com.leonardo.titanic

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TitanicApplication

fun main(args: Array<String>) {
	runApplication<TitanicApplication>(*args)
}
