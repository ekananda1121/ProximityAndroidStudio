package com.example.proximitysensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView value;
    Database Database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager!=null){
            Sensor proxiSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

            if(proxiSensor!=null){
                sensorManager.registerListener(this, proxiSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        }
        value = findViewById(R.id.txtValues);
        Database = new Database(this);
    }

    public void btnAdd(View v){
        int data = Integer.parseInt(value.getText().toString());
        Boolean status = Database.addData(data);

        if (status)
            Toast.makeText(this, "Data berhasil diinput", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Data gagal diinput", Toast.LENGTH_SHORT).show();
    }

    public void pindah(View view){
        Intent intent = new Intent(MainActivity.this, tampilData.class);
        startActivity(intent);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType()==Sensor.TYPE_PROXIMITY){
            ((TextView)findViewById(R.id.txtValues)).setText("" + event.values[0]);

            if (event.values[0]>0) {
                Toast.makeText(this, "Object jauh", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Object dekat", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}