package thedatabase;

import android.provider.BaseColumns;

/**
 * Created by Leondria on 12/8/2014.
 */
public class TableData {

    public TableData(){

    }

    public static abstract class TableInfo implements BaseColumns{

        public static final String DIRECTION_LEFT = "left";
        public static final String DIRECTION_RIGHT = "right";
        public static final String DIRECTION_UP = "up";
        public static final String DIRECTION_DOWN = "down";
        public static final String DIRECTION_FRONT = "front";
        public static final String DIRECTION_BACK = "back";
        public static final String DIRECTION_SHAKE= "shake";
        public static final String DATABASE_NAME = "Phone_Positions";
        public static final String TABLE_NAME = "Positions";
        public static final String TIMESTAMP = "timestamp";
        public static final String SUM = "sum";


    }
}

