package thedatabase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import thedatabase.TableData;


/**
 * Created by Leondria on 12/8/2014.
 */
public class DatabaseOperations  extends SQLiteOpenHelper{

    public static final int database_version = 1;
    public String CREATE_QUERY="CREATE TABLE " + TableData.TableInfo.TABLE_NAME+
            "(" + TableData.TableInfo.DIRECTION + " TEXT,"
            + TableData.TableInfo.TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL);";

    public DatabaseOperations(Context context){
        super(context, TableData.TableInfo.DATABASE_NAME, null, database_version);
        Log.d("Database Operations","Data base created");
    }



    @Override
    public void onCreate(SQLiteDatabase sdb) {
        sdb.execSQL(CREATE_QUERY);
        Log.d("Database Operations","Table Phone Positions Created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sdb, int oldVersion, int newVersion) {
        sdb.execSQL("DROP TABLE IF EXISTS "+TableData.TableInfo.TABLE_NAME);
        onCreate(sdb);


    }

    public void putInfo(DatabaseOperations dop, String position, String timestamp ){
        SQLiteDatabase SQ= dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String column1 = TableData.TableInfo.DIRECTION;
        String column2 = TableData.TableInfo.TIMESTAMP;
        cv.put(column1, position);
        cv.put(column2, timestamp);
        long k = SQ.insert(TableData.TableInfo.TABLE_NAME,null,cv);
        Log.d("Database Operations","one row inserted " + k);
        SQ.close();

    }

    public Cursor getInfo(DatabaseOperations dop){
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] cols = {TableData.TableInfo.DIRECTION,TableData.TableInfo.TIMESTAMP};
        Cursor CR = SQ.query(TableData.TableInfo.TABLE_NAME,cols,null,null,null,null,null);
        return CR;
    }


    // Getting All Positions
    public List<Position> getAllPositions() {
        List<Position> positionList = new ArrayList<Position>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TableData.TableInfo.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Position pos = new Position();
               // pos.setID(cursor.getString(0));
                pos.setPosition(cursor.getString(0));
                pos.setTimestamp(cursor.getString(1));
                // Adding position to list
                positionList.add(pos);
            } while (cursor.moveToNext());
        }

        // return positions list
        return positionList;
    }


    // Getting All Left timestamps
    public List<Position> getAllLeftTimeStamps() {
        List<Position> positionList = new ArrayList<Position>();
        // Select All Query
        String selectQuery =  "SELECT "+TableData.TableInfo.TIMESTAMP+" FROM "+TableData.TableInfo.TABLE_NAME+" WHERE "+TableData.TableInfo.DIRECTION+"  = 'left'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Position pos = new Position();
                // pos.setID(cursor.getString(0));
               // pos.setPosition(cursor.getString(0));
                pos.setTimestamp(cursor.getString(0));
                // Adding position to list
                positionList.add(pos);
            } while (cursor.moveToNext());
        }

        // return positions list
        return positionList;
    }
    // Getting All Right timestamps
    public List<Position> getAllRightTimeStamps() {
        List<Position> positionList = new ArrayList<Position>();
        // Select All Query
        String selectQuery =  "SELECT "+TableData.TableInfo.TIMESTAMP+" FROM "+TableData.TableInfo.TABLE_NAME+" WHERE "+TableData.TableInfo.DIRECTION+"  = 'right'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Position pos = new Position();
                // pos.setID(cursor.getString(0));
                // pos.setPosition(cursor.getString(0));
                pos.setTimestamp(cursor.getString(0));
                // Adding position to list
                positionList.add(pos);
            } while (cursor.moveToNext());
        }

        // return positions list
        return positionList;
    }

    // Getting All Up timestamps
    public List<Position> getAllUpTimeStamps() {
        List<Position> positionList = new ArrayList<Position>();
        // Select All Query
        String selectQuery =  "SELECT "+TableData.TableInfo.TIMESTAMP+" FROM "+TableData.TableInfo.TABLE_NAME+" WHERE "+TableData.TableInfo.DIRECTION+"  = 'up'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Position pos = new Position();
                // pos.setID(cursor.getString(0));
                // pos.setPosition(cursor.getString(0));
                pos.setTimestamp(cursor.getString(0));
                // Adding position to list
                positionList.add(pos);
            } while (cursor.moveToNext());
        }

        // return positions list
        return positionList;
    }

    // Getting All Down timestamps
    public List<Position> getAllDownTimeStamps() {
        List<Position> positionList = new ArrayList<Position>();
        // Select All Query
        String selectQuery =  "SELECT "+TableData.TableInfo.TIMESTAMP+" FROM "+TableData.TableInfo.TABLE_NAME+" WHERE "+TableData.TableInfo.DIRECTION+"  = 'down'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Position pos = new Position();
                // pos.setID(cursor.getString(0));
                // pos.setPosition(cursor.getString(0));
                pos.setTimestamp(cursor.getString(0));
                // Adding position to list
                positionList.add(pos);
            } while (cursor.moveToNext());
        }

        // return positions list
        return positionList;
    }
    // Getting All front timestamps
    public List<Position> getAllFrontTimeStamps() {
        List<Position> positionList = new ArrayList<Position>();
        // Select All Query
        String selectQuery =  "SELECT "+TableData.TableInfo.TIMESTAMP+" FROM "+TableData.TableInfo.TABLE_NAME+" WHERE "+TableData.TableInfo.DIRECTION+"  = 'front'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Position pos = new Position();
                // pos.setID(cursor.getString(0));
                // pos.setPosition(cursor.getString(0));
                pos.setTimestamp(cursor.getString(0));
                // Adding position to list
                positionList.add(pos);
            } while (cursor.moveToNext());
        }

        // return positions list
        return positionList;
    }

    // Getting All back timestamps
    public List<Position> getAllBackTimeStamps() {
        List<Position> positionList = new ArrayList<Position>();
        // Select All Query
        String selectQuery =  "SELECT "+TableData.TableInfo.TIMESTAMP+" FROM "+TableData.TableInfo.TABLE_NAME+" WHERE "+TableData.TableInfo.DIRECTION+"  = 'back'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Position pos = new Position();
                // pos.setID(cursor.getString(0));
                // pos.setPosition(cursor.getString(0));
                pos.setTimestamp(cursor.getString(0));
                // Adding position to list
                positionList.add(pos);
            } while (cursor.moveToNext());
        }

        // return positions list
        return positionList;
    }

    // get sum positions left
    public int getSumLeft() {
    String selectQuery =  "SELECT COUNT(*) FROM "+TableData.TableInfo.TABLE_NAME+" WHERE "+TableData.TableInfo.DIRECTION+"  = 'left'";
        int sum=0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            sum = cursor.getInt(0);
        }
        cursor.close();
        return sum;
    }


    // get sum positions right
    public int getSumRight(){
        String selectQuery =  "SELECT COUNT(*) FROM "+TableData.TableInfo.TABLE_NAME+" WHERE "+TableData.TableInfo.DIRECTION+"  = 'right'";
        int sum=0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            sum = cursor.getInt(0);
        }
        cursor.close();
        return sum;
    }


    // get sum positions up
    public int getSumUp(){
        String selectQuery =  "SELECT COUNT(*) FROM "+TableData.TableInfo.TABLE_NAME+" WHERE "+TableData.TableInfo.DIRECTION+"  = 'up'";
        int sum=0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            sum = cursor.getInt(0);
        }
        cursor.close();
        return sum;
    }

    // get sum positions down
    public int getSumDown(){
        String selectQuery =  "SELECT COUNT(*) FROM "+TableData.TableInfo.TABLE_NAME+" WHERE "+TableData.TableInfo.DIRECTION+"  = 'down'";
        int sum=0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            sum = cursor.getInt(0);
        }
        cursor.close();
        return sum;
    }


    // get sum positions front
    public int getSumFront(){
        String selectQuery =  "SELECT COUNT(*) FROM "+TableData.TableInfo.TABLE_NAME+" WHERE "+TableData.TableInfo.DIRECTION+"  = 'front'";
        int sum=0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            sum = cursor.getInt(0);
        }
        cursor.close();
        return sum;
    }

    // get sum positions back
    public int getSumBack(){
        String selectQuery =  "SELECT COUNT(*) FROM "+TableData.TableInfo.TABLE_NAME+" WHERE "+TableData.TableInfo.DIRECTION+"  = 'back'";
        int sum=0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            sum = cursor.getInt(0);
        }
        cursor.close();
        return sum;
    }

    public void deleteRows(){

        String selectQuery="DELETE FROM "+ TableData.TableInfo.TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
    }





}
