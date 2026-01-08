package com.teste.testegfx.ui.main.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teste.testegfx.R;
import com.teste.testegfx.data.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter
        extends RecyclerView.Adapter<PostViewHolder> {

    private final List<Post> items = new ArrayList<>();

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {
        LayoutInflater inflater =
                LayoutInflater.from(parent.getContext());

        return new PostViewHolder(
                inflater.inflate(
                        R.layout.item_post,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(
            @NonNull PostViewHolder holder,
            int position
    ) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void submitList(List<Post> list) {
        items.clear();

        if (list != null) {
            items.addAll(list);
        }

        notifyDataSetChanged();
    }
}
