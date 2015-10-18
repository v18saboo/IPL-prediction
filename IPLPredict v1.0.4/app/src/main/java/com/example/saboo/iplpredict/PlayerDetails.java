package com.example.saboo.iplpredict;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class PlayerDetails extends ActionBarActivity {
    Toolbar toolbar;
    String name="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_details);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        name=getIntent().getExtras().getString("name");
        toolbar.inflateMenu(R.menu.actions_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(name);

        getBowlerData();


    }

    public void getBowlerData()
    {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.add("a[]",name);

        client.get("http://0fdb8a0c.ngrok.io/DBMS/getPlayerDetails.php",params, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                Log.e("STARTED", "DAWG");
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                Log.e("SUCCESS","DAWG");
                try {
                    JSONArray array = new JSONArray(new String(response));
                    for(int i=0;i<array.length();++i) {
                        JSONObject object = array.getJSONObject(i);
                        String s = object.getString("Name");
                        String n = object.getString("Nationality");
                        Log.e("ADDED",s);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("SORRY BRUH",e.toString());
                }
                // called when response HTTP status is "200 OK"
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.e("FAILURE",new String(errorResponse));
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}
