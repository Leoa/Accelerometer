package com.leobee.accelerometer;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.util.Random;

import thedatabase.DatabaseOperations;
import utilities.Timestamp;
import utilities.RandomNumbers;

/**
 * Created by Leondria on 12/17/2014.
 */
public class TestDatabase extends Activity {

   // public void TextDatabase(){


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
Context ctx = this;
        DatabaseOperations DB = new DatabaseOperations(ctx);

        int num=0;


        for(int i = 0; i < 60;i++){
            num = RandomNumbers.randInt(0,7);
            if (num ==0) {

                DB.putInfo(DB,"left", Timestamp.getCurrentTimeStamp());
            } else if (num == 1) {

                DB.putInfo(DB,"right", Timestamp.getCurrentTimeStamp());
            } else if (num == 2) {

                DB.putInfo(DB,"down", Timestamp.getCurrentTimeStamp());
            } else if (num == 3) {

                DB.putInfo(DB,"up", Timestamp.getCurrentTimeStamp());
            } else if (num == 4) {

                DB.putInfo(DB,"front", Timestamp.getCurrentTimeStamp());
            } else if (num == 5) {

                DB.putInfo(DB,"back", Timestamp.getCurrentTimeStamp());

            }



        }



}


      /*
*/
    }




