package com.example.to_do.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.to_do.database.TaskDatabase
import com.example.to_do.database.TaskEntry
import com.example.to_do.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val taskDao = TaskDatabase.getDatabase(application).taskDao()
    private val repository : TaskRepository

    val getAllTasks: LiveData<List<TaskEntry>>
    init {
        repository = TaskRepository(taskDao)
        getAllTasks = repository.getAllTasks()
    }

    fun insert(taskEntry: TaskEntry){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(taskEntry)
        }
    }
    fun delete(taskEntry: TaskEntry){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(taskEntry)
        }
    }
    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}