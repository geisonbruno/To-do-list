package com.android.todolist.data

import androidx.lifecycle.LiveData
import com.android.todolist.data.local.TaskDao
import com.android.todolist.data.local.TaskEntry
import javax.inject.Inject

class TaskRepository @Inject constructor(private val taskDao: TaskDao) {

    suspend fun insert(taskEntry: TaskEntry) = taskDao.insert(taskEntry)

    suspend fun updateData(taskEntry: TaskEntry) = taskDao.update(taskEntry)

    suspend fun deleteItem(taskEntry: TaskEntry) = taskDao.delete(taskEntry)

    suspend fun deleteAll() {
        taskDao.deleteAll()
    }

    fun getAllTasks() = taskDao.getAllTasks()

    fun getAllPriorityTasks() = taskDao.getAllPriorityTasks()

    fun searchDatabase(searchQuery: String): LiveData<List<TaskEntry>> {
        return taskDao.searchDatabase(searchQuery)
    }

}
