package com.leonardo.titanic

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class TitanicApplication

fun main(args: Array<String>) {
	runApplication<TitanicApplication>(*args)
}
