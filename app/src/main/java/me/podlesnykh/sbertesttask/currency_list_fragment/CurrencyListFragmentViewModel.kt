package me.podlesnykh.sbertesttask.currency_list_fragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import me.podlesnykh.sbertesttask.BuildConfig
import me.podlesnykh.sbertesttask.network.OkHttpInstance
import me.podlesnykh.sbertesttask.network.pojo.CurrencyList
import me.podlesnykh.sbertesttask.storage.Database
import me.podlesnykh.sbertesttask.storage.entities.CurrencyListEntity
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import org.simpleframework.xml.core.Persister
import java.io.IOException
import java.io.StringReader

class CurrencyListFragmentViewModel(application: Application) : ViewModel() {

    private val _currencyList = MutableLiveData<CurrencyList>()
    val currencyList get() = _currencyList

    private val _loadingFlag = MutableLiveData<Boolean>()
    val loadingFlag get() = _loadingFlag

    private val _errorDialog = MutableLiveData<Boolean>()
    val errorDialog get() = _errorDialog

    private val db = Room.databaseBuilder(
        application.applicationContext,
        Database::class.java,
        "myDatabase"
    ).build()

    fun loadCurrencyList() {
        val request = Request.Builder()
            .url(BuildConfig.CURRENCY_RATE_URL)
            .build()

        OkHttpInstance.client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                errorDialog.postValue(true)
                _loadingFlag.postValue(false)
                Log.v("ERROR", e.localizedMessage)
                val dbEntity = db.currencyStorageDao().getAll()
                if (dbEntity != null) {
                    _currencyList.postValue(
                        CurrencyList(
                            dbEntity.currencyList,
                            dbEntity.date
                        )
                    )
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val data = parseXml(response)
                db.currencyStorageDao().deleteAll()
                db.currencyStorageDao().insertAll(
                    CurrencyListEntity(
                        currencyList = data.currencyList,
                        date = data.date
                    )
                )
                _currencyList.postValue(data)
                loadingFlag.postValue(false)
            }
        })
    }

    private fun parseXml(response: Response): CurrencyList {
        val reader = StringReader(response.body?.string())
        val serializer = Persister()
        return serializer.read(CurrencyList::class.java, reader, false)
    }
}