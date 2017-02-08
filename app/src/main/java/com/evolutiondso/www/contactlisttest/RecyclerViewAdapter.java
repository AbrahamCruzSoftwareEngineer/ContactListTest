package com.evolutiondso.www.contactlisttest;

/**
 * Created by Albrtx on 07/02/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.evolutiondso.www.contactlisttest.Model.Result;

import java.util.List;



public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.PersonViewHolder>{

    private List<Result> persons;
    private Context context;



    public RecyclerViewAdapter(List<Result> persons, Context context) {
        this.persons = persons;
        this.context = context;
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;

        TextView personName;
        TextView personAge;
        ImageView personPhoto;
        Result result; //Este se pasa para el onclick

        PersonViewHolder(final View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personAge = (TextView)itemView.findViewById(R.id.person_age);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);

            //Generas un click listener para cada elemento del recycler
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(v.getContext(), Detail.class);
                    //Cambie los POJOS para poder hacerlos Parcelables en esta pagina http://www.parcelabler.com/
                    myIntent.putExtra("RESULT", (Parcelable) result);
                    myIntent.putExtra("POSITION", getAdapterPosition());//Obtiene la posicion que se pasara al detail (como el id)

                    v.getContext().startActivity(myIntent);


                }
            });

        }

    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        holder.personName.setText(persons.get(position).getName());
        holder.personAge.setText(persons.get(position).getEmail());
        holder.result = persons.get(position);
        Glide.with(context)
                .load(persons.get(position).getSmallImageURL())
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.personPhoto);
        //holder.personPhoto.setImageResource(Integer.parseInt(persons.get(position).getSmallImageURL()));
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}
