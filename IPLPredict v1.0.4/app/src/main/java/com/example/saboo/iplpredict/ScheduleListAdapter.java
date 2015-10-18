package com.example.saboo.iplpredict;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by saboo on 21-04-2015.
 */
public class ScheduleListAdapter extends BaseAdapter {
    private static ArrayList<ScheduleListItem> schedule;

    private LayoutInflater mInflater;

    public ScheduleListAdapter(Context context, ArrayList<ScheduleListItem> results) {
        schedule = results;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return schedule.size();
    }

    public Object getItem(int position) {
        return schedule.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, final ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.schedulelistitem, null);
            holder = new ViewHolder();
            holder.team1 = (TextView) convertView.findViewById(R.id.team1);
            holder.team2 = (TextView) convertView.findViewById(R.id.team2);
            holder.match_no = (TextView) convertView.findViewById(R.id.match_no);
            holder.date = (TextView) convertView.findViewById(R.id.date);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Button predict=(Button)convertView.findViewById(R.id.predict);
        holder.team1.setText(schedule.get(position).team1);
        holder.team2.setText(schedule.get(position).team2);
        holder.match_no.setText("Match " + schedule.get(position).match_no);
        holder.date.setText(schedule.get(position).date);
        final String[] t={holder.team1.getText().toString(),holder.team2.getText().toString()};
        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(parent.getContext(),PredictingActivity.class);

                i.putExtra("teams",t);
                parent.getContext().startActivity(i);
            }
        });

        return convertView;
    }

    static class ViewHolder {
        TextView team1;
        TextView team2;
        TextView match_no;
        TextView date;
    }
}