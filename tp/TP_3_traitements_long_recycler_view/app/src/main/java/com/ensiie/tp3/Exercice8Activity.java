package com.ensiie.tp3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by Adrian on 29/11/2016.
 */
public class Exercice8Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_recycler_view);
    }
}

class DualListAdapter extends RecyclerView.Adapter {
    public static int DATE_VIEWTYPE = 1;
    public static int MESSAGE_VIEWTYPE = 2;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == DATE_VIEWTYPE){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.exercice_08_1_item_list,parent, false);
            return new DateViewHolder(view);
        } else if(viewType == MESSAGE_VIEWTYPE){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.exercice_08_2_item_list,parent, false);
            return new TextViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

class DateViewHolder extends RecyclerView.ViewHolder{

    public DateViewHolder(View itemView) {
        super(itemView);
    }

    public void setDate(Date d){
        String date = Utils.formatDate(d);
        TextView tv = (TextView)itemView.findViewById(R.id.date);
        tv.setText(date);
    }
}

class TextViewHolder extends RecyclerView.ViewHolder{

    public TextViewHolder(View itemView) {
        super(itemView);
    }

    public void setText(String str){
        TextView tv = (TextView)itemView.findViewById(R.id.message);
        tv.setText(str);
    }
}