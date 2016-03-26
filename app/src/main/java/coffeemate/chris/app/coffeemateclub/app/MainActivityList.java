package coffeemate.chris.app.coffeemateclub.app;

import android.content.Intent;
import android.os.Bundle;

import coffeemate.chris.app.coffeemateclub.R;
import coffeemate.chris.app.coffeemateclub.adapter.CustomListAdapterList;
import coffeemate.chris.app.coffeemateclub.model.Coffee;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
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

public class MainActivityList extends Activity {
    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    // Coffees json url
    private static final String url = "http://www.coffeemate.club/api/coffees";
    private ProgressDialog pDialog;
    private List<Coffee> coffeeList = new ArrayList<Coffee>();
    private ListView listView;
    private CustomListAdapterList adapter;
    private Coffee coffee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_detail);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");


        listView = (ListView) findViewById(R.id.list1);
        adapter = new CustomListAdapterList(this, coffeeList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        // Creating volley request obj
        JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();


                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {



                            try {

                                JSONObject obj = response.getJSONObject(i);

                                if (obj.getString("_id").equals(id)) {

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

                                }

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

}
