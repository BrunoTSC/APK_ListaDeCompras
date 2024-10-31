package com.example.listadecompras.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listadecompras.data.ItemsDatabase
import androidx.lifecycle.viewModelScope
import com.example.listadecompras.data.ItemEntity
import com.example.listadecompras.data.toModel
import com.example.listadecompras.model.ItemModel
import com.example.listadecompras.model.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemsViewModel(
    private val database: ItemsDatabase
): ViewModel(){

    private val itemsLiveData = MutableLiveData<List<ItemModel>>()

    fun addItem(name: String){
        viewModelScope.launch(Dispatchers.IO) {
            val entity = ItemEntity(id = 0, name = name)
            database.itemsDao().insert(entity)
            Log.d("ItemsViewModel", "Item adicionado: $name")
            fetchAll()
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            fetchAll()
        }
    }

    private fun fetchAll(){
        val result = database.itemsDao().getAll().map {
            it.toModel(onRemove = ::removeItem)
        }
        itemsLiveData.postValue(result)
    }
    private fun removeItem(item: ItemModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val entity = item.toEntity()
            database.itemsDao().delete(entity)
            fetchAll()
        }
    }
}

