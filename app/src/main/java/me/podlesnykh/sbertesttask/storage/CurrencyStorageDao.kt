package me.podlesnykh.sbertesttask.storage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import me.podlesnykh.sbertesttask.storage.entities.CurrencyListEntity

@Dao
interface CurrencyStorageDao {
    @Query("SELECT * FROM currencylistentity")
    fun getAll(): CurrencyListEntity

    @Query("SELECT COUNT(*) FROM currencylistentity")
    fun countRecords(): Int

    @Query("DELETE FROM currencylistentity")
    fun deleteAll()

    @Insert
    fun insertAll(currencyList: CurrencyListEntity)
}