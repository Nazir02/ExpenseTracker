package com.encom.todo.data.model

import com.google.gson.annotations.SerializedName


data class ToDoModel(
    @SerializedName("id")
    val id:Int,
    @SerializedName("title")
    val title:String,
    @SerializedName("description")
    val description:String,
    @SerializedName("isFinished")
    var isFinished:Boolean
)
