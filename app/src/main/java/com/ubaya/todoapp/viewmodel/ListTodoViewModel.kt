package com.ubaya.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ubaya.todoapp.model.Todo
import com.ubaya.todoapp.model.TodoDatabase
import com.ubaya.todoapp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

//KALO ADA MERAH ALT+ENTER, IMPLEMENT MEMBERS
//AKSES COROUTINE, di set bahwa thread yg dipake adalah IO
class ListTodoViewModel(application: Application):
    AndroidViewModel(application), CoroutineScope{

    private var job = Job()

    val todoLD = MutableLiveData<List<Todo>>()
    val todoLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh(){
        loadingLD.value = true
        todoLoadErrorLD.value = false
        launch {
            val db = buildDb(getApplication())
            todoLD.postValue(db.todoDao().selectAllTodo())
            loadingLD.postValue(false)
        }
    }

    fun clearTask(todo:Todo) {
        launch {
            val db = buildDb(getApplication())
            db.todoDao().deleteTodo(todo)
            todoLD.postValue(db.todoDao().selectAllTodo())
        }
    }

}