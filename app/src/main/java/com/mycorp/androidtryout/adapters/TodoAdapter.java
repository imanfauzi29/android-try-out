package com.mycorp.androidtryout.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mycorp.androidtryout.R;
import com.mycorp.androidtryout.databinding.TodoListBinding;
import com.mycorp.androidtryout.models.TodoModel;

import java.util.ArrayList;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> implements Filterable {

    private ArrayList<TodoModel> todos = new ArrayList<>();
    private  ArrayList<TodoModel> todoFilter;

    public TodoAdapter(Context context, List<TodoModel> dataResponse) {
        Log.d("data", String.valueOf(dataResponse));
        this.todos = (ArrayList<TodoModel>) dataResponse;

        this.todoFilter = (ArrayList<TodoModel>) dataResponse;
        notifyItemChanged(0, todoFilter);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                Log.d("data", String.valueOf(charSequence));
                String string = charSequence.toString();
                if (string.isEmpty()) {
                    todoFilter = todos;
                } else {
                    List<TodoModel> filteredList = new ArrayList<>();
                    for (TodoModel user : todos) {
                        if (user.getTask().toLowerCase().contains(string.toLowerCase())) {
                            Log.d("user", user.getTask());
                            filteredList.add(user);
                        }
                    }
                    todoFilter = (ArrayList<TodoModel>) filteredList;

                    Log.d("ec", String.valueOf(todoFilter));
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = todoFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                Log.d("publishResults: ", String.valueOf(filterResults.values));
                todoFilter = (ArrayList<TodoModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
    public interface ProductListener {
        void onUpdate(TodoModel todo);

        void onDelete(TodoModel todo);
    }

    private ProductListener listener;

    public void setListener(ProductListener listener) {
        this.listener = listener;
    }

    public void setProducts(ArrayList<TodoModel> todos) {
        this.todoFilter = todos;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.todo_list,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.ViewHolder holder, int position) {
        holder.bindData(todoFilter.get(position));
    }

    @Override
    public int getItemCount() {
        return todoFilter.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TodoListBinding binding;

        public ViewHolder(@NonNull TodoListBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void bindData(TodoModel todoModel) {
            Log.d("data", todoModel.getTask());
            binding.setTodo(todoModel);
        }
    }

}