package com.tracker.jettipapp.util

fun getTipAmount(totalBill: Int, tipPercentage: Int): Int {
    return (totalBill * tipPercentage)/100
}