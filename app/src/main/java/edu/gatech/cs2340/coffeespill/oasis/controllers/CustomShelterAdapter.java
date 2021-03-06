package edu.gatech.cs2340.coffeespill.oasis.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import edu.gatech.cs2340.coffeespill.oasis.model.Shelter;
import edu.gatech.cs2340.coffeespill.oasis.R;

/**
 * Created by andrew_chang on 2018-03-22.
 */

public class CustomShelterAdapter extends RecyclerView.Adapter<CustomShelterAdapter.ViewHolder> {
    public static final String SHELTER_KEY = "item_key";
    @SuppressWarnings("CanBeFinal")
    private List<Shelter> mShelters;
    @SuppressWarnings("CanBeFinal")
    private Context mContext;

    public CustomShelterAdapter(Context context, List<Shelter> s) {
        this.mShelters = s;
        this.mContext = context;
    }

    @NonNull
    @Override
    public CustomShelterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View shelterView = inflater.inflate(R.layout.custom_shelter_row, parent, false);
        return new ViewHolder(shelterView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomShelterAdapter.ViewHolder holder, int position) {
        final Shelter shelter = mShelters.get(position);

        if (shelter != null) {
            holder.sName.setText(shelter.getName());
            holder.sCapacity.setText(String.format(Locale.getDefault(), "%d", shelter.getCapacity()));
            holder.sAddress.setText(shelter.getAddress());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, ShelterDescriptionActivity.class);
                    intent.putExtra(SHELTER_KEY, shelter);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mShelters.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @SuppressWarnings("CanBeFinal")
        TextView sName;
        @SuppressWarnings("CanBeFinal")
        TextView sCapacity;
        @SuppressWarnings("CanBeFinal")
        TextView sAddress;
        @SuppressWarnings("CanBeFinal")
        View mView;

        ViewHolder(View shelterView) {
            super(shelterView);

            sName = shelterView.findViewById(R.id.shelterName);
            sCapacity = shelterView.findViewById(R.id.shelterCapacity);
            sAddress = shelterView.findViewById(R.id.shelterAddress);
            mView = shelterView;
        }
    }
}
