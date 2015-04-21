package com.example.saboo.iplpredict;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class TossActivity extends ActionBarActivity {

    Toolbar toolbar;
    String Titles[]=new String[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toss);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.inflateMenu(R.menu.actions_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Batting First");
        Titles = getIntent().getExtras().getStringArray("teams");
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
        //startActivity(intent);

    }

    public void onClickButtonAway(View view) {

        //Intent intent = new Intent(this, PredictingActivity.class);
        Toast.makeText(this,Titles[1] + " will bat first",Toast.LENGTH_SHORT).show();
        //startActivity(intent);

    }
}
