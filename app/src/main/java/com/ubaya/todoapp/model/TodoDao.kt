package com.ubaya.todoapp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

//FUNGSINYA UNTUK MENULIS SEMUA FUNGSI YANG ADA HUB DENGAN DATABASE
//LEBIH BAIK SETIAP TABLE PUNYA DAO NYA MASING2


//INSERT - QUERY UNTUK INSERT KE PARAMETER (OBJEKNYA)
//QUERY - QUERY CUSTOM
//UPDATE

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg todo:Todo)

    @Query("SELECT * FROM todo ORDER BY priority DESC")
    fun selectAllTodo(): List<Todo>

    @Query("SELECT * FROM todo WHERE uuid = :id")
    fun selectTodo(id:Int): Todo

    //UPDATE QUERY MANUAL
    @Query("UPDATE todo SET title=:title, notes=:notes, " +
            "priority=:priority WHERE uuid = :id")
    fun update(title:String, notes:String, priority:Int, id:Int)

    //UPDATE SMUA (TIDAK MANUAL)
    @Update
    fun update(todo:Todo)

    @Delete
    fun deleteTodo(todo:Todo)
}