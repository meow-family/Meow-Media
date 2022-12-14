package com.octopus.socialnetwork.ui.util.extensions

fun <T> List<T>.lastIndexOrZero() : Int {
    return if (this.isEmpty()) 0 else this.size - 1
}