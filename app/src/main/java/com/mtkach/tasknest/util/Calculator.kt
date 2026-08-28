package com.mtkach.tasknest.util

sealed class CalcResult {
    data class Ok(val text: String) : CalcResult()
    data class Err(val message: String) : CalcResult()
}

object Calculator {
    fun compute(aRaw: String, bRaw: String, op: String): CalcResult {
        val a = aRaw.replace(',', '.').toDoubleOrNull()
            ?: return CalcResult.Err("Введіть обидва числа")
        val b = bRaw.replace(',', '.').toDoubleOrNull()
            ?: return CalcResult.Err("Введіть обидва числа")

        if (op == "÷" && b == 0.0) return CalcResult.Err("Ділення на нуль")

        val value = when (op) {
            "+" -> a + b
            "−" -> a - b
            "×" -> a * b
            "÷" -> a / b
            else -> return CalcResult.Err("Невідома операція")
        }

        val text = if (value == Math.floor(value) && !value.isInfinite()) {
            value.toLong().toString()
        } else {
            "%.4f".format(value).trimEnd('0').trimEnd('.')
        }
        return CalcResult.Ok(text)
    }
}
