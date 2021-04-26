package com.app.util

import java.time.temporal.ChronoUnit
import java.time.{LocalDate, LocalDateTime}

class DateTimeUtil {

  private val MIN_DATE = LocalDateTime.of(1, 1, 1, 0, 0)
  private val MAX_DATE = LocalDateTime.of(3000, 1, 1, 0, 0)

  def isBetweenHalfOpen[T <: Comparable[T]](value: T, start: T, end: T): Boolean = {
    (start == null || value.compareTo(start) >= 0) && (end == null || value.compareTo(end) < 0)
  }

  def atStartOfDayOrMin(localDate: LocalDate): LocalDateTime = {
    if (localDate != null) localDate.atStartOfDay
    else MIN_DATE
  }

  def atStartOfNextDayOrMax(localDate: LocalDate): LocalDateTime = {
    if (localDate != null) localDate.plus(1, ChronoUnit.DAYS).atStartOfDay
    else MAX_DATE
  }

}
