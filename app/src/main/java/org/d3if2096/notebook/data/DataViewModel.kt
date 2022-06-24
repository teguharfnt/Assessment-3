package org.d3if2096.notebook.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2096.notebook.db.DataDao
import org.d3if2096.notebook.db.DataEntity
import org.d3if2096.notebook.model.Data
import org.d3if2096.notebook.model.masukData

class DataViewModel(private val db: DataDao): ViewModel(){

    private val data = MutableLiveData<Data?>()

    fun masukData(judul: String, deskripsi: String){
        val dataNotebook = DataEntity(
            judul=judul,
            deskripsi = deskripsi
        )

        data.value = dataNotebook.masukData()

        viewModelScope.launch {
            withContext(Dispatchers.IO){
                db.insert(dataNotebook)
            }
        }
    }

    fun getData():LiveData<Data?> = data
}