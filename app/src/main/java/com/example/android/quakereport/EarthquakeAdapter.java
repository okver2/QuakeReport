package com.example.android.quakereport;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Oleg on 7/11/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter (Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
              R.layout.list_item, parent, false);
        }

        final Earthquake currentEarthquake = getItem(position);

        TextView magTextView = (TextView) listItemView.findViewById(R.id.eqMag);
        magTextView.setText(formatMagnitude(currentEarthquake.getMagnitude()));

        TextView offsetTextView = (TextView) listItemView.findViewById(R.id.location_offset);
        offsetTextView.setText(offsetLocation(currentEarthquake.getEQplace()));

        TextView primaryTextView = (TextView) listItemView.findViewById(R.id.primary_location);
        primaryTextView.setText(primaryLocation(currentEarthquake.getEQplace()));

        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getEQtime());

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        dateTextView.setText(formatDate(dateObject));

        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);
        timeTextView.setText(formatTime(dateObject));

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(currentEarthquake.getEQurl()));
                v.getContext().startActivity(i);
                //Toast.makeText(v.getContext(), "click", Toast.LENGTH_SHORT).show();
            }
        });

        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String primaryLocation(String location) {
        return location.substring(location.lastIndexOf(' ', location.indexOf(','))+1);
    }

    private String offsetLocation(String location) {
        return location.substring(0, location.lastIndexOf(' ', location.indexOf(',')));
    }

    private String formatMagnitude(double mag) {
        DecimalFormat formatter = new DecimalFormat("0.0");
        return formatter.format(mag);
    }

    private int getMagnitudeColor(double mag) {
        int color = (int) mag;
        int resColor = R.color.magnitude1;

        switch(color) {
            case 0:
            case 1:
                resColor = R.color.magnitude1;
                break;
            case 2:
                resColor = R.color.magnitude2;
                break;
            case 3:
                resColor = R.color.magnitude3;
                break;
            case 4:
                resColor = R.color.magnitude4;
                break;
            case 5:
                resColor = R.color.magnitude5;
                break;
            case 6:
                resColor = R.color.magnitude6;
                break;
            case 7:
                resColor = R.color.magnitude7;
                break;
            case 8:
                resColor = R.color.magnitude8;
                break;
            case 9:
                resColor = R.color.magnitude9;
                break;
        }
        return ContextCompat.getColor(getContext(), resColor);
    }
}
