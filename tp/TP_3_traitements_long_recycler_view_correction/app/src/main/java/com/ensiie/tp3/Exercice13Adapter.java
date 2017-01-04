package com.ensiie.tp3;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ensiie.tp3.bo.Neighbourhood;

import java.util.ArrayList;

/**
 * Created by Adrian on 29/11/2016.
 */
public class Exercice13Adapter extends RecyclerView.Adapter<Exercice13Adapter.MyViewHolder> {

	interface MyItemClickListener {
		void onItemClickListener(int position);
	}

	private Exercice13Adapter.MyItemClickListener listener;
	private ArrayList<Neighbourhood>              neighbourhoods;

	public Exercice13Adapter(ArrayList<Neighbourhood> neighbourhoods) {
		this.neighbourhoods = neighbourhoods;
	}

	@Override
	public Exercice13Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercice_13_item_list, parent, false);
		return new Exercice13Adapter.MyViewHolder(view);
	}

	@Override
	public void onBindViewHolder(Exercice13Adapter.MyViewHolder holder, int position) {
		final Neighbourhood neighbourhood = neighbourhoods.get(position);
		holder.city.setText(neighbourhood.getName());
	}

	@Override
	public int getItemCount() {
		return neighbourhoods.size();
	}

	public void setListener(Exercice13Adapter.MyItemClickListener listener) {
		this.listener = listener;
	}

	class MyViewHolder extends RecyclerView.ViewHolder {

		TextView city;

		public MyViewHolder(final View itemView) {
			super(itemView);
			city = (TextView) itemView.findViewById(R.id.city);
			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					listener.onItemClickListener(getAdapterPosition());
				}
			});
		}
	}
}
