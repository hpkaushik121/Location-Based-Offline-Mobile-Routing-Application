package com.example.sourabh.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sourabh.R;
import com.example.sourabh.activities.AboutActivity;
import com.example.sourabh.activities.MainActivity;
import com.example.sourabh.activities.Analytics;
import com.example.sourabh.map.Tracking;
import com.example.sourabh.util.Variable;

import java.text.SimpleDateFormat;

/**
 * This file is
 * Created by Sourabh kaushik on March 14, 2018.
 */
public class AppSettings {
    private Activity activity;
    private ViewGroup appSettingsVP, trackingAnalyticsVP, changeMapItemVP;
    private ImageButton infobtn;



    /**
     * init and set
     *
     * @param activity
     * @param calledFromVP
     */
    public AppSettings (Activity activity) {
        this.activity = activity;
        appSettingsVP = (ViewGroup) activity.findViewById(R.id.app_settings_layout);

        changeMapItemVP = (ViewGroup) activity.findViewById(R.id.app_settings_change_map);
    }
    
    public void showAppSettings(final ViewGroup calledFromVP)
    {
        initClearBtn(appSettingsVP, calledFromVP);
        chooseMapBtn(appSettingsVP);
        trackingBtn(appSettingsVP);
        alternateRoute();
        appSettingsVP.setVisibility(View.VISIBLE);
        calledFromVP.setVisibility(View.INVISIBLE);

        if (Tracking.getTracking().isTracking()) resetAnalyticsItem();
    }


    public ViewGroup getAppSettingsVP() {
        return appSettingsVP;
    }

    /**
     * init and implement directions checkbox
     */

    
    /**
     * init and set alternate route radio button option
     */
    private void alternateRoute() {
        RadioGroup rg = (RadioGroup) activity.findViewById(R.id.app_settings_weighting_rbtngroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.app_settings_fastest_rbtn:
                        Variable.getVariable().setWeighting("fastest");
                        break;
                    case R.id.app_settings_shortest_rbtn:
                        Variable.getVariable().setWeighting("shortest");
                        break;
                }
            }
        });
        RadioButton rbf, rbs;
        rbf = (RadioButton) activity.findViewById(R.id.app_settings_fastest_rbtn);
        rbs = (RadioButton) activity.findViewById(R.id.app_settings_shortest_rbtn);
        infobtn = (ImageButton) activity.findViewById(R.id.app_settings_info_btn);
        infobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, AboutActivity.class);
                activity.startActivity(intent);
            }
        });
        infobtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(activity,"Show team information",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        if (Variable.getVariable().getWeighting().equalsIgnoreCase("fastest")) {
            rbf.setChecked(true);
        } else {
            rbs.setChecked(true);
        }
    }

    /**
     * tracking item btn handler
     *
     * @param appSettingsVP
     */
    private void trackingBtn(final ViewGroup appSettingsVP) {
        //        final ImageView iv = (ImageView) activity.findViewById(R.id.app_settings_tracking_iv);
        //        final TextView tv = (TextView) activity.findViewById(R.id.app_settings_tracking_tv);



    }

    private void confirmWindow() {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        final EditText edittext = new EditText(activity);
        builder.setTitle(activity.getResources().getString(R.string.dialog_stop_save_tracking));
        builder.setMessage("path: " + Variable.getVariable().getTrackingFolder().getAbsolutePath() + "/");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String formattedDate = df.format(System.currentTimeMillis());
        edittext.setText(formattedDate);
        builder.setView(edittext);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        //        builder.setView(inflater.inflate(R.layout.dialog_tracking_exit, null));
        // Add action buttons
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int id) {
                // save file
                Tracking.getTracking().saveAsGPX(edittext.getText().toString());
                Tracking.getTracking().stopTracking(AppSettings.this);

            }
        }).setNeutralButton(R.string.stop, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Tracking.getTracking().stopTracking(AppSettings.this);

            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        //        ((EditText) ((AlertDialog) dialog).findViewById(R.id.dialog_tracking_exit_et)).setText(formattedDate);
        dialog.show();
    }

    /**
     * dynamic show start or stop tracking
     */


    /**
     * init and reset analytics items to visible (when tracking start)
     */
    private void resetAnalyticsItem() {
        changeMapItemVP.setVisibility(View.GONE);
        trackingAnalyticsVP.setVisibility(View.VISIBLE);
        trackingAnalyticsBtn();


    }

    /**
     * actions to preform when tracking analytics item (btn) is clicked
     */
    private void trackingAnalyticsBtn() {
        trackingAnalyticsVP.setOnTouchListener(new View.OnTouchListener() {
            @Override public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        trackingAnalyticsVP
                                .setBackgroundColor(activity.getResources().getColor(R.color.my_primary_light));
                        return true;
                    case MotionEvent.ACTION_UP:
                        trackingAnalyticsVP.setBackgroundColor(activity.getResources().getColor(R.color.my_icons));
                        openAnalyticsActivity();
                        return true;
                }
                return false;
            }
        });
    }

    /**
     * update speed and distance at analytics item
     *
     * @param speed
     * @param distance
     */



    /**
     * move to select and load map view
     *
     * @param appSettingsVP
     */
    private void chooseMapBtn(final ViewGroup appSettingsVP) {
        changeMapItemVP.setOnTouchListener(new View.OnTouchListener() {
            @Override public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        changeMapItemVP.setBackgroundColor(activity.getResources().getColor(R.color.my_primary_light));
                        return true;
                    case MotionEvent.ACTION_UP:
                        changeMapItemVP.setBackgroundColor(activity.getResources().getColor(R.color.my_icons));
                        // Variable.getVariable().setAutoLoad(false); // close auto load from
                        // main activity
                        activity.finish();
                        startMainActivity();
                        return true;
                }
                return false;
            }
        });
    }

    /**
     * init clear btn
     */
    private void initClearBtn(final ViewGroup appSettingsVP, final ViewGroup calledFromVP) {
        ImageButton appsettingsClearBtn = (ImageButton) activity.findViewById(R.id.app_settings_clear_btn);
        appsettingsClearBtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                appSettingsVP.setVisibility(View.INVISIBLE);
                calledFromVP.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * move to main activity
     */
    private void startMainActivity() {
        //        if (Tracking.getTracking().isTracking()) {
        //            Toast.makeText(activity, "You need to stop your tracking first!", Toast.LENGTH_LONG).show();
        //        } else {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra("SELECTNEWMAP", true);
        activity.startActivity(intent);
        //        activity.finish();
        //        }
    }

    /**
     * open analytics activity
     */

    private void openAnalyticsActivity() {
        Intent intent = new Intent(activity, Analytics.class);
        activity.startActivity(intent);
        //        activity.finish();
    }

    /**
     * send message to logcat
     *
     * @param str
     */
    private void log(String str) {
        Log.i(this.getClass().getName(), str);
    }

    /**
     * send message to logcat and Toast it on screen
     *
     * @param str: message
     */
    private void logToast(String str) {
        log(str);
        Toast.makeText(activity, str, Toast.LENGTH_SHORT).show();
    }
}
