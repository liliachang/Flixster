package com.codepath.flixster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MoviesActivity extends AppCompatActivity {
    private final int REQUEST_CODE = 20;
    ArrayList<Movie> movies;
    MoviesAdapter adapter;
    ListView lvMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        // Get ListView that we want to populate
        lvMovies = (ListView)findViewById(R.id.lvMovies);
        movies = new ArrayList<>();
        // Create an ArrayAdapter
        adapter = new MoviesAdapter(this, movies);

        // Get actual movies
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJsonResults = null;
                try {
                    movieJsonResults = response.getJSONArray("results");
                    movies.addAll(Movie.fromJSONArray(movieJsonResults));
                    adapter.notifyDataSetChanged();
                    Log.d("DEBUG", movies.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });

        // Associate the adapter with the ListView
        if (lvMovies != null) {
            lvMovies.setAdapter(adapter);
        }

        setupListViewListener();
    }

    private void setupListViewListener() {
        lvMovies.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        launchInfoView(position);
                    }
                }
        );
    }

    private void launchInfoView(int position) {
        Intent i = new Intent(MoviesActivity.this, InfoItem.class);
        Movie movie = movies.get(position);
        i.putExtra("title", movie.getTitle());
        i.putExtra("overview", movie.getOverview());
        i.putExtra("backdrop", movie.getBackdropPath());
        i.putExtra("vote_average", movie.getVote_average());
        i.putExtra("release_date", movie.release_date);
        startActivityForResult(i, REQUEST_CODE);
    }
}
