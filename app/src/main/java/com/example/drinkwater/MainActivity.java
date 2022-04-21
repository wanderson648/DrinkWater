package com.example.drinkwater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  private Button btnNotify;
  private EditText editMinutes;
  private TimePicker timePicker;

  private int hour;
  private int minutes;
  private int interval;

  private boolean activated;

  private SharedPreferences preferences;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    btnNotify = findViewById(R.id.btn_notify);
    editMinutes = findViewById(R.id.edit_text_number_interval);
    timePicker = findViewById(R.id.time_picker);

    timePicker.setIs24HourView(true);
    preferences = getSharedPreferences("db", Context.MODE_PRIVATE);

    activated = preferences.getBoolean("activated", false);

    if(activated) {
      btnNotify.setText(R.string.pause);
      int color = ContextCompat.getColor(this, R.color.teal_700);
      btnNotify.setBackgroundColor(color);

      int interval = preferences.getInt("interval", 0);
      int hour = preferences.getInt("hour", timePicker.getCurrentHour());
      int minute = preferences.getInt("minutes", timePicker.getCurrentMinute());

      editMinutes.setText(String.valueOf(interval));
      timePicker.setCurrentHour(hour);
      timePicker.setCurrentMinute(minute);
    }
  }



  public void notifyClick(View view) {
    String sInterval = editMinutes.getText().toString();

    if (sInterval.isEmpty()) {
      Toast.makeText(this, R.string.error_msg, Toast.LENGTH_SHORT).show();
      return;
    }

    hour = timePicker.getCurrentHour();
    minutes = timePicker.getCurrentMinute();
    interval = Integer.parseInt(sInterval);

    if(!activated) {
      btnNotify.setText(R.string.pause);
      int color = ContextCompat.getColor(this, R.color.teal_700);
      btnNotify.setBackgroundColor(color);
      activated = true;

      SharedPreferences.Editor editor = preferences.edit();
      editor.putBoolean("activated", true);
      editor.putInt("hour", hour);
      editor.putInt("minutes", minutes);
      editor.putInt("interval", interval);
      editor.apply();


    } else {
      btnNotify.setText(R.string.notify);
      int color = ContextCompat.getColor(this, androidx.cardview.R.color.cardview_dark_background);
      btnNotify.setBackgroundColor(color);
      activated = false;

      SharedPreferences.Editor editor = preferences.edit();
      editor.putBoolean("activated", false);
      editor.remove("hour");
      editor.remove("minutes");
      editor.remove("interval");
      editor.apply();
    }

  }
}

















