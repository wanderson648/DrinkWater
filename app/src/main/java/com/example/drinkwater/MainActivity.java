package com.example.drinkwater;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    private Button btnNotify;
    private EditText editMinutes;
    private TimePicker timePicker;

    private int hour;
    private int minutes;
    private int interval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      // pegando os ids definidos no xml
      btnNotify = findViewById(R.id.btn_notify);
      editMinutes = findViewById(R.id.edit_text_number_interval);
      timePicker = findViewById(R.id.time_picker);


      // definindo o formato de hora no padrão brasileiro
      timePicker.setIs24HourView(true);

    }

    // forma simples de ouvir eventos de touch
    public void notifyClick(View view) {
      String sInterval = editMinutes.getText().toString();

      hour = timePicker.getCurrentHour();
      minutes = timePicker.getCurrentMinute();
      interval = Integer.parseInt(sInterval);

      Log.d("teste", "horas: " + hour + "minutes: "+ minutes + " intervalo: "+ interval);
    }
}

















