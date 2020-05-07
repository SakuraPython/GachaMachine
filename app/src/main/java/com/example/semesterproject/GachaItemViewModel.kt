package com.example.semesterproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*

class GachaItemViewModel : ViewModel() {
    private val gachaRepository = GachaRepository.get()
    private val gachaIdLiveData = MutableLiveData<UUID>()

    /*var gachaLiveData: LiveData<GachaItem?> =
        Transformations.switchMap(gachaIdLiveData) { id ->
            gachaRepository.getItem(id)
        }*/
    var gachaLiveData = gachaRepository.getItems()

    fun loadItem(gachaItemId: UUID) {
        gachaIdLiveData.value = gachaItemId
    }

    fun saveItem(gachaItem: GachaItem) {
        gachaRepository.addItem(gachaItem)
    }
}