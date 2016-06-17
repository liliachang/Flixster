package com.codepath.flixster;

import android.content.Context;
import android.content.res.Configuration;
import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by liliachang on 6/15/16.
 */
public class MoviesAdapter extends ArrayAdapter<Movie> {
    // View lookup cache
    private static class ViewHolder {
        ImageView ivPoster;
        TextView tvTitle;
        TextView tvOverview;
    }

    public MoviesAdapter(Context context, ArrayList<Movie> movies) {
        super(context, R.layout.item_movie, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Movie movie = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; //view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
            viewHolder.ivPoster = (ImageView) convertView.findViewById(R.id.ivPoster);
            viewHolder.ivPoster.setImageResource(0);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.tvOverview.setText(movie.overview);
        viewHolder.tvTitle.setText(movie.title);
        Log.d("MoviesAdapter", "Position: " + position);
        String imageUri;
        //sets pic to backdrop or poster
        boolean isLandscape = getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (isLandscape) {
            //configure ivImage to have backdrop image URL
            imageUri = movie.getBackdropPath();
            Picasso.with(getContext()).load(imageUri)
                    .transform(new RoundedCornersTransformation(10, 10))
                    .resize(1600,1000).centerCrop()
                    .placeholder(R.drawable.reel)
                    .error(R.drawable.reel)
                    .into(viewHolder.ivPoster);
        } else {
            //configure ivImage to have poster image URL
            imageUri = movie.getPosterPath();
            Picasso.with(getContext()).load(imageUri)
                    .transform(new RoundedCornersTransformation(10, 10))
                    .resize(500,800).centerCrop()
                    .placeholder(R.drawable.reel)
                    .error(R.drawable.reel)
                    .into(viewHolder.ivPoster);
        }

        //Picasso.with(getContext()).load(imageUri).into(viewHolder.ivPoster);
        //Picasso.with(getContext()).load(imageUri).transform(new RoundedCornersTransformation(10, 10)).into(viewHolder.ivPoster);
        /*Picasso.with(getContext()).load(imageUri)
                .transform(new RoundedCornersTransformation(10, 10))
                .resize(500,800).centerCrop()
                .placeholder(R.drawable.reel)
                .error(R.drawable.reel)
                .into(viewHolder.ivPoster);*/
        // Return the completed view to render on screen
        return convertView;
    }
}
