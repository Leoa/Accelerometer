package com.leobee.accelerometer;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import thedatabase.DatabaseOperations;
import thedatabase.Position;
import utilities.TimeOutTimer;
import utilities.Timestamp;
import utilities.PreferenceConnector;
import android.content.SharedPreferences;
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
    DatabaseOperations DB = new DatabaseOperations(ctx);
    Button btn,btn2;
    TimeOutTimer timer = new TimeOutTimer();
    public static String APP_PREFERENCES = "appPreferences";
    public static String PREFERENCE_SAMPLE = "prefSample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // text box display if phone is shaking
        textBox=(TextView)findViewById(R.id.textView);
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
       // senAccelerometer = (senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
        senAccelerometer =  senSensorManager .getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_GAME);
        textBox.setText("Move Phone");

        timer.startTimer();

        btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                //  Log.d("Reading: ", "Reading all Positions..");
                List<Position> positions = DB.getAllPositions();
                for (Position pos : positions) {
                    String log = "Position: " + pos.getPosition() + " ,timestamp: " + pos.getTimestamp();
                    // Writing Contacts to log
                    Log.d("Name: ", log);

                }

                int posLeft = DB.getSumLeft();
                int posRight = DB.getSumRight();
                int posUp = DB.getSumUp();
                int posDown = DB.getSumDown();
                int posFront = DB.getSumFront();
                int posBack = DB.getSumBack();

                System.out.println("Sum of lefts are "+ posLeft);
                System.out.println("Sum of rights are "+ posRight);
                System.out.println("Sum of ups are "+ posUp);
                System.out.println("Sum of downs are "+ posDown);
                System.out.println("Sum of fronts are "+ posFront);
                System.out.println("Sum of backs are "+ posBack);

                //getAllLeftTimeStamps()
                List<Position> timeStampLefts = DB.getAllLeftTimeStamps();
                for (Position timeLefts : timeStampLefts) {
                    String log = " Getting all left Positions:  ,timestamp: " + timeLefts.getTimestamp();
                    // Writing Contacts to log
                    Log.d("Name: ", log);

                }


            }
        });

        btn2 =(Button)findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener(){
            // the onClick method must be included when you declare a new OnClickListener object
            public void onClick(View v) {
                //Your code for when your button was clicked would go here

               timer.stopTimerTask(ctx);



                int removeSplash = PreferenceConnector.readInteger(MainActivity.this,
                        PreferenceConnector.DATACOLLECTION_ON_OFF, 0);

                if (removeSplash != 1) {
                     removeSplash = 1;
                    PreferenceConnector.writeInteger(MainActivity.this,
                            PreferenceConnector.DATACOLLECTION_ON_OFF, removeSplash);
                }


            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event){



        // where we can detect change
        Sensor mySensor = event.sensor;

        // value added to database for art calculations


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
            long curTime=System.currentTimeMillis();


            // compare the current time to last update to limit data from sensor
            if((curTime - lastUpdate)>1){
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                //detect if the device has been shaken
                float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 3000;
                if(speed < SHAKE_THRESHOLD){
                    // do nothing
                    textBox.setText("Phone is NOT moving");
                } else {
                    if (xChange > 2) {
                        direction[0] = "LEFT";
                        DB.putInfo(DB,"left", Timestamp.getCurrentTimeStamp());
                    } else if (xChange < -2) {
                        direction[0] = "RIGHT";
                        DB.putInfo(DB,"right", Timestamp.getCurrentTimeStamp());
                    } else if (yChange > 2) {
                        direction[1] = "DOWN";
                        DB.putInfo(DB,"down", Timestamp.getCurrentTimeStamp());
                    } else if (yChange < -2) {
                        direction[1] = "UP";
                        DB.putInfo(DB,"up", Timestamp.getCurrentTimeStamp());
                    } else if (zChange > 2) {
                        direction[2] = "FORWARD";
                        DB.putInfo(DB,"front", Timestamp.getCurrentTimeStamp());
                    } else if (zChange < -2) {
                        direction[2] = "BACKWARD";
                        DB.putInfo(DB,"back", Timestamp.getCurrentTimeStamp());
                    } else {
                       // textBox.setText("phone is shaking");
                        //DB.putInfo(DB,"shake", Timestamp.getCurrentTimeStamp());
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
              //  DatabaseOperations DOP = new DatabaseOperations(ctx);
               /* Cursor CR =DOP.getInfo(DOP);
                CR.moveToFirst();
                boolean movementStatus = false;
                do{

                    String right = "";

                    CR.getInt(0);

                }while(CR.moveToNext());
*/
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor,int accuracy){}

    @Override

    protected  void onPause(){
    super.onPause();

        ///query db


             senSensorManager.unregisterListener(this);

}
    protected void onResume(){
        super.onResume();
        senSensorManager.registerListener(this,senAccelerometer,senSensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onStop(){


        super.onStop();

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
