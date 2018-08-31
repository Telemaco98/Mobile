package com.android.ohara.telemaco.data.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.ohara.telemaco.data.DBHelper;
import com.android.ohara.telemaco.data.contract.SeasonContract;
import com.android.ohara.telemaco.entity.Episode;
import com.android.ohara.telemaco.entity.Season;
import java.util.ArrayList;

/**
 * Class that represent the table season of the database telemaco
 *
 * @author Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @version 11 de abr de 2018 | 23:29:36
 */
public class SeasonDAO implements DAOSeasonSpecialOperations {
    private static SeasonDAO seasonDAO = null;
    private SQLiteDatabase db;
    private static Context context;

    /**
     * Default constructor
     */
    public SeasonDAO (Context context) {
        DBHelper dbHelper = DBHelper.getInstance( context );
        this.context = context;
        db = dbHelper.getWritableDatabase();
    }

    /**
     * @return seasonDAO
     */
    public static synchronized SeasonDAO getInstance (Context context) {
        if (seasonDAO == null) seasonDAO = new SeasonDAO(context);
        return seasonDAO;
    }

    @Override
    public void insert(Season season) {
        ContentValues values = new ContentValues();
        values.put( SeasonContract.Columns.NUMBER, season.getNumber());
        values.put( SeasonContract.Columns.ID_FK_SERIES, season.getNumber());

        db.insert( SeasonContract.TABLE_NAME, null, values );
    }

    @Override
    public Season select(int id) {
        String[] columns = {
            SeasonContract.Columns.ID,
            SeasonContract.Columns.NUMBER,
            SeasonContract.Columns.ID_FK_SERIES
        };

        Season season = null;

        String selection = SeasonContract.Columns.ID + "=?";
        String[] selectionArgs = {String.valueOf(id)};
        try (Cursor c = db.query(SeasonContract.TABLE_NAME, columns, selection, selectionArgs, null, null, null)) {
            if (c.moveToFirst())
                season = SeasonDAO.fromCursor(c);
        }

        return season;
    }

    private static Season fromCursor(Cursor c) {
        int id       = c.getInt( c.getColumnIndex( SeasonContract.Columns.ID));
        int number   = c.getInt( c.getColumnIndex( SeasonContract.Columns.ID));
        int idSeries = c.getInt( c.getColumnIndex( SeasonContract.Columns.ID));

        ArrayList<Episode> episodes;
        EpisodeDAO episodeDAO = EpisodeDAO.getInstance(context);
        episodes = episodeDAO.selectAllEpisodes(id);

        return new Season (id, number, episodes, idSeries);
    }

    @Override
    public Season select (int number, int idSerie) {
        String[] columns = {
                SeasonContract.Columns.ID,
                SeasonContract.Columns.NUMBER,
                SeasonContract.Columns.ID_FK_SERIES
        };

        Season season = null;

        String selection = SeasonContract.Columns.NUMBER + "=? AND " + SeasonContract.Columns.ID_FK_SERIES + "=?";
        String[] selectionArgs = {String.valueOf(number), String.valueOf(idSerie)};
        try (Cursor c = db.query(SeasonContract.TABLE_NAME, columns, selection, selectionArgs, null, null, null)) {
            if (c.moveToFirst())
                season = SeasonDAO.fromCursor(c);
        }

        return season;
    }

    @Override
    public ArrayList<Season> selectAllSeasons(int idSerie) {
        String[] columns = {
                SeasonContract.Columns.ID,
                SeasonContract.Columns.NUMBER,
                SeasonContract.Columns.ID_FK_SERIES
        };

        ArrayList<Season> seasons = new ArrayList<> ();

        String selection = SeasonContract.Columns.ID_FK_SERIES + "=?";
        String[] selectionArgs = {String.valueOf(idSerie)};
        try (Cursor c = db.query(SeasonContract.TABLE_NAME, columns, selection, selectionArgs, null, null, null)) {
            if (c.moveToFirst()) {
                do {
                    Season season = SeasonDAO.fromCursor( c );
                    seasons.add(season);
                } while (c.moveToNext());
            }
        }

        return seasons;
    }
    

    @Override
    public void delete(Season season) {
        String whereClause = SeasonContract.Columns.ID + "=?";
        String[] whereArgs = {String.valueOf(season.getId())};
        db.delete( SeasonContract.TABLE_NAME, whereClause,  whereArgs);
    }

    @Override
    public void update(Season season) {
        ContentValues values = new ContentValues();
        values.put( SeasonContract.Columns.NUMBER, season.getNumber());
        values.put( SeasonContract.Columns.ID_FK_SERIES, season.getNumber());

        String whereClause = SeasonContract.Columns.ID + "=?";
        String[] whereArgs = {String.valueOf(season.getId())};
        db.update( SeasonContract.TABLE_NAME, values, whereClause,  whereArgs);
    }
}
