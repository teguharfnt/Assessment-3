package org.d3if2096.notebook.datanote

import androidx.lifecycle.ViewModel
import org.d3if2096.notebook.db.DataDao

class NoteViewModel(private val db:DataDao) : ViewModel() {
    val data = db.getLastData()


}