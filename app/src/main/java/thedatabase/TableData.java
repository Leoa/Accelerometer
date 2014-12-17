package thedatabase;

import android.provider.BaseColumns;

/**
 * Created by Leondria on 12/8/2014.
 */
public class TableData {

    public TableData(){

    }

    public static abstract class TableInfo implements BaseColumns{

        public static final String DIRECTION = "directions";
        public static final String DATABASE_NAME = "Phone_Positions";
        public static final String TABLE_NAME = "Positions";
        public static final String TIMESTAMP = "timestamp";



    }
}

