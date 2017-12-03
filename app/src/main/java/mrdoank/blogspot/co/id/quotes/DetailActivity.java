package mrdoank.blogspot.co.id.quotes;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import mrdoank.blogspot.co.id.quotes.model.Quote;

/**
 * Created by root on 02/12/17.
 */

public class DetailActivity extends AppCompatActivity {
    private final String TAG = DetailActivity.class.getSimpleName();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tvContentDetail = findViewById(R.id.tv_content_detail);
        TextView tvTitle = findViewById(R.id.tv_title);
        TextView tvLink = findViewById(R.id.tv_link);
        TextView tvId = findViewById(R.id.tv_id);


        Quote quote = getIntent().getParcelableExtra("key-quote");

        tvContentDetail.setText(String.valueOf(quote.getContent()));
        tvTitle.setText(String.valueOf(quote.getTitle()));
        tvLink.setText(String.valueOf(quote.getLink()));
        tvId.setText(String.valueOf(quote.getID()));

        setTitle("Detail Quote");
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    public void openOtherIntent(View view) {
        Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(settingsIntent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
