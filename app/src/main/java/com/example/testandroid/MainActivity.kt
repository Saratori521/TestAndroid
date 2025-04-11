package com.example.testandroid

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: TodoViewModel
    private lateinit var adapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        setupRecyclerView()
        setupFab()
        observeTodos()
    }

    private fun setupRecyclerView() {
        adapter = TodoAdapter(
            onTodoClick = { showEditDialog(it) },
            onTodoChecked = { todo, isChecked ->
                viewModel.updateTodo(todo.copy(isCompleted = isChecked))
            }
        )
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun setupFab() {
        fab.setOnClickListener {
            showAddDialog()
        }
    }

    private fun observeTodos() {
        viewModel.allTodos.observe(this) { todos ->
            adapter.submitList(todos)
        }
    }

    private fun showAddDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Новая задача")
            .setView(R.layout.dialog_add_todo)
            .setPositiveButton("Добавить") { dialog, _ ->
                // Здесь будет логика добавления новой задачи
                dialog.dismiss()
            }
            .setNegativeButton("Отмена") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun showEditDialog(todo: Todo) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Редактировать задачу")
            .setView(R.layout.dialog_add_todo)
            .setPositiveButton("Сохранить") { dialog, _ ->
                // Здесь будет логика редактирования задачи
                dialog.dismiss()
            }
            .setNegativeButton("Отмена") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                // Здесь будет логика поиска
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
} 