package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Leondria on 12/8/2014.
 *
 * @return yyyy-MM-dd HH:mm:ss formate date as string
 */
public class Timestamp {

    public Timestamp(){}

    public static String getCurrentTimeStamp(){
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTimeStamp = dateFormat.format(new Date()); // Find todays date

            return currentTimeStamp;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }


}
