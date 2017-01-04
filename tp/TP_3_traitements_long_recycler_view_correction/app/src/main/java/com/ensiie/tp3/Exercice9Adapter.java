package com.ensiie.tp3;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ensiie.tp3.bo.TextDescription;

import java.util.ArrayList;

/**
 * Created by Adrian on 29/11/2016.
 */
class Exercice9Adapter extends RecyclerView.Adapter<Exercice9Adapter.MyViewHolder> {

    interface MyItemClickListener {
        void onItemClickListener(int position);
    }

    private MyItemClickListener listener;
    private ArrayList<TextDescription> dataSimpleList;

    public Exercice9Adapter(ArrayList<TextDescription> dataSimpleList) {
        this.dataSimpleList = dataSimpleList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercice_06_item_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final TextDescription textDescription = dataSimpleList.get(position);
        holder.title.setText(textDescription.getText());
        holder.description.setText(textDescription.getDescription());
    }

    @Override
    public int getItemCount() {
        return dataSimpleList.size();
    }

    public void setListener(MyItemClickListener listener) {
        this.listener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;

        public MyViewHolder(final View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClickListener(getAdapterPosition());
                }
            });
        }
    }
}