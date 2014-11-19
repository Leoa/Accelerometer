package com.leobee.accelerometer;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.leobee.accelerometer.R;
//http://code.tutsplus.com/tutorials/using-the-accelerometer-on-android--mobile-22125


public class MainActivity extends Activity implements SensorEventListener{

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private long lastUpdate=0;
    private float last_x,last_y,last_z;
    private static final int SHAKE_THRESHOLD = 600;
    private TextView textBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // text box display if phone is shakeing
        textBox=(TextView)findViewById(R.id.textView);
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = (senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        textBox.setText("Shake Phone");
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        // where we can detect change
        Sensor mySensor =event.sensor;

        if(mySensor.getType()==Sensor.TYPE_ACCELEROMETER){
            // get x,y and z of the phone's position
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            // get the current time of the
            long curTime=System.currentTimeMillis();

            // compare the current time to last update to limit data from sensor
            if((curTime - lastUpdate)>100){
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                //detect if the device has been shaken
                float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 10000;
                if(speed < SHAKE_THRESHOLD){
                    // do nothing
                    textBox.setText("phone is NOT shaking");
                }
                textBox.setText("phone is shaking");
                last_x = x;
                last_y = y;
                last_z = z;

            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor,int accuracy){}

@Override

protected  void onPause(){

    super.onPause();
    senSensorManager.unregisterListener(this);

}
    protected void onResume(){
        super.onResume();
        senSensorManager.registerListener(this,senAccelerometer,senSensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
