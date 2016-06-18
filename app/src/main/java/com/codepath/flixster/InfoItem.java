package com.codepath.flixster;

import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

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

        String vote_average = getIntent().getStringExtra("vote_average");
        //RatingBar rbVote_Av = (RatingBar) findViewById(R.id.rbVote_Av);
        //rbVote_Av.setIsIndicator(true);
        //rbVote_Av.setRating((float)vote_average);

        String release_date = getIntent().getStringExtra("release_date");
        String popularity = getIntent().getStringExtra("popularity");
        TextView tvPopularity = (TextView) findViewById(R.id.tvPopularity);
        tvPopularity.append("Release Date: " + release_date + ". Rating: " + vote_average + ". Popularity: " + popularity);

    }

    public void onSubmit(View v) {
        this.finish();
    }

}
