package com.ubaya.todoapp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//FUNGSINYA UNTUK MENULIS SEMUA FUNGSI YANG ADA HUB DENGAN DATABASE
//LEBIH BAIK SETIAP TABLE PUNYA DAO NYA MASING2


//INSERT - QUERY UNTUK INSERT KE PARAMETER (OBJEKNYA)
//QUERY - QUERY CUSTOM
//UPDATE

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg todo:Todo)

    @Query("SELECT * FROM todo")
    fun selectAllTodo(): List<Todo>

    @Query("SELECT * FROM todo WHERE uuid = :id")
    fun selectTodo(id:Int): Todo

    @Delete
    fun deleteTodo(todo:Todo)
}