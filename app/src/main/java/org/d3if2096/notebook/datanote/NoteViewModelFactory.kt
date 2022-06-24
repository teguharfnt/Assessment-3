package org.d3if2096.notebook.datanote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if2096.notebook.data.DataViewModel
import org.d3if2096.notebook.db.DataDao

class NoteViewModelFactory (
    private val db: DataDao
): ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)){
            return NoteViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModelClass")
    }
}