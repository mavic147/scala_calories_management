package com.app.util

import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.time.{LocalDate, LocalDateTime, LocalTime}

class DateTimeUtil {
  private val DATE_TIME_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

  private val MIN_DATE = LocalDateTime.of(1, 1, 1, 0, 0)
  private val MAX_DATE = LocalDateTime.of(3000, 1, 1, 0, 0)

  def isBetweenHalfOpen[T <: Ordered[T]](value: T, start: T, end: T): Boolean = {
    (start == null || value.compare(start) >= 0) && (end == null || value.compare(end) < 0)
  }

  def atStartOfDayOrMin(localDate: LocalDate): LocalDateTime = {
    if (localDate != null) localDate.atStartOfDay
    else MIN_DATE
  }

  def atStartOfNextDayOrMax(localDate: LocalDate): LocalDateTime = {
    if (localDate != null) localDate.plus(1, ChronoUnit.DAYS).atStartOfDay
    else MAX_DATE
  }

  def toString(localDateTime: LocalDateTime): String = {
    if (localDateTime == null) ""
    else localDateTime.format(DATE_TIME_FORMATTER)
  }

  def parseLocalDate(localDateStr: String): LocalDate = {
    if (localDateStr != null && localDateStr.nonEmpty) LocalDate.parse(localDateStr)
    else null
  }

  def parseLocalTime(localTimeStr: String): LocalTime = {
    if (localTimeStr != null && localTimeStr.nonEmpty) LocalTime.parse(localTimeStr)
    else null
  }

}
