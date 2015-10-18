package com.example.saboo.iplpredict;

import com.example.saboo.iplpredict.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class FullscreenActivity extends Activity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    private static final boolean TOGGLE_ON_CLICK = true;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    private SystemUiHider mSystemUiHider;
    private Spinner spinner1, spinner2;
    ArrayAdapter<String> adp1,adp2;
    List<String> l1,l2;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

//        final View contentView = findViewById(R.id.fullscreen_content);
        spinner1=(Spinner) findViewById(R.id.spinner);
        spinner2= (Spinner) findViewById(R.id.spinner2);

        l1=new ArrayList<String>();
        l1.add("Chennai Super Kings");
        l1.add("Delhi Daredevils");
        l1.add("Kings XI Punjab");
        l1.add("Kolkata Knight Riders");
        l1.add("Mumbai Indians");
        l1.add("Rajasthan Royals");
        l1.add("Royal Challengers Bangalore");
        l1.add("Sunrisers Hyderabad");
        adp2=new ArrayAdapter<String>(FullscreenActivity.this,
                R.layout.spinner,l1);
        spinner1.setAdapter(adp2);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub


                l2=new ArrayList<String>(l1);
                pos=spinner1.getSelectedItemPosition();
                l2.remove(pos);
                adp1=new ArrayAdapter<String>(FullscreenActivity.this,
                        R.layout.spinner,l2);
                spinner2.setAdapter(adp1);
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub


            }
            public void onNothingSelected(AdapterView<?> arg0) {
                                      }
                                           });
        // Set up an instance of SystemUiHider to control the system UI for
        // this activity.
       /* mSystemUiHider = SystemUiHider.getInstance(this, contentView, HIDER_FLAGS);
       mSystemUiHider.setup();
        mSystemUiHider
                .setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
                    // Cached values.
                    int mControlsHeight;
                    int mShortAnimTime;

                    @Override
                    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
                    public void onVisibilityChange(boolean visible) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                            // If the ViewPropertyAnimator API is available
                            // (Honeycomb MR2 and later), use it to animate the
                            // in-layout UI controls at the bottom of the
                            // screen.
                            if (mControlsHeight == 0) {
                                mControlsHeight = controlsView.getHeight();
                            }
                            if (mShortAnimTime == 0) {
                                mShortAnimTime = getResources().getInteger(
                                        android.R.integer.config_shortAnimTime);
                            }
                            controlsView.animate()
                                    .translationY(visible ? 0 : mControlsHeight)
                                    .setDuration(mShortAnimTime);
                        } else {
                            // If the ViewPropertyAnimator APIs aren't
                            // available, simply show or hide the in-layout UI
                            // controls.
                            controlsView.setVisibility(visible ? View.VISIBLE : View.GONE);
                        }


                    }
                });

        // Set up the user interaction to manually show or hide the system UI.

        */
        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.

    }


    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */

    Handler mHideHandler = new Handler();


    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */

    public void onClickDummyButton(View view) {
        String[] teams=new String[2];
        teams[0]=spinner1.getSelectedItem().toString();
        teams[1]=spinner2.getSelectedItem().toString();
        Intent intent = new Intent(this, PredictingActivity.class);
        intent.putExtra("teams",teams);
        startActivity(intent);

    }
    public void onClickSchedule(View view)
    {
        Intent i=new Intent(this,ScheduleActivity.class);
        startActivity(i);
    }
}
