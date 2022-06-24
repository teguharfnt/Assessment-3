package org.d3if2096.notebook.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data")
data class DataEntity (
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0L,
    var judul :String,
    var deskripsi : String
)