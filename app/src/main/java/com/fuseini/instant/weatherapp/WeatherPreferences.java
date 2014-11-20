package com.fuseini.instant.weatherapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.text.InputType;
import android.widget.EditText;

import com.fuseini.instant.weatherapp.adapter.WeatherObject;

public class WeatherPreferences {

    // Private preferences values
    private static final String PREF_LOCATION = "location";
    private static final String PREF_DEFAULT_LOCATION = "accra,gh";
    private static final String PREF_UNITS = "units";
    private static final int PREF_DEFAULT_UNITS = WeatherObject.METRIC;
    // Private preferences member
    private static SharedPreferences prefs;

    // Constructor
    public WeatherPreferences(Activity activity) {
        // Get preferences in private mode
        prefs = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    // Location preference dialog
    public static void changeLocation(final Activity activity) {
        final WeatherPreferences wp = new WeatherPreferences(activity);

        // Create an Alert Dialog Builder and set title
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(com.fuseini.instant.weatherapp.R.string.dialog_location_title);
        builder.setCancelable(false);

        // Crate an EditText view inside the dialog
        final EditText inputField = new EditText(activity);
        inputField.setInputType(InputType.TYPE_CLASS_TEXT);
        inputField.setHint(com.fuseini.instant.weatherapp.R.string.dialog_location_hint);
        builder.setView(inputField);

        // Set positive button
        builder.setPositiveButton(com.fuseini.instant.weatherapp.R.string.dialog_positive_button, new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Change location shared preferences
                wp.setUserLocation(inputField.getText().toString());
                // Recreate Activity after changes
                activity.recreate();
            }
        });

        // Set negative button
        builder.setNegativeButton(com.fuseini.instant.weatherapp.R.string.dialog_negative_button, new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Set previous user location
                wp.setUserLocation(wp.getUserLocation());
            }
        });

        // Show dialog
        builder.show();
    }

    // Units preference dialog
    public static void changeUnits(final Activity activity) {
        final WeatherPreferences wp = new WeatherPreferences(activity);

        // Create an Alert Dialog Builder and set title
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(com.fuseini.instant.weatherapp.R.string.dialog_units_title);
        builder.setCancelable(false);

        // Crate a list from XML array
        builder.setItems(com.fuseini.instant.weatherapp.R.array.weather_unit_system, new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // which = selected item ID
                if (which == 0) {
                    wp.setUserUnits(WeatherObject.METRIC);
                } else if (which == 1) {
                    wp.setUserUnits(WeatherObject.IMPERIAL);
                } else {
                    wp.setUserUnits(WeatherObject.DEFAULT);
                }
                // Recreate parent activity after changes
                activity.recreate();
            }
        });

        // Show dialog
        builder.show();
    }

    // Get location from preferences
    String getUserLocation() {
        return prefs.getString(PREF_LOCATION, PREF_DEFAULT_LOCATION);
    }

    // Set location to preferences
    void setUserLocation(String location) {
        prefs.edit().putString(PREF_LOCATION, location).commit();
    }

    // Get units from preferences
    int getUserUnits() {
        return prefs.getInt(PREF_UNITS, PREF_DEFAULT_UNITS);
    }

    // Set location to preferences
    void setUserUnits(int units) {
        prefs.edit().putInt(PREF_UNITS, units).commit();
    }

}
