package com.example.to_do.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.to_do.database.TaskDao
import com.example.to_do.database.TaskDatabase
import com.example.to_do.database.TaskEntry
import com.example.to_do.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    application: Application,
    private var repository: TaskRepository // Injetado pelo Hilt
) : AndroidViewModel(application) {

    private val taskDao = TaskDatabase.getDatabase(application).taskDao()

    var getAllTasks: LiveData<List<TaskEntry>> = repository.getAllTasks()
    val getAllPriorityTasks: LiveData<List<TaskEntry>>

    init {
        repository = TaskRepository(taskDao)
        getAllTasks = repository.getAllTasks()
        getAllPriorityTasks = repository.getAllPriorityTasks()
    }

    fun insert(taskEntry: TaskEntry) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(taskEntry)
        }
    }

    fun delete(taskEntry: TaskEntry) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(taskEntry)
        }
    }

    fun update(taskEntry: TaskEntry) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(taskEntry)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<TaskEntry>> {
        return repository.searchDatabase(searchQuery)
    }


}
