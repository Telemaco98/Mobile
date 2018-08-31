package com.android.ohara.telemaco.data.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.ohara.telemaco.data.DBHelper;
import com.android.ohara.telemaco.data.contract.EpisodeContract;
import com.android.ohara.telemaco.entity.Episode;
import java.util.ArrayList;

/**
 * Class that represent the table episode of the database telemaco
 *
 * @author  Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @version 28/06/2018
 */
public class EpisodeDAO implements DAOEpisodeSpecialOperations {
    private static EpisodeDAO epDAO = null;
    private SQLiteDatabase db;

    /**
     * Default constructor
     */
    private EpisodeDAO (Context context) {
        DBHelper dbHelper = DBHelper.getInstance( context );
        db = dbHelper.getWritableDatabase();
    }

    /**
     * @return epDAO
     */
    public static synchronized EpisodeDAO getInstance ( Context context ) {
        if (epDAO == null) epDAO = new EpisodeDAO(context);
        return epDAO;
    }

    @Override
    public void insert (Episode episode) {
        ContentValues values = new ContentValues();
        values.put( EpisodeContract.Columns.NAME,         episode.getName() );
        values.put( EpisodeContract.Columns.NUMBER,       episode.getNumber() );
        values.put( EpisodeContract.Columns.TIME,         episode.getTime() );
        values.put( EpisodeContract.Columns.SYNOPSIS,     episode.getSynopsis() );
        values.put( EpisodeContract.Columns.ID_FK_SEASON, episode.getIdSeason() );

        db.insert( EpisodeContract.TABLE_NAME, null, values );
    }

    @Override
    public Episode select(int id) {
        String[] columns = {
            EpisodeContract.Columns.ID,
            EpisodeContract.Columns.NAME,
            EpisodeContract.Columns.NUMBER,
            EpisodeContract.Columns.TIME,
            EpisodeContract.Columns.SYNOPSIS,
            EpisodeContract.Columns.ID_FK_SEASON
        };

        Episode episode = null;

        try (Cursor c = db.query( EpisodeContract.TABLE_NAME, columns, EpisodeContract.Columns.ID + "=?", new String[]{String.valueOf(id)}, null, null, null)) {
            if (c.moveToFirst())
                episode = EpisodeDAO.fromCursor (c);
        }

        return episode;
    }

    public static Episode fromCursor (Cursor c) {
        int id          = c.getInt( c.getColumnIndex( EpisodeContract.Columns.ID));
        String name     = c.getString( c.getColumnIndex( EpisodeContract.Columns.NAME));
        int number      = c.getInt( c.getColumnIndex( EpisodeContract.Columns.NUMBER));
        int time        = c.getInt( c.getColumnIndex( EpisodeContract.Columns.TIME));
        String synopsis = c.getString( c.getColumnIndex( EpisodeContract.Columns.SYNOPSIS));
        int id_season   = c.getInt( c.getColumnIndex( EpisodeContract.Columns.ID_FK_SEASON));

        return new Episode (id, name, number, time, synopsis, id_season);
    }

    @Override
    public Episode select(String name, int idSeason) {
        String[] columns = {
                EpisodeContract.Columns.ID,
                EpisodeContract.Columns.NAME,
                EpisodeContract.Columns.NUMBER,
                EpisodeContract.Columns.TIME,
                EpisodeContract.Columns.SYNOPSIS,
                EpisodeContract.Columns.ID_FK_SEASON
        };

        Episode episode = null;

        String selection =  EpisodeContract.Columns.NAME + "=? AND " + EpisodeContract.Columns.ID_FK_SEASON + "=?";
        try (Cursor c = db.query( EpisodeContract.TABLE_NAME, columns, selection, new String[]{name, String.valueOf(idSeason)}, null, null, null)) {
            if (c.moveToFirst())
                episode = EpisodeDAO.fromCursor (c);
        }

        return episode;
    }

    @Override
    public Episode select(int number, int idSeason) {
        String[] columns = {
                EpisodeContract.Columns.ID,
                EpisodeContract.Columns.NAME,
                EpisodeContract.Columns.NUMBER,
                EpisodeContract.Columns.TIME,
                EpisodeContract.Columns.SYNOPSIS,
                EpisodeContract.Columns.ID_FK_SEASON
        };

        Episode episode = null;

        String selection =  EpisodeContract.Columns.NUMBER + "=? AND " + EpisodeContract.Columns.ID_FK_SEASON + "=?";
        try (Cursor c = db.query( EpisodeContract.TABLE_NAME, columns, selection, new String[]{String.valueOf(number), String.valueOf(idSeason)}, null, null, null)) {
            if (c.moveToFirst())
                episode = EpisodeDAO.fromCursor (c);
        }

        return episode;
    }

    @Override
    public ArrayList<Episode> selectAllEpisodes(int idSeason) {
        String[] columns = {
                EpisodeContract.Columns.ID,
                EpisodeContract.Columns.NAME,
                EpisodeContract.Columns.NUMBER,
                EpisodeContract.Columns.TIME,
                EpisodeContract.Columns.SYNOPSIS,
                EpisodeContract.Columns.ID_FK_SEASON
        };

        ArrayList<Episode> episodes = new ArrayList<>();

        String selection =  EpisodeContract.Columns.ID_FK_SEASON + "=?";
        try (Cursor c = db.query( EpisodeContract.TABLE_NAME, columns, selection, new String[]{String.valueOf(idSeason)}, null, null, null)) {
            if (c.moveToFirst()) {
                episodes = new ArrayList<>();
                do {
                    Episode episode = EpisodeDAO.fromCursor( c );
                    episodes.add( episode );
                } while (c.moveToNext());
            }
        }

        return episodes;
    }

    @Override
    public void delete(Episode episode) {
        db.delete( EpisodeContract.TABLE_NAME, EpisodeContract.Columns.ID+"=?", new String[]{String.valueOf( episode.getId() )} );
    }

    @Override
    public void update(Episode episode) {
        ContentValues values = new ContentValues();
        values.put( EpisodeContract.Columns.NAME,         episode.getName() );
        values.put( EpisodeContract.Columns.NUMBER,       episode.getNumber() );
        values.put( EpisodeContract.Columns.TIME,         episode.getTime() );
        values.put( EpisodeContract.Columns.SYNOPSIS,     episode.getSynopsis() );
        values.put( EpisodeContract.Columns.ID_FK_SEASON, episode.getIdSeason() );

        db.update( EpisodeContract.TABLE_NAME, values, EpisodeContract.Columns.ID + "=?", new String[]{String.valueOf( episode.getId() )});
    }
}