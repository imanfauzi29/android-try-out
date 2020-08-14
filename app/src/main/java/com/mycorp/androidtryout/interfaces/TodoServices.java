package com.mycorp.androidtryout.interfaces;

import com.mycorp.androidtryout.models.DataResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TodoServices {

    @GET("todos")
    Call<DataResponse> getDataTodo();
}
