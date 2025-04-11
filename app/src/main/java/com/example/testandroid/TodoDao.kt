package com.example.testandroid

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos ORDER BY priority DESC, createdAt DESC")
    fun getAllTodos(): LiveData<List<Todo>>

    @Insert
    suspend fun insertTodo(todo: Todo)

    @Update
    suspend fun updateTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("SELECT * FROM todos WHERE title LIKE :searchQuery ORDER BY priority DESC, createdAt DESC")
    fun searchTodos(searchQuery: String): LiveData<List<Todo>>
} 