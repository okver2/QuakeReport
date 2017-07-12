package com.example.android.quakereport;

/**
 * Created by Oleg on 7/11/2017.
 */

public class Earthquake {
    private double mEQmag;
    private String mEQplace;
    private long mEQtime;
    private String mEQurl;

    public Earthquake(double eqMag, String eqPlace, long eqTime, String url)
    {
        mEQmag = eqMag;
        mEQplace = eqPlace;
        mEQtime = eqTime;
        mEQurl = url;
    }

    public double getMagnitude() {
        return mEQmag;
    }

    public String getEQplace() {
        return mEQplace;
    }

    public long getEQtime() {
        //DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        //Date date = new Date();
        //return dateFormat.format(date);

        return mEQtime;
    }

    public String getEQurl() {
        return mEQurl;
    }
}
