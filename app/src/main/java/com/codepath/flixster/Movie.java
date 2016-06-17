package com.codepath.flixster;

import android.content.res.Configuration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by liliachang on 6/15/16.
 */
public class Movie {
    public String title;
    public String posterPath;
    public String backdropPath;
    public float vote_average;
    public String overview;

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }
    public float getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public Movie(JSONObject jsonObject) throws JSONException {
        this.posterPath = jsonObject.getString("poster_path");
        this.backdropPath = jsonObject.getString("backdrop_path");
        this.title = jsonObject.getString("title");
        this.vote_average = jsonObject.getInt("vote_average");
        this.overview = jsonObject.getString("overview");
    }

    public static ArrayList<Movie> fromJSONArray(JSONArray array) {
        ArrayList<Movie> results = new ArrayList<>();
        for (int n = 0; n < array.length(); n++) {
            try {
                results.add(new Movie(array.getJSONObject(n)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    /*@Override
    public String toString() {
        return title + " - " + vote_average;

    }*/
}
