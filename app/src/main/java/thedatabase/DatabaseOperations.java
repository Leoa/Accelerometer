package thedatabase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
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
        Log.d("Database Operations","Table Created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sdb, int oldVersion, int newVersion) {

    }

    public void putInfo(DatabaseOperations dop, String position, String timestamp , int sum){
        SQLiteDatabase SQ= dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String column = null;
        column = TableData.TableInfo.DIRECTION;
        cv.put(column,position);
        cv.put(TableData.TableInfo.TIMESTAMP, timestamp);
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
}
