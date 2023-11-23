package com.abc.`in`.kural.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abc.`in`.kural.model.Adhikaram
import com.abc.`in`.kural.model.Iyal
import com.abc.`in`.kural.model.Paal
import com.abc.`in`.kural.repository.KuralRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.util.Log
import com.abc.`in`.kural.model.Kural
import com.abc.`in`.kural.model.Athisudi

class KuralViewModel(private val repository: KuralRepository) : ViewModel() {

    private val _adhikaramData = MutableStateFlow<List<Adhikaram>>(emptyList())
    val adhikaramData: StateFlow<List<Adhikaram>> = _adhikaramData

    private val _iyalData = MutableStateFlow<List<Iyal>>(emptyList())
    val iyalData: StateFlow<List<Iyal>> = _iyalData

    private val _paalData = MutableStateFlow<List<Paal>>(emptyList())
    val paalData: StateFlow<List<Paal>> = _paalData

    private val _kuralData = MutableStateFlow<List<Kural>>(emptyList())
    val kuralData: StateFlow<List<Kural>> = _kuralData

    private val _kural = MutableStateFlow<Kural?>(null)
    val kural: StateFlow<Kural?> = _kural

    private val _athisudi = MutableStateFlow<List<Athisudi>>(emptyList())
    val athisudi: StateFlow<List<Athisudi>> = _athisudi

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            _adhikaramData.value = repository.getAdhikaramData()
            _athisudi.value = repository.getAthisudiData()
        }
    }

    fun filteredIyalData(name:String) {
        Log.e("namekey",name)
        viewModelScope.launch {
            val allAdhikaramData = _adhikaramData.value
            val filteredAdhikarams = allAdhikaramData.single { data ->
                // Apply your filtering logic here based on the filterText
                //adhikaram.name.contains(filterText, ignoreCase = true)
                data.name == name
            }
            Log.e("iyallist",filteredAdhikarams.iyal.toString())
           // _filteredAdhikaramData.value = filteredAdhikarams
            _iyalData.value=filteredAdhikarams.iyal
        }
    }

    fun filteredPaalData(name:String) {
        Log.e("kurallistdata",name)

        viewModelScope.launch {
            val mIyalData = _iyalData.value
            Log.e("iyallist",_iyalData.value.toString())
            val filteredData = mIyalData.single { data ->
                data.name == name
            }
            Log.e("kurallist",filteredData.paal.toString())
            _paalData.value=filteredData.paal
        }
    }

    fun filteredKuralData(name:String) {
        viewModelScope.launch {
            val allData = _paalData.value
            val filteredData = allData.single { data ->
                Log.e("kurallist",data.toString())
                // Apply your filtering logic here based on the filterText
                //adhikaram.name.contains(filterText, ignoreCase = true)
                data.name == name
            }
            Log.e("kurallist",filteredData.kuralkal.toString())
            _kuralData.value=filteredData.kuralkal
        }
    }

    fun filteredKural(kuraldata:Kural) {
        viewModelScope.launch {
            val allData = _kuralData.value
            val filteredData = allData.single { data ->
                Log.e("kurallist",data.toString())
                // Apply your filtering logic here based on the filterText
                //adhikaram.name.contains(filterText, ignoreCase = true)
                data == kuraldata
            }
            _kural.value=filteredData
        }
    }
}