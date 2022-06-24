package org.d3if2096.notebook.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if2096.notebook.db.DataDao

class DataViewModelFactory(
    private val db: DataDao
):ViewModelProvider.Factory{
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DataViewModel::class.java)){
            return DataViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}