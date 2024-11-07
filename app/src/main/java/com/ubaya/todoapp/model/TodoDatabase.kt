package com.ubaya.todoapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ubaya.todoapp.util.DB_NAME
import com.ubaya.todoapp.util.MIGRATION_1_2

//@Database - UNTUK KASI TAU ROOM SETTINGAN DATABASE YG ADA
//VERSION - KALAU ADA UPDATE DATABASE

//SINGLETON - Sebuah Class hanya boleh menciptakan satu biji objek saja
//VOLATILE - Memberitahu Thread lainnya yang mau akses database nya, bahwa thread pertama sudah akses duluan
//INVOKE - fungsi yang dipanggil saat dijalankan

//SETIAP ADA PERUBAHAN VERSION + 1

@Database(entities = arrayOf(Todo::class), version = 2)
abstract class TodoDatabase:RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object{
        @Volatile private var instance: TodoDatabase ?= null
        private val LOCK = Any()

        //Create Database
        fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, TodoDatabase::class.java,
            DB_NAME)
            .addMigrations(MIGRATION_1_2)
            .build()

        operator fun invoke(context: Context){
            if(instance==null){
                synchronized(LOCK){
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}