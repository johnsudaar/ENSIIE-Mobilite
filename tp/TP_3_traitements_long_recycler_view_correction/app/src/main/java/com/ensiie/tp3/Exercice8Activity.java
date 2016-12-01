package com.ensiie.tp3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Adrian on 29/11/2016.
 */
public class Exercice8Activity extends AppCompatActivity {

    private ArrayList<Object> dataTwoTypesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_recycler_view);

        dataTwoTypesList = DataGenerator.getDataForTwoTypesList();

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new MyAdapter());
    }

    class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private final static int HEADER = 0, CONTENT = 1;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            if (viewType == HEADER) {
                final View view = layoutInflater.inflate(R.layout.exercice_08_1_item_list, parent, false);
                return new HeaderViewHolder(view);
            } else {
                final View view = layoutInflater.inflate(R.layout.exercice_08_2_item_list, parent, false);
                return new ContentViewHolder(view);
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            final Object object = dataTwoTypesList.get(position);

            if (holder.getItemViewType() == HEADER) {
                final HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
                headerViewHolder.date.setText(Utils.formatDate((Date) object));
            } else {
                final ContentViewHolder contentViewHolder = (ContentViewHolder) holder;
                contentViewHolder.message.setText((String) object);
            }
        }

        @Override
        public int getItemCount() {
            return dataTwoTypesList.size();
        }

        @Override
        public int getItemViewType(int position) {
            if (dataTwoTypesList.get(position) instanceof Date) {
                return HEADER;
            } else {
                return CONTENT;
            }
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        TextView date;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.date);
        }
    }

    class ContentViewHolder extends RecyclerView.ViewHolder {

        TextView message;

        public ContentViewHolder(View itemView) {
            super(itemView);
            message = (TextView) itemView.findViewById(R.id.message);
        }
    }
}
