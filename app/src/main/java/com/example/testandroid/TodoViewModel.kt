package com.example.testandroid

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val database = TodoDatabase.getDatabase(application)
    private val todoDao = database.todoDao()
    
    val allTodos: LiveData<List<Todo>> = todoDao.getAllTodos()

    fun insertTodo(todo: Todo) {
        viewModelScope.launch {
            todoDao.insertTodo(todo)
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch {
            todoDao.updateTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            todoDao.deleteTodo(todo)
        }
    }

    fun searchTodos(query: String): LiveData<List<Todo>> {
        return todoDao.searchTodos("%$query%")
    }
} 