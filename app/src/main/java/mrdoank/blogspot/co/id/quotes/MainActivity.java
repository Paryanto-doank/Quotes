package mrdoank.blogspot.co.id.quotes;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mrdoank.blogspot.co.id.quotes.model.Quote;
import mrdoank.blogspot.co.id.quotes.utility.RecyclerTouchListener;
import mrdoank.blogspot.co.id.quotes.utility.SessionManager;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView rvContent;
    List<Quote> quotes;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvContent = findViewById(R.id.rv_content);
        quotes = new ArrayList<>();
        adapter = new Adapter(quotes);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.
                VERTICAL, false);
        rvContent.setLayoutManager(layoutManager);
        rvContent.setAdapter(adapter);

        rvContent.addOnItemTouchListener(new RecyclerTouchListener(this, rvContent, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Quote quote = quotes.get(position);
                        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                        intent.putExtra("key-quote", quote);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongClick(View view, int position) {
                    }
                }));

        String url = "http://quotesondesign.com/wp-json/posts?filter[orderby]=rand&filter[posts_per_page]=20";

        try {
            String result = new FetchData().execute(url).get();
            JSONArray jsonObject = new JSONArray(result);
            for (int i = 0; i < jsonObject.length(); i++) {
                JSONObject object = jsonObject.getJSONObject(i);

                Quote quote = new Quote();
                quote.setID(object.getString("ID"));
                quote.setTitle(object.getString("title"));
                quote.setContent(removeStringJunk(object.getString("content")));
                quote.setLink(object.getString("link"));
                quotes.add(quote);
                Log.d(TAG, "onCreate: " + quote.toString());
            }
            adapter.addAll(quotes);
            Log.d(TAG, "onCreate result : " + result);
        } catch (InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }
    }

    public class FetchData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            String result;
            String inputLine;

            try {
                URL myUrl = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();

                connection.setRequestMethod("GET");
                connection.setReadTimeout(15000);
                connection.setConnectTimeout(15000);
                connection.connect();

                InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();

                while ((inputLine = reader.readLine()) != null) {
                    stringBuilder.append(inputLine);
                }
                result = stringBuilder.toString();

            } catch (IOException e) {
                result = null;
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }

    /** Removes HTML elements and trailing whitespace. */
    private static String removeStringJunk( String string )
    {
        String result = Html.fromHtml( string ).toString();
        Matcher matcher = Pattern.compile( "\n" ).matcher( result );
        result = matcher.replaceAll( "" );
        result = result.trim();
        return result;
    }

    public void logout(View view) {
        finish();
        SessionManager.getInstance().clear();
    }
}
