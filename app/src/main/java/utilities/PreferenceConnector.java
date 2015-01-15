package utilities;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by Leondria on 1/15/2015.
 */
public class PreferenceConnector {

    public static final String PREF_NAME = "PEOPLE_PREFERENCES";
    public static final String DATACOLLECTION_ON_OFF="DATA_ON_OFF";
    public static final String REMOVESPLASH = "REMOVESPLASH";
    public static final int MODE = Context.MODE_PRIVATE;


    public static void writeInteger(Context context, String key, int value) {
        getEditor(context).putInt(key, value).commit();

    }

    public static int readInteger(Context context, String key, int defValue) {
        return getPreferences(context).getInt(key, defValue);
    }

    public static Editor getEditor(Context context) {
        return getPreferences(context).edit();
    }

    public static void writeString(Context context, String key, String value) {
        getEditor(context).putString(key, value).commit();

    }

    public static String readString(Context context, String key, String defValue) {
        return getPreferences(context).getString(key, defValue);
    }


    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, MODE);
    }
}
