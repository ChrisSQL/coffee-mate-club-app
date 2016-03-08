package coffeemate.chris.app.coffeemateclub.app;

import android.content.Intent;
import android.os.Bundle;

import coffeemate.chris.app.coffeemateclub.R;
import coffeemate.chris.app.coffeemateclub.adapter.CustomListAdapter;
import coffeemate.chris.app.coffeemateclub.model.Coffee;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

public class MainActivity extends Activity {
    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();
    private SwipeRefreshLayout swipeContainer;

    // Coffees json url
    private static final String url = "http://www.coffeemate.club/api/coffees";
    private ProgressDialog pDialog;
    private List<Coffee> coffeeList = new ArrayList<Coffee>();
    private ListView listView;
    private CustomListAdapter adapter;
    private Coffee coffee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                fetchTimelineAsync(0);
            }
        });

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, coffeeList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent detailIntent = new Intent(MainActivity.this, MainActivityList.class);
                detailIntent.putExtra("id", coffeeList.get(position).getId());
                startActivity(detailIntent);


            }
        });

        // changing action bar color
//        getActionBar().setBackgroundDrawable(
//                new ColorDrawable(Color.parseColor("#1b1b1b")));

        // Creating volley request obj
        JsonArrayRequest movieReq = new JsonArrayRequest(url,
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    Log.d(TAG, response.toString());
                    hidePDialog();



                    // Parsing json
                    for (int i = 0; i < response.length(); i++) {

                        if (response.length() == 0){

                            fetchTimelineAsync(1);
                        }

                        try {

                            JSONObject obj = response.getJSONObject(i);
                            coffee = new Coffee();
                            coffee.setId(obj.getString("_id"));
                            coffee.setTitle(obj.getString("title"));
                            coffee.setMarketingtext(obj.getString("marketingtext"));
                            coffee.setBrand(obj.getString("brand"));
                            coffee.setThumbnailUrl(obj.getString("urlimage"));
                            coffee.setVotes(obj.getInt("votes"));
                            coffee.setPrice(obj.getInt("price"));

                            // adding coffee to movies array
                            coffeeList.add(coffee);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    // notifying list adapter about data changes
                    // so that it renders the list view with updated data
                    adapter.notifyDataSetChanged();
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            VolleyLog.d(TAG, "Error: " + error.getMessage());
            hidePDialog();

        }
    });

    // Adding request to request queue
    AppController.getInstance().addToRequestQueue(movieReq);
}

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void fetchTimelineAsync(int page) {

        JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();



                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {

                            if (response.length() == 0){

                                fetchTimelineAsync(1);
                            }

                            try {

                                JSONObject obj = response.getJSONObject(i);
                                coffee = new Coffee();
                                coffee.setId(obj.getString("_id"));
                                coffee.setTitle(obj.getString("title"));
                                coffee.setMarketingtext(obj.getString("marketingtext"));
                                coffee.setBrand(obj.getString("brand"));
                                coffee.setThumbnailUrl(obj.getString("urlimage"));
                                coffee.setVotes(obj.getInt("votes"));
                                coffee.setPrice(obj.getInt("price"));

                                // adding coffee to movies array
                                coffeeList.add(coffee);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                        swipeContainer.setRefreshing(false);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);

    }


}
