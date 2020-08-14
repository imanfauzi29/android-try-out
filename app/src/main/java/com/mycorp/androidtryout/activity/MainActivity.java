package com.mycorp.androidtryout.activity;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.google.android.material.textfield.TextInputEditText;
import com.mycorp.androidtryout.R;
import com.mycorp.androidtryout.adapters.TodoAdapter;
import com.mycorp.androidtryout.interfaces.TodoServices;
import com.mycorp.androidtryout.models.DataResponse;
import com.mycorp.androidtryout.models.TodoModel;
import com.mycorp.androidtryout.viewModels.TodoViewModel;

import java.util.List;
import java.util.Optional;

public class MainActivity extends AppCompatActivity {

    TodoViewModel todoViewModel;
    TodoAdapter todoAdapter;
    RecyclerView recyclerView;
    TextInputEditText textSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvTodos);
        textSearch = findViewById(R.id.etSearch);

        todoViewModel = ViewModelProviders.of(this).get(TodoViewModel.class);
        todoViewModel.getDataResponse().observe(this, new Observer<DataResponse>() {
            @Override
            public void onChanged(DataResponse dataResponse) {
                List<TodoModel> todoModels = dataResponse.getDataTodo();
                todoAdapter = new TodoAdapter(MainActivity.this, todoModels);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                        LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(todoAdapter);
            }
        });

        textSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                todoAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}