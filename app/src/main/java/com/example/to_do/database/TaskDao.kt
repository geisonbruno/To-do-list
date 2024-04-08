package com.example.to_do.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {
    @Insert
    suspend fun  insert(taskEntry: TaskEntry)

    @Delete
    suspend fun delete(taskEntry: TaskEntry)

    @Update
    suspend fun update(taskEntry: TaskEntry)

    @Query("DELETE FROM task_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM task_table ORDER BY timestamp DESC")
    fun getAllTasks(): LiveData<List<TaskEntry>>

    @Query("select * from task_table order by priority asc")
    fun getAllPriorityTasks(): LiveData<List<TaskEntry>>

//  - Seleciona todos os registros da tabela 'task_table'
    //   onde o campo 'title' contém uma string que corresponde
    //   ao padrão especificado na variável ':searchQuery'.
// -  Os resultados são então ordenados pelo campo 'timestamp' em ordem decrescente,
    //   exibindo os registros mais recentes primeiro.
    @Query("select * from task_table where title like :searchQuery order by timestamp desc")
    fun searchDatabase(searchQuery: String): LiveData<List<TaskEntry>>
}