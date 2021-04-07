package me.podlesnykh.sbertesttask.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.podlesnykh.sbertesttask.storage.converters.ValuteListTypeConverters
import me.podlesnykh.sbertesttask.storage.entities.CurrencyListEntity

@Database(entities = [CurrencyListEntity::class], version = 1)
@TypeConverters(value = [ValuteListTypeConverters::class])
abstract class Database : RoomDatabase() {
    abstract fun currencyStorageDao(): CurrencyStorageDao
}