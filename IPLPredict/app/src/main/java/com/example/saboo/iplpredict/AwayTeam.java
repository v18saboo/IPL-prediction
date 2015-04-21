package com.example.saboo.iplpredict;

/**
 * Created by saboo on 18-04-2015.
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
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

/**
 * Created by hp1 on 21-01-2015.
 */
public class AwayTeam extends Fragment {

    ArrayList<ListItem> listitem = new ArrayList<ListItem>();
    public ArrayList<String> order=new ArrayList<String>();

    ListAdapter boxAdapter;
    String name="";
    public AwayTeam(){}
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.teamchoosing,container,false);
        Bundle bundle = getArguments();
        name = bundle.getString("name");
        fillData();
        boxAdapter = new ListAdapter(getActivity(), listitem);
        final ListView lvMain = (ListView) v.findViewById(R.id.list);
        lvMain.setAdapter(boxAdapter);
        //lvMain.setClickable(true);
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /* ERROR IS HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                CheckBox cb= (CheckBox)arg1.findViewById(R.id.checkBox);
                String str=listitem.get(arg2).name;
                if(cb.isChecked()) {
                    order.remove(str);
                }
                else order.add(str);
                cb.setChecked(!cb.isChecked());

                //Toast.makeText(getActivity(),"Clicked " + str,Toast.LENGTH_LONG).show();
            }

        });
        return v;
    }



    void fillData() {


        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.add("teamname",name);

        client.get("http://0fdb8a0c.ngrok.io/DBMS/getTeam.php",params, new AsyncHttpResponseHandler() {

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
                        listitem.add(new ListItem(s, false));
                        Log.e("ADDED",s);
                    }
                    boxAdapter.notifyDataSetChanged();
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
    public void showResult(View v) {
        String result = "Selected Product are :";
        int totalAmount=0;
        for (ListItem p : boxAdapter.getBox()) {
            if (p.cbox){
                result += "\n" + p.name;
            }
        }
        //Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
    }

}