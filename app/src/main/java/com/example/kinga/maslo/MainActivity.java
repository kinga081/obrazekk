package com.example.kinga.maslo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private ImageView iv;
    private Sensor ruch;
    private SensorManager manager;
    private float currentDegree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.imageView);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        ruch = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }
    protected void onResume() {
        super.onResume();
        //manager.registerListener(this, step, SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(this,ruch, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (Sensor.TYPE_ACCELEROMETER == event.sensor.getType()) {/////////////
            float degree = Math.round(event.values[0]) * 10;
            //tx.setText(""+degree);

            
            RotateAnimation ra = new RotateAnimation(
                    currentDegree,
                    degree,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f);
// how long the animation will take place
            ra.setDuration(210);
// set the animation after the end of the reservation status
            ra.setFillAfter(true);

// Start the animation
            iv.startAnimation(ra);
            currentDegree = degree;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    public void onStop() {

        super.onStop();
    }

}
