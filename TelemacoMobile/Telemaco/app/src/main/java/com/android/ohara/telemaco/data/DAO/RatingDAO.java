package com.android.ohara.telemaco.data.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.ohara.telemaco.data.DBHelper;
import com.android.ohara.telemaco.data.contract.RatingContract;
import com.android.ohara.telemaco.entity.Rating;
import com.android.ohara.telemaco.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author  Shirley Ohara Telemaco de Freitas (shirleyohara@ufrn.edu.br)
 * @version 28/06/2018
 */
public class RatingDAO implements DAORatingSpecialOperations {
    private static RatingDAO ratingDAO = null;
    private SQLiteDatabase db;
    private static Context context;

    private RatingDAO (Context context) {
        DBHelper dbHelper = DBHelper.getInstance(context);
        this.context = context;
        db = dbHelper.getWritableDatabase();
    }

    public static synchronized RatingDAO getInstance(Context context) {
        if (ratingDAO == null) ratingDAO = new RatingDAO(context);
        return ratingDAO;
    }

    @Override
    public void insert(Rating rating) {
        java.sql.Date date = new java.sql.Date(rating.getDate().getTime());

        ContentValues values = new ContentValues();
        values.put( RatingContract.Columns.DATE,         date.toString());
        values.put( RatingContract.Columns.STARS,        rating.getStars());
        values.put( RatingContract.Columns.RATING,       rating.getComment());
        values.put( RatingContract.Columns.ID_FK_USER,   rating.getUser().getId());
        values.put( RatingContract.Columns.ID_FK_SERIES, rating.getIdSerie());

        db.insert( RatingContract.TABLE_NAME, null, values );
    }

    @Override
    public Rating select(int id) {
        String[] columns = {
            RatingContract.Columns.DATE,
            RatingContract.Columns.STARS,
            RatingContract.Columns.RATING,
            RatingContract.Columns.ID_FK_USER,
            RatingContract.Columns.ID_FK_SERIES
        };

        Rating rating = null;

        try (Cursor c = db.query( RatingContract.TABLE_NAME, columns, RatingContract.Columns.ID +"=?", new String[]{String.valueOf(id)}, null, null, RatingContract.Columns.DATE)){
            if (c.moveToFirst())
                rating = RatingDAO.fromCursor(c);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rating;
    }

    private static Rating fromCursor(Cursor c) throws ParseException {
        int id            = c.getInt( c.getColumnIndex( RatingContract.Columns.ID));
        String dateString = c.getString( c.getColumnIndex( RatingContract.Columns.DATE ));
        int stars         = c.getInt( c.getColumnIndex( RatingContract.Columns.STARS ));
        String comment    = c.getString( c.getColumnIndex( RatingContract.Columns.RATING ));
        int iduser        = c.getInt( c.getColumnIndex( RatingContract.Columns.ID_FK_USER ));
        int idSeries      = c.getInt( c.getColumnIndex( RatingContract.Columns.ID_FK_SERIES ));

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = format.parse(dateString);

        UserDAO userDAO = UserDAO.getInstance( context );
        User user = userDAO.select( iduser );

        return new Rating(id, date, stars, comment, user, idSeries);
    }

    @Override
    public void delete (Rating rating) {
        String whereClause = RatingContract.Columns.ID + "=?";
        String[] whereArgs = {String.valueOf(rating.getId())};
        db.delete( RatingContract.TABLE_NAME, whereClause, whereArgs);
    }

    @Override
    public void update(Rating rating) {
        java.sql.Date date = new java.sql.Date(rating.getDate().getTime());

        ContentValues values = new ContentValues();
        values.put( RatingContract.Columns.DATE,         date.toString());
        values.put( RatingContract.Columns.STARS,        rating.getStars());
        values.put( RatingContract.Columns.RATING,       rating.getComment());
        values.put( RatingContract.Columns.ID_FK_USER,   rating.getUser().getId());
        values.put( RatingContract.Columns.ID_FK_SERIES, rating.getIdSerie());

        String whereClause = RatingContract.Columns.ID + "=?";
        String[] whereArgs = {String.valueOf(rating.getId())};
        db.update( RatingContract.TABLE_NAME, values, whereClause, whereArgs);
    }

    @Override
    public ArrayList<Rating> selectBySerie(int idSerie) {
        String[] columns = {
                RatingContract.Columns.DATE,
                RatingContract.Columns.STARS,
                RatingContract.Columns.RATING,
                RatingContract.Columns.ID_FK_USER,
                RatingContract.Columns.ID_FK_SERIES
        };

        ArrayList<Rating> ratings = new ArrayList<>();

        String selection = RatingContract.Columns.ID_FK_SERIES + "=?";
        String[] selectionArgs = {String.valueOf(idSerie)};
        try (Cursor c = db.query( RatingContract.TABLE_NAME, columns, selection, selectionArgs, null, null, RatingContract.Columns.DATE)){
            if (c.moveToFirst()) {
                do {
                    Rating rating = RatingDAO.fromCursor( c );
                    ratings.add( rating );
                } while (c.moveToNext());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return ratings;
    }
}
