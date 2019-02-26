package com.example.myapplication;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;

public class weatherdata
{
	private String city;
	private int condition;
	private String temperature;
	private String humidity;
	private String mintemp;
	private String maxtemp;
	private String sunrise;
	private String sunset;
	private String icon_name;

	public static weatherdata fromJSON(JSONObject jsonObject)
	{
		try
		{
			weatherdata data=new weatherdata();
			data.city=jsonObject.getString("name");
			data.condition=jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id");

			double temp=jsonObject.getJSONObject("main").getDouble("temp")- 273.15;
			int t=(int) Math.rint(temp);
			data.temperature=Integer.toString(t);

			temp=jsonObject.getJSONObject("main").getDouble("temp_min")-273.15;
			t=(int) Math.rint(temp);
			data.mintemp=Integer.toString(t);

			temp=jsonObject.getJSONObject("main").getDouble("temp_max")-273.15;
			t=(int) Math.rint(temp);
			data.maxtemp=Integer.toString(t);

			temp=jsonObject.getJSONObject("main").getDouble("humidity");
			t=(int) Math.rint(temp);
			data.humidity=Integer.toString(t);

			temp=jsonObject.getJSONObject("sys").getDouble("sunrise");
			Date date=new Date((long)temp*1000L);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
			//sdf.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
			data.sunrise = sdf.format(date);
			data.sunrise=data.sunrise.substring(11,16);
			//Log.d("weather",""+data.sunrise);

			temp=jsonObject.getJSONObject("sys").getDouble("sunset");
			date = new Date((long)temp*1000L);
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
			//sdf.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
			data.sunset = sdf.format(date);
			data.sunset=data.sunset.substring(11,16);
			//Log.d("weather",""+data.sunset);

			data.icon_name=getname(data.condition);

			return data;
		}
		catch(JSONException ex)
		{
			ex.printStackTrace();
			return null;
		}
	}

	private static String getname(int cond)
	{
		if (cond >= 0 && cond < 300)
		{
			return "tstorm1";
		}
		else if (cond >= 300 && cond < 500)
		{
			return "light_rain";
		}
		else if (cond >= 500 && cond < 600)
		{
			return "shower3";
		}
		else if (cond >= 600 && cond <= 700)
		{
			return "snow4";
		}
		else if (cond >= 701 && cond <= 771)
		{
			return "fog";
		}
		else if (cond >= 772 && cond < 800)
		{
			return "tstorm3";
		}
		else if (cond == 800)
		{
			return "sunny";
		}
		else if (cond >= 801 && cond <= 804)
		{
			return "cloudy2";
		}
		else if (cond >= 900 && cond <= 902)
		{
			return "tstorm3";
		}
		else if (cond == 903)
		{
			return "snow5";
		}
		else if (cond == 904)
		{
			return "sunny";
		}
		else if (cond >= 905 && cond <= 1000)
		{
			return "tstorm3";
		}
		return "dunno";
	}

	public String getCity() {
		return city;
	}

	public String getTemperature() {
		return temperature + "°C";
	}

	public String getHumidity() {
		return humidity+"%";
	}

	public String getMintemp() {
		return mintemp + "°C";
	}

	public String getMaxtemp() {
		return maxtemp + "°C";
	}

	public String getSunrise() {
		return sunrise;
	}

	public String getSunset() {
		return sunset;
	}

	public String getIcon_name() {
		return icon_name;
	}
}
