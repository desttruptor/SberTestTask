package me.podlesnykh.sbertesttask.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import me.podlesnykh.sbertesttask.network.pojo.Valute

@Entity
data class CurrencyListEntity(
    @ColumnInfo(name = "valute_list") val currencyList: List<Valute>,
    @PrimaryKey @ColumnInfo(name = "date") val date: String
)