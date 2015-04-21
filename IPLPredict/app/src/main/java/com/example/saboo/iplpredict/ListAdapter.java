package com.example.saboo.iplpredict;

/**
 * Created by saboo on 19-04-2015.
 */
import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<ListItem> objects;
    ArrayList<String> order=new ArrayList<String>();
    ListAdapter(Context context, ArrayList<ListItem> names) {
        ctx = context;
        objects = names;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.listitem, parent, false);
        }

         ListItem p = getListItem(position);

        ((TextView) view.findViewById(R.id.name)).setText(p.name);
        //((TextView) view.findViewById(R.id.tvPrice)).setText(p.price + "");
        //((ImageView) view.findViewById(R.id.ivImage)).setImageResource(p.image);

        CheckBox cbBuy = (CheckBox) view.findViewById(R.id.checkBox);

        //cbBuy.setOnCheckedChangeListener(myCheckChangeList);
        //cbBuy.setTag(position);
        //cbBuy.setChecked(p.cbox);

        return view;
    }

    ListItem getListItem(int position) {
        return ((ListItem) getItem(position));
    }

    /*Checks which list items are checked and adds them into a list.*/

    ArrayList<ListItem> getBox() {
        ArrayList<ListItem> box = new ArrayList<ListItem>();
        for (ListItem p : objects) {
            if (p.cbox)
                box.add(p);
        }
        return box;
    }
    ArrayList<String> getOrder() {
        return order;
    }

    OnCheckedChangeListener myCheckChangeList = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            getListItem((Integer) buttonView.getTag()).cbox = isChecked;
        }
    };
}