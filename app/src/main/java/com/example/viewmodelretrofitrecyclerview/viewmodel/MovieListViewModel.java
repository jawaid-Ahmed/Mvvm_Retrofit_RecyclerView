package com.example.viewmodelretrofitrecyclerview.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.viewmodelretrofitrecyclerview.model.MovieModel;
import com.example.viewmodelretrofitrecyclerview.network.ApiService;
import com.example.viewmodelretrofitrecyclerview.network.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListViewModel extends ViewModel {

    MutableLiveData<List<MovieModel>> liveData;

    //create mutablelivedata object and initialize in constructor
    public MovieListViewModel() {
        this.liveData = new MutableLiveData<>();
    }

    //method to return observable data object
    public MutableLiveData<List<MovieModel>> getMoviesObservable(){
        return liveData;
    }

    /*this is api call to make retrofit api call*/
    public void makeApiCall(){
        ApiService apiService= RetroInstance.getRetrofitClient().create(ApiService.class);
        Call<List<MovieModel>> call=apiService.getMovies();

        call.enqueue(new Callback<List<MovieModel>>() {
            @Override
            public void onResponse(Call<List<MovieModel>> call, Response<List<MovieModel>> response) {
                liveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<MovieModel>> call, Throwable t) {
                liveData.postValue(null);
            }
        });

    }

}
