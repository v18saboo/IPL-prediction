package com.example.saboo.iplpredict;

import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ScheduleActivity extends ActionBarActivity {


    Toolbar toolbar;
    ScheduleListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.inflateMenu(R.menu.actions_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setTitle("Schedule");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ArrayList<ScheduleListItem> schedule = fillJson();
        adapter = new ScheduleListAdapter(this, schedule);
        final ListView lv1 = (ListView) findViewById(R.id.Schedulelist);
        lv1.setAdapter(adapter);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv1.getItemAtPosition(position);
                ScheduleListItem fullObject = (ScheduleListItem) o;
                String s = "You have chosen: " + " " + fullObject.team1 + "vs" + fullObject.team2 + "";
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_schedule, menu);
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

    private ArrayList<ScheduleListItem> GetSearchResults() {
        ArrayList<ScheduleListItem> results = new ArrayList<ScheduleListItem>();

        ScheduleListItem sr1 = new ScheduleListItem();
        sr1.team1 = "Delhi Daredevils";
        sr1.team2 = "Chennai Super Kings";
        sr1.match_no = 1;
        sr1.date = "21April  2015";
        results.add(sr1);
        ScheduleListItem sr2 = new ScheduleListItem();

        sr2.team1 = "Mumbai Indians";
        sr2.team2 = "Rajasthan Royals";
        sr2.match_no = 2;
        sr2.date = "22 April 2015";
        results.add(sr2);
        ScheduleListItem sr3 = new ScheduleListItem();

        sr3.team1 = "Royal Challengers Bangalore";
        sr3.team2 = "Sunrisers Hyderabad";
        sr3.match_no = 3;
        sr3.date = "23 April 2015";
        results.add(sr3);
        ScheduleListItem sr4 = new ScheduleListItem();

        sr4.team1 = "Kings XI Punjab";
        sr4.team2 = "Kolkata Knight Riders";
        sr4.match_no = 4;
        sr4.date = "24 April 2015";
        results.add(sr4);

        return results;
    }

    private ArrayList<ScheduleListItem> fillJson() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        final ArrayList<ScheduleListItem> results = new ArrayList<ScheduleListItem>();


        client.get("http://0fdb8a0c.ngrok.io/DBMS/getSchedule.php", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                Log.e("STARTED", "DAWG");
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                Log.e("SUCCESS", "DAWG");
                try {
                    JSONArray array = new JSONArray(new String(response));
                    for (int i = 0; i < array.length(); ++i) {
                        JSONObject object = array.getJSONObject(i);
                        ScheduleListItem s = new ScheduleListItem();
                        String date = object.getString("Date");
                        String team1 = object.getString("Home");
                        String team2 = object.getString("Away");
                        results.add(new ScheduleListItem(date,team1,team2,i+1));
                        Log.e("ADDED", team1 + team2);
                    }
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("SORRY BRUH", e.toString());
                }
                // called when response HTTP status is "200 OK"
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.e("FAILURE", new String(errorResponse));
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }

        });

        return results;

    }
}
