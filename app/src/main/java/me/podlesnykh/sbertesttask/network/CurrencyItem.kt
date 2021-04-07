package me.podlesnykh.sbertesttask.network

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "ValCurs")
data class CurrencyItem(
    @Element(name = "NumCode")
    val numCode: Int,
    @Element(name = "CharCode")
    val charCode: String,
    @Element(name = "Nominal")
    val nominal: Int,
    @Element(name = "Name")
    val name: String,
    @Element(name = "Value")
    val value: Double
)
