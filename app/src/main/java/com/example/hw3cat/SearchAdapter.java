package com.example.hw3cat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.CatViewHolder> implements Filterable {

    ArrayList<Cat> catsToAdapt;
    ArrayList<Cat> filterList;
    Context context;

    public SearchAdapter(ArrayList<Cat> catsList) {
        this.catsToAdapt = catsList;
        this.filterList = new ArrayList<>();
    }

    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat, parent, false);
        this.context = parent.getContext();
        CatViewHolder catViewHolder = new CatViewHolder(view);
        return catViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, final int position) {
        if (filterList.isEmpty()) {
            filterList = catsToAdapt;
        }

        final Cat catAtPosition = filterList.get(position);
        holder.name.setText(catAtPosition.getName());
        holder.cat_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CatDetailActivity.class);
                System.out.println(catAtPosition.getId());
                intent.putExtra("catID", catAtPosition.getId());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (filterList.isEmpty()) {
            return catsToAdapt.size();
        } else {
            return filterList.size();
        }

    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String searchString = charSequence.toString();
                if (searchString.isEmpty()) {
                    filterList = catsToAdapt;
                } else {
                    ArrayList<Cat> filteredList = new ArrayList<>();
                    for (Cat cat : catsToAdapt) {
                        if (cat.getName().toLowerCase().contains(searchString)) {
                            filteredList.add(cat);
                        }
                    }

                    filterList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filterList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filterList = (ArrayList<Cat>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class CatViewHolder extends RecyclerView.ViewHolder {
        LinearLayout cat_layout;
        TextView name;

        public CatViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            cat_layout = view.findViewById(R.id.cat_layout);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + name.getText() + "'";
        }

    }
}