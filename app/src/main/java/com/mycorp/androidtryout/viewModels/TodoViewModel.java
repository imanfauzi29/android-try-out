package com.mycorp.androidtryout.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mycorp.androidtryout.Utils.TodoUtils;
import com.mycorp.androidtryout.interfaces.TodoServices;
import com.mycorp.androidtryout.models.DataResponse;
import com.mycorp.androidtryout.models.TodoModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoViewModel extends ViewModel {

    private MutableLiveData<DataResponse> dataResponse;
    private LiveData<TodoModel> todo;
    TodoServices todoServices;

    public TodoViewModel(){
        dataResponse = new MutableLiveData<DataResponse>();

        init();
    }

    public MutableLiveData<DataResponse> getDataResponse(){

        return dataResponse;
    }

    private void init() {
        UserDatas();
    }

    private void UserDatas() {
        TodoUtils todoUtils = new TodoUtils();
        TodoServices todoServices = todoUtils.todo(TodoServices.class, TodoUtils.URL);

        todoServices.getDataTodo().enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                dataResponse.setValue(response.body());
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {

            }
        });
    }

}
