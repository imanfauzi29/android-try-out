package com.mycorp.androidtryout.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DataResponse {

    @SerializedName("status")
    Boolean status;

    @SerializedName("message")
    String message;

    @SerializedName("data")
    List<TodoModel> dataTodo;

    public List<TodoModel> getDataTodo() {
        return dataTodo;
    }
}
