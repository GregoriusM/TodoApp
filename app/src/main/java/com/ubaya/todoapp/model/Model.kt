package com.ubaya.todoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//UNTUK MEMBUAT ROOM HARUS ADA 3 (Data Entity Model, DAO, dan Database Class)
//Entity - minta room untuk membuat table todoo
//ColumnInfo - kalo nama di model beda dengan di database
//PrimaryKey - room akan menganggap uuid sebagai primary key dan autogenerate (ditaruh diluar constractor karena yang tau data nya adalah room)

@Entity
data class Todo(
    @ColumnInfo(name = "title")
    var title:String,
    @ColumnInfo(name = "notes")
    var notes:String
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}