package com.ensiie.tp4;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ensiie.tp4.database.Todo;

import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter {

    interface TodoListener {
        void onTodoSelected(Todo todo);
    }

    private ArrayList<Todo> todos;
    private TodoListener listener;

    public TodoAdapter(ArrayList<Todo> todos) {
        this.todos = todos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_todo, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.text.setText(todos.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    public void setListener(TodoListener listener) {
        this.listener = listener;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        TextView text;

        MyViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onTodoSelected(todos.get(getAdapterPosition()));
                }
            });
        }
    }
}


