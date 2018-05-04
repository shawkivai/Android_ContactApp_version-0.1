package com.example.user04.contactapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER04 on 2/19/2018.
 */

public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder> {

    private List<listitem> listitems;
    private Context context;

    public Myadapter(List<listitem> listitems, Context context) {
        this.listitems = listitems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_category, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        listitem listItem = listitems.get(position);

        holder.name.setText(listItem.getName());
        holder.number.setText(listItem.getNumber());
        holder.email.setText(listItem.getEmail());
        holder.company.setText(listItem.getCompany());


//        holder.imageView.setImageResource(listItem.getCompany());


    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }


    public void setFilter(ArrayList<listitem>newList){


        listitems=new ArrayList<>();
        listitems.addAll(newList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView number ;
        public TextView email;
        public TextView company;



        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);

            number = (TextView) itemView.findViewById(R.id.number);

            email= (TextView) itemView.findViewById(R.id.email);
            company= (TextView) itemView.findViewById(R.id.company);

            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {




                    listitem listItem = listitems.get(getLayoutPosition());
                    Intent myIntent = new Intent(context, EditContact.class);
                    myIntent.putExtra("id", listItem.getName()); //Optional parameters
                    myIntent.putExtra("name", listItem.getName()); //Optional parameters
                    myIntent.putExtra("number", listItem.getNumber()); //Optional parameters
                    myIntent.putExtra("email", listItem.getEmail()); //Optional parameters
                    myIntent.putExtra("company", listItem.getCompany());
                    context.startActivity(myIntent);

                }
            });

        }

    }


}

