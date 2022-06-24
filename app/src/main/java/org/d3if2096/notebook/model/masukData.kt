package org.d3if2096.notebook.model

import org.d3if2096.notebook.db.DataEntity

fun DataEntity.masukData():Data{
    val judul=judul
    val deskripsi=deskripsi

    return Data(judul,deskripsi)
}