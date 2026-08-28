package com.mtkach.tasknest.util

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

private val birthFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("d.M.yyyy")

fun ageLabel(text: String, now: LocalDate = LocalDate.now()): String {
    val born = runCatching { LocalDate.parse(text.trim(), birthFormatter) }
        .getOrNull() ?: return "Невірний формат. Приклад: 5.3.2006"

    if (born.isAfter(now)) return "Дата ще в майбутньому"

    val years = Period.between(born, now).years
    return "Вам $years ${yearsWord(years)}"
}

private fun yearsWord(n: Int): String {
    val mod100 = n % 100
    val mod10 = n % 10
    return when {
        mod100 in 11..14 -> "років"
        mod10 == 1 -> "рік"
        mod10 in 2..4 -> "роки"
        else -> "років"
    }
}

private val monthNames = listOf(
    "січень", "лютий", "березень", "квітень", "травень", "червень",
    "липень", "серпень", "вересень", "жовтень", "листопад", "грудень"
)

private val dayNames = listOf(
    "понеділок", "вівторок", "середа", "четвер", "п'ятниця", "субота", "неділя"
)

private val monthHolidays = mapOf(
    1 to listOf("Новий рік (1 січня)", "Різдво Христове (7 січня)"),
    3 to listOf("Міжнародний жіночий день (8 березня)"),
    5 to listOf("День праці (1 травня)", "День перемоги над нацизмом (8 травня)"),
    6 to listOf("День Конституції (28 червня)"),
    8 to listOf("День незалежності України (24 серпня)"),
    10 to listOf("День захисників і захисниць України (1 жовтня)"),
    12 to listOf("Різдво (25 грудня)")
)

fun monthName(month: Int): String = monthNames.getOrElse(month - 1) { "?" }
fun dayName(day: Int): String = dayNames.getOrElse(day - 1) { "?" }
fun holidaysFor(month: Int): List<String> = monthHolidays[month] ?: listOf("Немає відмічених свят")
