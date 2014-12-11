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
            "(" + TableData.TableInfo.DIRECTION_RIGHT + " INT,"
            + TableData.TableInfo.DIRECTION_LEFT + " INT,"
            + TableData.TableInfo.DIRECTION_UP + " INT,"
            + TableData.TableInfo.DIRECTION_DOWN + " INT,"
            + TableData.TableInfo.DIRECTION_FRONT+ " INT,"
            + TableData.TableInfo.DIRECTION_BACK+  " INT,"
            + TableData.TableInfo.DIRECTION_SHAKE+ " INT,"
            + TableData.TableInfo.SUM+ " INT,"
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
        else{

            column = TableData.TableInfo.DIRECTION_SHAKE;
        }

        cv.put(column,sum);
        cv.put(TableData.TableInfo.TIMESTAMP, timestamp);
        long k = SQ.insert(TableData.TableInfo.TABLE_NAME,null,cv);
        Log.d("Database Operations","one row inserted " + k);
        SQ.close();

    }

    public Cursor getInfo(DatabaseOperations dop){
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] cols = {TableData.TableInfo.DIRECTION_BACK,
                TableData.TableInfo.DIRECTION_FRONT,
                TableData.TableInfo.DIRECTION_UP,
                TableData.TableInfo.DIRECTION_DOWN,
                TableData.TableInfo.DIRECTION_LEFT,
                TableData.TableInfo.DIRECTION_RIGHT,
                TableData.TableInfo.TIMESTAMP,
                TableData.TableInfo.SUM};
        Cursor CR = SQ.query(TableData.TableInfo.TABLE_NAME,cols,null,null,null,null,null);
        return CR;
    }
}
