package com.teste.testegfx.ui.main.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.teste.testegfx.R;
import com.teste.testegfx.data.model.Post;

public class PostViewHolder extends RecyclerView.ViewHolder {

    private final TextView textId;
    private final TextView textTitle;
    private final TextView textBody;

    public PostViewHolder(View itemView) {
        super(itemView);

        textId = itemView.findViewById(R.id.textId);
        textTitle = itemView.findViewById(R.id.textTitle);
        textBody = itemView.findViewById(R.id.textBody);
    }

    public void bind(Post post) {
        textId.setText("ID: " + post.getId());
        textTitle.setText(post.getTitle());
        textBody.setText(post.getBody());
    }
}
