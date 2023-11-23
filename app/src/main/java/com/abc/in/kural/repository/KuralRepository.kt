package com.abc.`in`.kural.repository

import android.content.Context
import com.abc.`in`.kural.model.Adhikaram
import com.abc.`in`.kural.model.Thirukkural
import com.abc.`in`.kural.model.AathisudiList
import com.abc.`in`.kural.model.Athisudi
import com.abc.`in`.kural.data.AathicudiSource
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

class KuralRepository(private val context: Context) {

    suspend fun getAdhikaramData(): List<Adhikaram> {
        val jsonString = withContext(Dispatchers.IO) {
            context.assets.open("thirukkural.json").bufferedReader().use {
                it.readText()
            }
        }
        val gson = Gson()
        val athikaramWrapper = gson.fromJson(jsonString, Thirukkural::class.java)
        return athikaramWrapper.adhikaram
    }

    suspend fun getAthisudiData(): List<Athisudi> {
        val jsonString = withContext(Dispatchers.IO) {
            AathicudiSource.AathicudiData
        }
        val gson = Gson()
        val data = gson.fromJson(jsonString, AathisudiList::class.java)
        return data.athisudi
    }
}