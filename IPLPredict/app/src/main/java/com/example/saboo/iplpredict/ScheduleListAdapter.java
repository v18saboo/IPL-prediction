package com.example.saboo.iplpredict;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

    public View getView(int position, View convertView, ViewGroup parent) {
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

        holder.team1.setText(schedule.get(position).team1);
        holder.team2.setText(schedule.get(position).team2);
        holder.match_no.setText("Match " + schedule.get(position).match_no);
        holder.date.setText(schedule.get(position).date);

        return convertView;
    }

    static class ViewHolder {
        TextView team1;
        TextView team2;
        TextView match_no;
        TextView date;
    }
}