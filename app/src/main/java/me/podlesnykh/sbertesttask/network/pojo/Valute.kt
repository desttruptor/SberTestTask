package me.podlesnykh.sbertesttask.network.pojo

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "Valute")
data class Valute(
    @field:Element(name = "NumCode")
    @param:Element(name = "NumCode")
    val numCode: Int,
    @field:Element(name = "CharCode")
    @param:Element(name = "CharCode")
    val charCode: String,
    @field:Element(name = "Nominal")
    @param:Element(name = "Nominal")
    val nominal: Int,
    @field:Element(name = "Name")
    @param:Element(name = "Name")
    val name: String,
    @field:Element(name = "Value")
    @param:Element(name = "Value")
    val value: String
)