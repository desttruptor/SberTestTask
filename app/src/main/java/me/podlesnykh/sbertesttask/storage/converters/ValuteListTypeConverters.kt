package me.podlesnykh.sbertesttask.storage.converters

import androidx.room.TypeConverter
import me.podlesnykh.sbertesttask.network.pojo.Valute
import org.simpleframework.xml.core.Persister
import java.io.StringReader
import java.io.StringWriter

object ValuteListTypeConverters {
    @TypeConverter
    @JvmStatic
    fun fromList(list: List<Valute>): String {
        val wrappedList = ValuteWrapper(list)
        val writer = StringWriter()
        val serializer = Persister()
        serializer.write(wrappedList, writer)
        return writer.toString()
    }

    @TypeConverter
    @JvmStatic
    fun fromString(s: String) : List<Valute> {
        val reader = StringReader(s)
        val serializer = Persister()
        return serializer.read(ValuteWrapper::class.java, reader, false).currencyList
    }
}