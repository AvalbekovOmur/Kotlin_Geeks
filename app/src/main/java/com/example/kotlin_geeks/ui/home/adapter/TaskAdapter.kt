package com.example.kotlin_geeks.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_geeks.databinding.ItemTaskBinding
import com.example.kotlin_geeks.model.Task

class TaskAdapter(private val context: Context) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(task: Task)
        fun onItemDelete(task: Task)
    }

    private val list = arrayListOf<Task>()

    fun setTask(tasks: List<Task>) {
        list.clear()
        list.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding).apply {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.onItemDelete(list[position])
                    val task = list[position]
                    showConfirmationDialog(task)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = list[position]
        holder.bind(currentTask)
    }

    override fun getItemCount(): Int = list.size

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.apply {
                tvTitle.text = task.title
                tvDescription.text = task.description
            }
        }
    }

    private fun showConfirmationDialog(task: Task) {
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setTitle("Подтверждение")
            .setMessage("Вы уверены, что хотите  удалить этот элемент?")
            .setPositiveButton("Да") { dialog, _ ->
                onItemClickListener?.onItemDelete(task)
                deleteTask(task)
                dialog.dismiss()
            }
            .setNegativeButton("Нет") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun deleteTask(task: Task) {
        val position = list.indexOf(task)
        if (position != -1) {
            list.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}
