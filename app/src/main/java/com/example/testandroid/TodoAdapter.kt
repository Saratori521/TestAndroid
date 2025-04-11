package com.example.testandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    private val onTodoClick: (Todo) -> Unit,
    private val onTodoChecked: (Todo, Boolean) -> Unit
) : ListAdapter<Todo, TodoAdapter.TodoViewHolder>(TodoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = getItem(position)
        holder.bind(todo)
    }

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        private val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)

        fun bind(todo: Todo) {
            titleTextView.text = todo.title
            descriptionTextView.text = todo.description
            checkBox.isChecked = todo.isCompleted

            itemView.setOnClickListener { onTodoClick(todo) }
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                onTodoChecked(todo, isChecked)
            }
        }
    }

    private class TodoDiffCallback : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }
    }
} 