package com.hst.scrapper.global.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

/**
 * @author dlgusrb0808@gmail.com
 */
fun toDate(ldt: LocalDateTime): Date = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant())

fun toDate(ld: LocalDate): Date = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant())

