package com.example.saboo.iplpredict;

import java.util.Date;

/**
 * Created by saboo on 21-04-2015.
 */
public class ScheduleListItem {
    String date; int match_no;
    String team1="",team2="";
    public ScheduleListItem(){}
    public ScheduleListItem(String d,String t1, String t2,int n)
    {
        match_no=n;
        date=d;
        team1=t1;
        team2=t2;
    }
}
