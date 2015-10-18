package com.example.saboo.iplpredict;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CancellationSignal;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class TossActivity extends ActionBarActivity {

    Toolbar toolbar;
    String Titles[]=new String[2];
    ProgressDialog PD;
    String Hometoss="TRUE";
    ArrayList<String> homeplayers=new ArrayList<String>();
    ArrayList<String> awayplayers=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toss);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.inflateMenu(R.menu.actions_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Batting First");
        Titles = getIntent().getExtras().getStringArray("teamnames");
        homeplayers = getIntent().getExtras().getStringArrayList("team1");
        awayplayers=getIntent().getExtras().getStringArrayList("team2");
        Button home=(Button)findViewById(R.id.button);
        home.setText(Titles[0]);
        Button away=(Button)findViewById(R.id.button2);
        away.setText(Titles[1]);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toss, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }
    public void onClickButtonHome(View view) {
        
        //Intent intent = new Intent(this, PredictingActivity.class);
        Toast.makeText(this,Titles[0] + " will bat first",Toast.LENGTH_SHORT).show();
        Hometoss="TRUE";
        //startActivity(intent);
        serverCall();

    }

    public void onClickButtonAway(View view) {

        //Intent intent = new Intent(this, PredictingActivity.class);
        Toast.makeText(this,Titles[1] + " will bat first",Toast.LENGTH_SHORT).show();
        //startActivity(intent);
        Hometoss="FALSE";
        serverCall();

    }
    void serverCall(){

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        int i;
        for(i=0;i<11;i++)
        {
            params.add("a[]",homeplayers.get(i));
            Log.e("Sending player",homeplayers.get(i));
        }
        for(i=0;i<11;i++)
        {
            params.add("b[]",awayplayers.get(i));
            Log.e("Sending player",awayplayers.get(i));

        }
        params.add("toss",Hometoss);
        client.get("http://0fdb8a0c.ngrok.io/DBMS/getPlayerDetails.php",params, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                PD = new ProgressDialog(TossActivity.this);
                PD.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                PD.setInverseBackgroundForced(true);
                PD.setTitle("Please Wait..");
                PD.setMessage("Predicting...");
                PD.setCancelable(true);
                PD.setOnCancelListener(new DialogInterface.OnCancelListener() {


                    public void onCancel(DialogInterface dialog) {
                        // TODO Auto-generated method stub
                        // Do something...
                        Toast.makeText(TossActivity.this,"You have cancelled the prediction.",Toast.LENGTH_LONG).show();
                        Intent i=new Intent(TossActivity.this,FullscreenActivity.class);
                        startActivity(i);
                        
                    }
                });
                PD.show();
                Log.e("STARTED", "DAWG");
                // called before request is started
            }


            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                Log.e("SUCCESS","DAWG");
                String s="";
                try {
                    JSONArray array = new JSONArray(new String(response));
                    Log.e("Array Length",array.length()+"");
                    JSONArray array1 = new JSONArray(new String(response));
                    Log.e("Array1 Length",array1.length()+"");
                    for(int i=0;i<array.length();++i) {
                        JSONObject object = array.getJSONObject(i);
                        try {
                            s = object.getString("Runs");
                            Log.e("WINNER CHICKEN DINNER -",s);
                        }
                        catch(JSONException e)
                        {
                            s=object.getString("Wickets");
                            Log.e("Wickets",s);
                        }

                        }

                    PD.dismiss();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("SORRY BRUH",e.toString());
                    PD.dismiss();
                    Toast.makeText(TossActivity.this,"Prediction Failure.\n Match is fixed.\n Please contact BCCI for winner",Toast.LENGTH_LONG).show();

                }
                // called when response HTTP status is "200 OK"
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.e("FAILURE",new String(errorResponse));
                PD.dismiss();
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });




    }
}
