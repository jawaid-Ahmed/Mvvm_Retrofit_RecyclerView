package com.example.viewmodelretrofitrecyclerview.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.viewmodelretrofitrecyclerview.R;
import com.example.viewmodelretrofitrecyclerview.model.MovieModel;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {


    private Context context;
    private List<MovieModel> moviesList;

    ItemClickListener clickListener;

    public MoviesAdapter(Context context, List<MovieModel> moviesList,ItemClickListener clickListener) {
        this.context = context;
        this.moviesList = moviesList;
        this.clickListener=clickListener;
    }

    public void setMoviesList(List<MovieModel> moviesList){

        this.moviesList=moviesList;
        Log.d("TAG", "setMoviesList: "+ this.moviesList.size());

    }

    @Override
    public MoviesViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.single_row,parent,false);

        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        MovieModel movie=this.moviesList.get(position);
        holder.title.setText(movie.getTitle());

        Log.d("TAG", "onBindViewHolder: "+movie.getthumbnailUrl());
        Glide.with(context)
                .load(movie.getthumbnailUrl())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.thumbnailUrl);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.OnMovieClick(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(this.moviesList != null) {
            return this.moviesList.size();
        }
        return 0;

    }


    public class MoviesViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView thumbnailUrl;

        public MoviesViewHolder(View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.item_text);
            thumbnailUrl=itemView.findViewById(R.id.item_image);
        }
    }

    public interface ItemClickListener{
        void OnMovieClick(MovieModel model);
    }
}
