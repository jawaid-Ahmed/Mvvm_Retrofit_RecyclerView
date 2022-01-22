package com.example.viewmodelretrofitrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.viewmodelretrofitrecyclerview.adapter.MoviesAdapter;
import com.example.viewmodelretrofitrecyclerview.model.MovieModel;
import com.example.viewmodelretrofitrecyclerview.viewmodel.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.ItemClickListener{

    private static final String TAG = "TAG";
    RecyclerView recyclerView;
    MoviesAdapter adapter;
    List<MovieModel> moviesList;
    MovieListViewModel viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.d(TAG, "onCreate: ");
        recyclerView=findViewById(R.id.mainRecyclerview);

        moviesList=new ArrayList<>();
        adapter=new MoviesAdapter(this, moviesList,this);
       // LinearLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        Log.d(TAG, "onCreate: 2");

        viewmodel=ViewModelProviders.of(this).get(MovieListViewModel.class);
        viewmodel.getMoviesObservable().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if(movieModels!=null){
                    Log.d(TAG, "onChanged: "+movieModels.size());
                    moviesList=movieModels;
                    adapter.setMoviesList(movieModels);
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(MainActivity.this, " No Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewmodel.makeApiCall();
        Log.d(TAG, "onCreate: 3");

    }

    @Override
    public void OnMovieClick(MovieModel model) {
        Toast.makeText(MainActivity.this, "Clicked "+model.getTitle(), Toast.LENGTH_SHORT).show();
    }
}