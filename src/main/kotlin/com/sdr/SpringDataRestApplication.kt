package com.sdr

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringDataRestApplication

fun main(args: Array<String>) {
  runApplication<SpringDataRestApplication>(*args)
}
