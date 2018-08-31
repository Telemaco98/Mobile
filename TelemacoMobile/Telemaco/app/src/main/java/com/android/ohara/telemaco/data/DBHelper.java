package com.android.ohara.telemaco.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.ohara.telemaco.data.contract.EpisodeContract;
import com.android.ohara.telemaco.data.contract.RatingContract;
import com.android.ohara.telemaco.data.contract.SeasonContract;
import com.android.ohara.telemaco.data.contract.SeriesContract;
import com.android.ohara.telemaco.data.contract.UserContract;
import com.android.ohara.telemaco.data.contract.UserEpisodeContract;
import com.android.ohara.telemaco.data.contract.UserSeasonContract;
import com.android.ohara.telemaco.data.contract.UserSeriesContract;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "telemaco";
    public static final int DB_VERSION = 1;

    private static String SQL_DROP_USER          = UserContract.Creation.DROP;
    private static String SQL_DROP_SERIES        = SeriesContract.Creation.DROP;
    private static String SQL_DROP_SEASON        = SeasonContract.Creation.DROP;
    private static String SQL_DROP_EPISODE       = EpisodeContract.Creation.DROP;
    private static String SQL_DROP_RATING        = RatingContract.Creation.DROP;
    private static String SQL_DROP_USER_SERIES   = UserSeriesContract.Creation.DROP;
    private static String SQL_DROP_USER_SEASON   = UserSeasonContract.Creation.DROP;
    private static String SQL_DROP_USER_EPISODES = UserEpisodeContract.Creation.DROP;

    private static String SQL_CREATE_USER         = UserContract.Creation.CREATE;
    private static String SQL_CREATE_SERIES       = SeriesContract.Creation.CREATE;
    private static String SQL_CREATE_SEASON       = SeasonContract.Creation.CREATE;
    private static String SQL_CREATE_EPISODE      = EpisodeContract.Creation.CREATE;
    private static String SQL_CREATE_RATING       = RatingContract.Creation.CREATE;
    private static String SQL_CREATE_USER_SERIES  = UserSeriesContract.Creation.CREATE;
    private static String SQL_CREATE_USER_SEASON  = UserSeasonContract.Creation.CREATE;
    private static String SQL_CREATE_USER_EPISODE = UserEpisodeContract.Creation.CREATE;

    private static DBHelper instance;

    private DBHelper(Context context) {
        super( context, DB_NAME, null, DB_VERSION);
    }

    public static DBHelper getInstance (Context context) {
        if(instance == null) instance = new DBHelper(context);
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( SQL_DROP_USER);
        db.execSQL( SQL_DROP_SERIES );
        db.execSQL( SQL_DROP_SEASON );
        db.execSQL( SQL_DROP_EPISODE );
        db.execSQL( SQL_DROP_RATING );
        db.execSQL( SQL_DROP_USER_SERIES );
        db.execSQL( SQL_DROP_USER_SEASON );
        db.execSQL( SQL_DROP_USER_EPISODES );

        db.execSQL( SQL_CREATE_USER );
        db.execSQL( SQL_CREATE_SERIES );
        db.execSQL( SQL_CREATE_SEASON );
        db.execSQL( SQL_CREATE_EPISODE );
        db.execSQL( SQL_CREATE_RATING );
        db.execSQL( SQL_CREATE_USER_SERIES );
        db.execSQL( SQL_CREATE_USER_SEASON );
        db.execSQL( SQL_CREATE_USER_EPISODE );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate( db );
    }

}
