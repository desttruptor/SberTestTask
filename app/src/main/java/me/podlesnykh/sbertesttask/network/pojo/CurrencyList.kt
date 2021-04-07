package me.podlesnykh.sbertesttask.network.pojo

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "ValCurs")
data class CurrencyList(
    @field:ElementList(name = "Valute", inline = true)
    @param:ElementList(name = "Valute", inline = true)
    val currencyList: List<Valute>,
    @field:Attribute(name = "Date")
    @param:Attribute(name = "Date")
    val date: String
)

