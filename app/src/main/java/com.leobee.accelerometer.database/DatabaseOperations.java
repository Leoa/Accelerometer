package com.leobee.accelerometer.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.leobee.accelerometer.database.TableData;


/**
 * Created by Leondria on 12/8/2014.
 */
public class DatabaseOperations  extends SQLiteOpenHelper{

    public static final int database_version = 1;
    public String CREATE_QUERY="CREATE TABLE " + com.leobee.accelerometer.database.TableData.TableInfo.TABLE_NAME+
            "(" + TableData.TableInfo.DIRECTION_RIGHT + " INT,"
                + TableData.TableInfo.DIRECTION_LEFT + " INT,"
                + TableData.TableInfo.DIRECTION_UP + " INT,"
                + TableData.TableInfo.DIRECTION_DOWN + " INT,"
                + TableData.TableInfo.DIRECTION_FRONT+ " INT,"
                + TableData.TableInfo.DIRECTION_BACK+" INT,"
                + TableData.TableInfo.TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL);";

    public DatabaseOperations(Context context){
        super(context, TableData.TableInfo.DATABASE_NAME, null, database_version);
        Log.d("Database Operations","Data base created");
    }



    @Override
    public void onCreate(SQLiteDatabase sdb) {
        sdb.execSQL(CREATE_QUERY);
        Log.d("Database Operations","Table Created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sdb, int oldVersion, int newVersion) {

    }

    public void putInfo(DatabaseOperations dop, String position, String timestamp){
        SQLiteDatabase SQ= dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String column =null;


        if(position.equalsIgnoreCase("back")) {
            column = TableData.TableInfo.DIRECTION_BACK;
        }
        else if(position.equalsIgnoreCase("front")){
            column = TableData.TableInfo.DIRECTION_FRONT;
        }
        else if(position.equalsIgnoreCase("left")){
            column = TableData.TableInfo.DIRECTION_LEFT;
        }
        else if(position.equalsIgnoreCase("right")){
            column = TableData.TableInfo.DIRECTION_RIGHT;
        }
        else if(position.equalsIgnoreCase("up")){
            column = TableData.TableInfo.DIRECTION_UP;
        }
        else if(position.equalsIgnoreCase("down")){
            column = TableData.TableInfo.DIRECTION_DOWN;
        }
        else{}

        cv.put(TableData.TableInfo.TIMESTAMP, timestamp);
        cv.put(column, position);
        long k = SQ.insert(TableData.TableInfo.TABLE_NAME,null,cv);
        Log.d("Database Operations","one row inserted " + k);


    }
}