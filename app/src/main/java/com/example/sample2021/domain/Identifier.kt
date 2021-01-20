package com.example.sample2021.domain

import java.io.Serializable


abstract class Identifier<T : Serializable>(val rawValue: T) : Serializable {
    override fun equals(other: Any?): Boolean = other is Identifier<*> && other.rawValue == rawValue

    override fun hashCode(): Int = rawValue.hashCode()
}