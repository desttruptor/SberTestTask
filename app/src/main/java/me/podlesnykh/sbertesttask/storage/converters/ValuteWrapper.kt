package me.podlesnykh.sbertesttask.storage.converters

import me.podlesnykh.sbertesttask.network.pojo.Valute
import org.simpleframework.xml.ElementList

data class ValuteWrapper(
    @field:ElementList(name = "Valute", inline = true)
    @param:ElementList(name = "Valute", inline = true)
    val currencyList: List<Valute>
)