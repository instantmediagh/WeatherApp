package com.fuseini.instant.weatherapp;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fuseini.instant.weatherapp.adapter.WeatherObject;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TomorrowFragment extends Fragment {
	
	// Tomorrow Views
	private static TextView tomorrowName, tomorrowIcon, tomorrowTemperature, tomorrowDescription;
	private static TextView tomorrowForecastOneTemp, tomorrowForecastTwoTemp, tomorrowForecastThreeTemp, tomorrowForecastFourTemp;
	private static TextView tomorrowForecastOneDay, tomorrowForecastTwoDay, tomorrowForecastThreeDay, tomorrowForecastFourDay;
	private static TextView tomorrowForecastOneIcon, tomorrowForecastTwoIcon, tomorrowForecastThreeIcon, tomorrowForecastFourIcon;
	private static TextView tomorrowCopyright;
	
	private static RelativeLayout tomorrowMain, tomorrowNoLayout;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// Inflate fragment XML
		View rootView = inflater.inflate(R.layout.fragment_tomorrow, container, false);
		
		// Initialize text views
		initializeViews(rootView);
		
		// Return view
		return rootView;
	}

	// Initialize text views
	private void initializeViews(View rootView) {
		// Relative layouts
		tomorrowMain = (RelativeLayout) rootView.findViewById(com.fuseini.instant.weatherapp.R.id.tomorrow_main_layout);
		tomorrowNoLayout = (RelativeLayout) rootView.findViewById(com.fuseini.instant.weatherapp.R.id.tomorrow_no_layout);
		
		// Hide main layout and show no weather layout
		tomorrowMain.setVisibility(View.GONE);
		tomorrowNoLayout.setVisibility(View.VISIBLE);
		
		tomorrowName = (TextView) rootView.findViewById(com.fuseini.instant.weatherapp.R.id.tomorrow_name);
		
		// Set weatherFont to icon TextView
		tomorrowIcon = (TextView) rootView.findViewById(com.fuseini.instant.weatherapp.R.id.tomorrow_icon);
		tomorrowForecastOneIcon = (TextView) rootView.findViewById(com.fuseini.instant.weatherapp.R.id.tomorrow_forecast1_icon);
		tomorrowForecastTwoIcon = (TextView) rootView.findViewById(com.fuseini.instant.weatherapp.R.id.tomorrow_forecast2_icon);
		tomorrowForecastThreeIcon = (TextView) rootView.findViewById(com.fuseini.instant.weatherapp.R.id.tomorrow_forecast3_icon);
		tomorrowForecastFourIcon = (TextView) rootView.findViewById(com.fuseini.instant.weatherapp.R.id.tomorrow_forecast4_icon);
		
		Typeface weatherFont = Typeface.createFromAsset(getActivity().getAssets(), "weathericons-font.ttf");
		tomorrowIcon.setTypeface(weatherFont);
		tomorrowForecastOneIcon.setTypeface(weatherFont);
		tomorrowForecastTwoIcon.setTypeface(weatherFont);
		tomorrowForecastThreeIcon.setTypeface(weatherFont);
		tomorrowForecastFourIcon.setTypeface(weatherFont);
		
		// Set no weather icon
		TextView tomorrowNoWeatherIcon = (TextView) rootView.findViewById(com.fuseini.instant.weatherapp.R.id.tomorrow_no_weather_icon);
		tomorrowNoWeatherIcon.setTypeface(weatherFont);
		
		tomorrowTemperature = (TextView) rootView.findViewById(com.fuseini.instant.weatherapp.R.id.tomorrow_temperature);
		tomorrowDescription = (TextView) rootView.findViewById(com.fuseini.instant.weatherapp.R.id.tomorrow_description);
		
		tomorrowForecastOneTemp = (TextView) rootView.findViewById(com.fuseini.instant.weatherapp.R.id.tomorrow_forecast1);
		tomorrowForecastTwoTemp = (TextView) rootView.findViewById(com.fuseini.instant.weatherapp.R.id.tomorrow_forecast2);
		tomorrowForecastThreeTemp = (TextView) rootView.findViewById(com.fuseini.instant.weatherapp.R.id.tomorrow_forecast3);
		tomorrowForecastFourTemp = (TextView) rootView.findViewById(com.fuseini.instant.weatherapp.R.id.tomorrow_forecast4);
		
		tomorrowForecastOneDay = (TextView) rootView.findViewById(com.fuseini.instant.weatherapp.R.id.tomorrow_forecast1_day);
		tomorrowForecastTwoDay = (TextView) rootView.findViewById(com.fuseini.instant.weatherapp.R.id.tomorrow_forecast2_day);
		tomorrowForecastThreeDay = (TextView) rootView.findViewById(com.fuseini.instant.weatherapp.R.id.tomorrow_forecast3_day);
		tomorrowForecastFourDay = (TextView) rootView.findViewById(com.fuseini.instant.weatherapp.R.id.tomorrow_forecast4_day);
		
		tomorrowCopyright = (TextView) rootView.findViewById(com.fuseini.instant.weatherapp.R.id.tomorrow_copyright);
	}
	
	// Display weather object list to text views
	public static void displayTomorrowWeather(Context context, List<WeatherObject> weatherList) {
		// Set tomorrow main layout
		tomorrowMain.setVisibility(View.VISIBLE);
		tomorrowNoLayout.setVisibility(View.GONE);
				
		// Name and icon
		tomorrowName.setText(weatherList.get(0).getLocation());
		tomorrowIcon.setText(context.getString(weatherList.get(0).getIcon()));
		
		// Temperature units
		if (weatherList.get(0).getUnit() == WeatherObject.METRIC || weatherList.get(0).getUnit() == WeatherObject.DEFAULT) {
			tomorrowTemperature.setText(weatherList.get(0).getTemperature() + " " + context.getString(com.fuseini.instant.weatherapp.R.string.celsius));
			tomorrowForecastOneTemp.setText(formatForecastTemperature(weatherList.get(1).getTemperature()) + " " + context.getString(com.fuseini.instant.weatherapp.R.string.celsius));
			tomorrowForecastTwoTemp.setText(formatForecastTemperature(weatherList.get(2).getTemperature()) + " " + context.getString(com.fuseini.instant.weatherapp.R.string.celsius));
			tomorrowForecastThreeTemp.setText(formatForecastTemperature(weatherList.get(3).getTemperature()) + " " + context.getString(com.fuseini.instant.weatherapp.R.string.celsius));
			tomorrowForecastFourTemp.setText(formatForecastTemperature(weatherList.get(4).getTemperature()) + " " + context.getString(com.fuseini.instant.weatherapp.R.string.celsius));
		} else if (weatherList.get(0).getUnit() == WeatherObject.IMPERIAL) {
			tomorrowTemperature.setText(weatherList.get(0).getTemperature() + " " + context.getString(com.fuseini.instant.weatherapp.R.string.fahrenheit));
			tomorrowForecastOneTemp.setText(formatForecastTemperature(weatherList.get(1).getTemperature()) + " " + context.getString(com.fuseini.instant.weatherapp.R.string.fahrenheit));
			tomorrowForecastTwoTemp.setText(formatForecastTemperature(weatherList.get(2).getTemperature()) + " " + context.getString(com.fuseini.instant.weatherapp.R.string.fahrenheit));
			tomorrowForecastThreeTemp.setText(formatForecastTemperature(weatherList.get(3).getTemperature()) + " " + context.getString(com.fuseini.instant.weatherapp.R.string.fahrenheit));
			tomorrowForecastFourTemp.setText(formatForecastTemperature(weatherList.get(4).getTemperature()) + " " + context.getString(com.fuseini.instant.weatherapp.R.string.fahrenheit));
		}
		
		// Details
		tomorrowDescription.setText(weatherList.get(0).getDescription().toLowerCase(Locale.US));
		
		// Forecast items
		tomorrowForecastOneIcon.setText(context.getString(weatherList.get(1).getIcon()));
		tomorrowForecastTwoIcon.setText(context.getString(weatherList.get(2).getIcon()));
		tomorrowForecastThreeIcon.setText(context.getString(weatherList.get(3).getIcon()));
		tomorrowForecastFourIcon.setText(context.getString(weatherList.get(4).getIcon()));
		
		// Display forthcoming days 
		Calendar currentDay = Calendar.getInstance();
		currentDay.add(Calendar.DATE, 2); // Skip today and tomorrow
		tomorrowForecastOneDay.setText(currentDay.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US));
		currentDay.add(Calendar.DATE, 1);
		tomorrowForecastTwoDay.setText(currentDay.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US));
		currentDay.add(Calendar.DATE, 1);
		tomorrowForecastThreeDay.setText(currentDay.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US));
		currentDay.add(Calendar.DATE, 1);
		tomorrowForecastFourDay.setText(currentDay.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US));
		
		tomorrowCopyright.setText(context.getText(com.fuseini.instant.weatherapp.R.string.copyright));
	}
	
	// Format temperature to remove digits
	private static String formatForecastTemperature (String temperature) {
		DecimalFormat df = new DecimalFormat("#");
		return df.format(Double.parseDouble(temperature)).toString();
	}
	
	// Notify user that weather info is outdated 
	public static void displayOutdatedInfo(Context context) {
		tomorrowCopyright.setText(context.getString(com.fuseini.instant.weatherapp.R.string.outdated_weather));
	}
}
