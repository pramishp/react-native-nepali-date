
package com.reactlibrary;

import android.app.Activity;
import android.app.FragmentManager;
import android.graphics.Color;

import com.facebook.react.ReactFragmentActivity;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.hornet.dateconverter.DateConverter;
import com.hornet.dateconverter.DatePicker.DatePickerDialog;
import com.hornet.dateconverter.Model;
import com.facebook.react.bridge.Callback;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RNNepaliDatePickerModule extends ReactContextBaseJavaModule implements DatePickerDialog.OnDateSetListener{

    private static final String ON_DATE_SET = "ON_DATE_SET";
    private static final String DATE_OUT_OF_RAGE = "Date is out of range";

    private ReactApplicationContext reactContext;


    public RNTNepaliCalenderManager(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @ReactMethod
    public void englishToNepali(int year, int month, int day,Callback successCallback,
                                Callback errorCallback) {

        DateConverter dc=new DateConverter();
        try {
            Model outputOfConversion = dc.getNepaliDate(year, month, day);
            int yy=outputOfConversion.getYear();
            int mm=outputOfConversion.getMonth();
            int dd=outputOfConversion.getDay();
            int day_of_week = outputOfConversion.getDayOfWeek();
            successCallback.invoke(yy, mm +1, dd, day_of_week);
        } catch (IllegalArgumentException e){
            errorCallback.invoke("Date out of Range");
        }
    }

    @ReactMethod
    public void nepaliToEnglish(int year, int month, int day,Callback successCallback,
                                Callback errorCallback) {
        DateConverter dc= new DateConverter();
        try {
            Model outputOfConversion = dc.getEnglishDate(year, month, day);
            int yy=outputOfConversion.getYear();
            int mm=outputOfConversion.getMonth()+1;
            int dd=outputOfConversion.getDay();
            int day_of_week = outputOfConversion.getDayOfWeek();
            successCallback.invoke(yy, mm, dd, day_of_week);
        } catch (IllegalArgumentException e){
            errorCallback.invoke("Date out of Range");
        }
    }

    @ReactMethod
    public void getTodayNepaliDate(Callback callback){
        DateConverter dc = new DateConverter();
        Model outputOfConversion = dc.getTodayNepaliDate();
        int yy=outputOfConversion.getYear();
        int mm=outputOfConversion.getMonth();
        int dd=outputOfConversion.getDay();
        int day_of_week = outputOfConversion.getDayOfWeek();
        callback.invoke(yy, mm+1, dd, day_of_week);
    }

    @Override
    public String getName() {
        return "NepaliCalender";
    }

    @ReactMethod
    public void showDatePicker(ReadableMap props){
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH));

        String title = null;
        Boolean setDarkTheme = null;
        Boolean dismissOnPause = null;
        Boolean showYearPickerFirst = null;
        String setAccentColor = null;
        Boolean newVersion = null;


        if (props.hasKey("title")){
            title = props.getString("title");
        }
        if (props.hasKey("setDarkTheme")){
            setDarkTheme = props.getBoolean("setDarkTheme");
        }
        if (props.hasKey("dismissOnPause")){
            dismissOnPause = props.getBoolean("dismissOnPause");
        }
        if (props.hasKey("showYearPickerFirst")){
            showYearPickerFirst = props.getBoolean("showYearPickerFirst");
        }
        if (props.hasKey("setAccentColor")){
            setAccentColor = props.getString("setAccentColor");
        }
        if (props.hasKey("newVersion")){
            newVersion = props.getBoolean("newVersion");
        }
        if (title != null){
            dpd.setTitle(title);
        }
        if (setDarkTheme != null){
            dpd.setThemeDark(setDarkTheme);
        }
        if (dismissOnPause != null){
            dpd.dismissOnPause(dismissOnPause);
        }
        if (showYearPickerFirst != null){
            dpd.showYearPickerFirst(showYearPickerFirst);
        }
        if (setAccentColor != null){
            dpd.setAccentColor(Color.parseColor(setAccentColor));
        }
        if (newVersion != null){
            dpd.setVersion(DatePickerDialog.Version.VERSION_2);
        } else {
            dpd.setVersion(DatePickerDialog.Version.VERSION_1);
        }

        final Activity activity= getCurrentActivity();
        if (activity instanceof android.support.v4.app.FragmentActivity) {
            android.support.v4.app.FragmentManager fragmentManager =
                    ((android.support.v4.app.FragmentActivity) activity).getSupportFragmentManager();
            dpd.show(fragmentManager, "DatePicker");
        }

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        WritableMap map = Arguments.createMap();

        map.putInt("year", year);
        map.putInt("month", monthOfYear+1);
        map.putInt("day", dayOfMonth);

        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(ON_DATE_SET, map);
    }


}
