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

        /*backdrop = getIntent().getStringExtra("backdrop");
        ImageView ivPoster = (ImageView) findViewById(R.id.ivPoster);*/

        double vote_average = getIntent().getIntExtra("vote_average", -1);
        RatingBar rbVote_Av = (RatingBar) findViewById(R.id.rbVote_Av);
        rbVote_Av.setIsIndicator(true);
        //rbVote_Av.setRating(4);
        rbVote_Av.setRating((float)vote_average);

        String release_date = getIntent().getStringExtra("release_date");
        int popularity = (int) (getIntent().getDoubleExtra("popularity", -1));
        TextView tvPopularity = (TextView) findViewById(R.id.tvPopularity);
        tvPopularity.append("Release Date: " + release_date + ". Popularity: " + popularity);

    }

    public void onSubmit(View v) {
        this.finish();
    }

}
