package com.appera.app.doghub;

// DogAdapter.java
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.DogViewHolder> {
    private Context context;
    private List<Dog> dogList;

    public DogAdapter(Context context, List<Dog> dogList) {
        this.context = context;
        this.dogList = dogList;
    }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dog, parent, false);
        return new DogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogViewHolder holder, int position) {
        Dog dog = dogList.get(position);
        holder.textName.setText(dog.getBreed());
        holder.textLifespan.setText("Lifespan: " + dog.getLifespan());
        holder.textTemperament.setText("Temperament: " + dog.getTemperament());
        holder.textGroup.setText("Group: " + dog.getGroup());
        Picasso.get().load(dog.getImageUrl()).into(holder.imageDog);
    }

    @Override
    public int getItemCount() {
        return dogList.size();
    }

    public static class DogViewHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textLifespan;
        TextView textTemperament;
        TextView textGroup;
        ImageView imageDog;

        public DogViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textLifespan = itemView.findViewById(R.id.textLifespan);
            textTemperament = itemView.findViewById(R.id.textTemperament);
            textGroup = itemView.findViewById(R.id.textGroup);
            imageDog = itemView.findViewById(R.id.imageDog);
        }
    }
}


