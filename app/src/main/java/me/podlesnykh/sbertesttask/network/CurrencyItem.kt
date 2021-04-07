package me.podlesnykh.sbertesttask.network

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

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

@Root(name = "ValCurs")
data class CurrencyList(
    @ElementList(inline = true)
    val currencyList: List<CurrencyItem>,
    @Attribute(name = "Date")
    val date: String
)
