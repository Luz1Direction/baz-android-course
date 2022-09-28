package com.example.criptomonedasapp.utils

import java.text.NumberFormat

fun Double.formatAsCurrency(): String {
    return NumberFormat.getCurrencyInstance().format(this)
}