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

import thedatabase.DatabaseOperations;

import utilities.Timestamp;
//http://code.tutsplus.com/tutorials/using-the-accelerometer-on-android--mobile-22125
//https://www.youtube.com/watch?v=zH7dmLjUrPA&list=PLshdtb5UWjSrEUEKlfHwqQtYu2HxtCwu_&index=2 17:30

public class MainActivity extends Activity implements SensorEventListener{

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private long lastUpdate=0;
    private float last_x,last_y,last_z;
    private static final int SHAKE_THRESHOLD = 1;
    private TextView textBox;
    private StringBuilder builder = new StringBuilder();
    private float [] history = new float[3];
    String [] direction = {"NONE","NONE","NONE"};
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // text box display if phone is shakeing
        textBox=(TextView)findViewById(R.id.textView);
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
       // senAccelerometer = (senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
        senAccelerometer =  senSensorManager .getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_GAME);
        textBox.setText("Move Phone");

    }

    @Override
    public void onSensorChanged(SensorEvent event){

        DatabaseOperations DB = new DatabaseOperations(ctx);

        // where we can detect change
        Sensor mySensor = event.sensor;

        // value added to database for art calculations
        int positionValue=1;

        if(mySensor.getType()==Sensor.TYPE_ACCELEROMETER){
            // get x,y and z of the phone's position
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            float xChange = history[0] - x;
            float yChange = history[1] - y;
            float zChange = history[2] - z;

            history[0] = x;
            history[1] = y;
            history[2] = z;

            // get the current time of the
            long curTime=System.currentTimeMillis()/1000;


            // compare the current time to last update to limit data from sensor
            if((curTime - lastUpdate)>1){
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                //detect if the device has been shaken
                float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 100;
                if(speed < SHAKE_THRESHOLD){
                    // do nothing
                    textBox.setText("Phone is NOT Moving");
                } else {
                    if (xChange > 2) {
                        direction[0] = "LEFT";
                        DB.putInfo(DB,"left", Timestamp.getCurrentTimeStamp(),positionValue);
                    } else if (xChange < -2) {
                        direction[0] = "RIGHT";
                        DB.putInfo(DB,"right", Timestamp.getCurrentTimeStamp(),positionValue);
                    } else if (yChange > 2) {
                        direction[1] = "DOWN";
                        DB.putInfo(DB,"down", Timestamp.getCurrentTimeStamp(),positionValue);
                    } else if (yChange < -2) {
                        direction[1] = "UP";
                        DB.putInfo(DB,"up", Timestamp.getCurrentTimeStamp(),positionValue);
                    } else if (zChange > 2) {
                        direction[2] = "FORWARD";
                        DB.putInfo(DB,"front", Timestamp.getCurrentTimeStamp(),positionValue);
                    } else if (zChange < -2) {
                        direction[2] = "BACKWARD";
                        DB.putInfo(DB,"back", Timestamp.getCurrentTimeStamp(),positionValue);
                    } else {
                        textBox.setText("phone is shaking");
                        DB.putInfo(DB,"shake", Timestamp.getCurrentTimeStamp(),positionValue);
                        last_x = x;
                        last_y = y;
                        last_z = z;
                    }
                }

                builder.setLength(0);
                builder.append("x: ");
                builder.append(direction[0]);
                builder.append("\n");
                builder.append(" y: ");
                builder.append(direction[1]);
                builder.append("\n");
                builder.append(" z: ");
                builder.append(direction[2]);


                textBox.setText(builder.toString());

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
