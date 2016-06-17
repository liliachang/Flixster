package com.codepath.flixster;

import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class InfoItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_item);
        getSupportActionBar().hide();
        String title = getIntent().getStringExtra("title");
        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.append(title);

        String overview = getIntent().getStringExtra("overview");
        TextView tvOverview = (TextView) findViewById(R.id.tvOverview);
        tvOverview.append(overview);

        /*backdrop = getIntent().getStringExtra("backdrop");
        ImageView ivPoster = (ImageView) findViewById(R.id.ivPoster);*/

        float vote_average = getIntent().getIntExtra("vote_average", -1);
        RatingBar rbVote_Av = (RatingBar) findViewById(R.id.rbVote_Av);
        rbVote_Av.setIsIndicator(true);

        rbVote_Av.setRating(vote_average);
    }
}
