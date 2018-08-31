package com.android.ohara.telemaco.data.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.android.ohara.telemaco.data.DBHelper;
import com.android.ohara.telemaco.data.contract.SeriesContract;
import com.android.ohara.telemaco.entity.Season;
import com.android.ohara.telemaco.entity.Serie;
import java.util.ArrayList;

/**
 *
 * @author  Shirley Ohara Telemaco de Freitas (shirleyohara@ufrn.edu.br)
 * @version 26/06/2018
 */
public class SerieDAO implements DAOSerieSpecialOperations {
    private static SerieDAO serieDAO = null;
    private SQLiteDatabase db;
    
    private SerieDAO(Context context) {
        DBHelper dbHelper = DBHelper.getInstance( context );
        db = dbHelper.getWritableDatabase();
    }

    public static synchronized SerieDAO getInstance(Context context) {
        if(serieDAO == null)
            serieDAO = new SerieDAO(context);
        return serieDAO;
    }
    
    @Override
    public void insert(Serie series){
        ContentValues values = new ContentValues();
        values.put( SeriesContract.Columns.NAME,     series.getName() );
        values.put( SeriesContract.Columns.YEAR,     series.getYear() );
        values.put( SeriesContract.Columns.STATUS,   series.getStatus() );
        values.put( SeriesContract.Columns.CREATOR,  series.getCreator() );
        values.put( SeriesContract.Columns.CLASSIF,  series.classifToString() );
        values.put( SeriesContract.Columns.GENDER,   series.getGenre() );
        values.put( SeriesContract.Columns.SYNOPSIS, series.getSynopsis() );
        values.put( SeriesContract.Columns.IMAGE,    series.getImage() );

        db.insert(SeriesContract.TABLE_NAME, null, values);
    }
    
    @Override
    public Serie select(int id) {
        String[] columns = {
            SeriesContract.Columns.ID,
            SeriesContract.Columns.NAME,
            SeriesContract.Columns.YEAR,
            SeriesContract.Columns.STATUS,
            SeriesContract.Columns.CREATOR,
            SeriesContract.Columns.CLASSIF,
            SeriesContract.Columns.GENDER,
            SeriesContract.Columns.SYNOPSIS,
            SeriesContract.Columns.IMAGE
        };

        Serie serie = null;

        try (Cursor cursor = db.query( SeriesContract.TABLE_NAME, columns, SeriesContract.Columns.ID +" = ?", new String[]{String.valueOf(id)}, null, null, null)){
            if (cursor.moveToFirst())
                serie = SerieDAO.fromCursor (cursor);
        }
        return serie;
    }
    

    @Override
    public Serie select (String name) {
        String[] columns = {
                SeriesContract.Columns.ID,
                SeriesContract.Columns.NAME,
                SeriesContract.Columns.YEAR,
                SeriesContract.Columns.STATUS,
                SeriesContract.Columns.CREATOR,
                SeriesContract.Columns.CLASSIF,
                SeriesContract.Columns.GENDER,
                SeriesContract.Columns.SYNOPSIS,
                SeriesContract.Columns.IMAGE
        };

        Serie serie = null;

        try (Cursor cursor = db.query( SeriesContract.TABLE_NAME, columns, SeriesContract.Columns.NAME + "=?", new String[]{name}, null, null, SeriesContract.Columns.NAME )){
            if (cursor.moveToFirst())
                serie = SerieDAO.fromCursor (cursor);
        }
        return serie;
    }
    
    @Override
    public ArrayList<Serie> selectAllSeries () {
    	String[] columns = {
            SeriesContract.Columns.ID,
            SeriesContract.Columns.NAME,
            SeriesContract.Columns.YEAR,
            SeriesContract.Columns.STATUS,
            SeriesContract.Columns.CREATOR,
            SeriesContract.Columns.CLASSIF,
            SeriesContract.Columns.GENDER,
            SeriesContract.Columns.SYNOPSIS,
            SeriesContract.Columns.IMAGE
        };

    	ArrayList<Serie> series = new ArrayList<>();

    	try (Cursor cursor = db.query( SeriesContract.TABLE_NAME, columns, null, null, null, null, SeriesContract.Columns.NAME )){
            if (cursor.moveToFirst()) {
                do {
                    Serie serie = SerieDAO.fromCursor (cursor);
                    series.add( serie );
                } while (cursor.moveToNext());
            }
        }
        return series;
    }

    public static Serie fromCursor (Cursor c) {
        int id          = c.getInt( c.getColumnIndex( SeriesContract.Columns.ID ));
        String name     = c.getString( c.getColumnIndex( SeriesContract.Columns.NAME ));
        int year        = c.getInt( c.getColumnIndex( SeriesContract.Columns.YEAR ));
        String status   = c.getString( c.getColumnIndex( SeriesContract.Columns.STATUS ));
        String creator  = c.getString( c.getColumnIndex( SeriesContract.Columns.CREATOR ));
        String classif  = c.getString( c.getColumnIndex( SeriesContract.Columns.CLASSIF ));
        String gender   = c.getString( c.getColumnIndex( SeriesContract.Columns.GENDER ));
        String synopsis = c.getString( c.getColumnIndex( SeriesContract.Columns.SYNOPSIS ));
        String image    = c.getString( c.getColumnIndex( SeriesContract.Columns.IMAGE ));

        ArrayList<Season> seasons = new ArrayList<>( ); // FIXME

        return new Serie(id, name, year, status, creator, classif, gender, synopsis, image, seasons);
    }

    @Override
    public void delete(Serie series) {
        db.delete( SeriesContract.TABLE_NAME, SeriesContract.Columns.ID+"=?", new String[]{String.valueOf(series.getId())});
    }
   
    @Override
    public void update(Serie series) {
        ContentValues values = new ContentValues();
        values.put( SeriesContract.Columns.NAME, series.getName() );
        values.put( SeriesContract.Columns.YEAR, series.getYear() );
        values.put( SeriesContract.Columns.STATUS, series.getStatus() );
        values.put( SeriesContract.Columns.CREATOR, series.getCreator() );
        values.put( SeriesContract.Columns.CLASSIF, series.classifToString() );
        values.put( SeriesContract.Columns.GENDER, series.getGenre() );
        values.put( SeriesContract.Columns.SYNOPSIS, series.getSynopsis() );
        values.put( SeriesContract.Columns.IMAGE, series.getImage() );

        db.update( SeriesContract.TABLE_NAME, values, SeriesContract.Columns.ID + "=?", new String[]{String.valueOf(series.getId())});
    }

    @Override
    public ArrayList<Serie> search(String input) {
        String[] columns = {
                SeriesContract.Columns.ID,
                SeriesContract.Columns.NAME,
                SeriesContract.Columns.YEAR,
                SeriesContract.Columns.STATUS,
                SeriesContract.Columns.CREATOR,
                SeriesContract.Columns.CLASSIF,
                SeriesContract.Columns.GENDER,
                SeriesContract.Columns.SYNOPSIS,
                SeriesContract.Columns.IMAGE
        };

        ArrayList<Serie> series = new ArrayList<>();

        String selection = "LOWER (" + SeriesContract.Columns.NAME + ") LIKE " + "?";
        String[] selectionArgs = {input.toLowerCase()};
        try (Cursor cursor = db.query( SeriesContract.TABLE_NAME, columns, selection, selectionArgs, null, null, SeriesContract.Columns.NAME )){
            if (cursor.moveToFirst()) {
                do {
                    Serie serie = SerieDAO.fromCursor (cursor);
                    series.add( serie );
                } while (cursor.moveToNext());
            }
        }
        return series;
    }
}
