package com.android.ohara.telemaco.data.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.ohara.telemaco.data.DBHelper;
import com.android.ohara.telemaco.data.contract.UserContract;
import com.android.ohara.telemaco.data.contract.UserEpisodeContract;
import com.android.ohara.telemaco.data.contract.UserSeriesContract;
import com.android.ohara.telemaco.entity.Episode;
import com.android.ohara.telemaco.entity.Serie;
import com.android.ohara.telemaco.entity.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author  Shirley Ohara Telemaco de Freitas (shirleyohara@ufrn.edu.br)
 * @version 28/06/2018
 */
public class UserDAO implements DAOUserSpecialOperations {
    private static UserDAO userDAO = null;
    private SQLiteDatabase db;
    private Context context;
    
    private UserDAO ( Context context ) {
        DBHelper dbHelper = DBHelper.getInstance( context );
        this.context = context;
        db = dbHelper.getWritableDatabase();
    }
    
    /**
     * @return userDAO
     */
    public static synchronized UserDAO getInstance ( Context context ) {
        if(userDAO == null)
            userDAO = new UserDAO(context);
        return userDAO;
    }
    
    @Override
    public void insert(User user) {
        java.sql.Date date = new java.sql.Date(user.getBirth().getTime());

        ContentValues values = new ContentValues();
        values.put( UserContract.Columns.NAME,      user.getName());
        values.put( UserContract.Columns.EMAIL,     user.getEmail());
        values.put( UserContract.Columns.PSW,       user.getPassword());
        values.put( UserContract.Columns.LAST_NAME, user.getLastName());
        values.put( UserContract.Columns.BIRTHDAY,  date.toString());
        values.put( UserContract.Columns.GENDER,    user.getGender());

        db.insert( UserContract.TABLE_NAME, null, values );
    }
    
    @Override
    public User select(int id) {
        String[] columns = {
            UserContract.Columns.ID,
            UserContract.Columns.NAME,
            UserContract.Columns.LAST_NAME,
            UserContract.Columns.EMAIL,
            UserContract.Columns.PSW,
            UserContract.Columns.BIRTHDAY,
            UserContract.Columns.GENDER
        };

        User user = null;
        try (Cursor cursor = db.query( UserContract.TABLE_NAME, columns, UserContract.Columns.ID + "=?", new String[]{String.valueOf( id )}, null, null, null)) {
            if (cursor.moveToNext()) {
                user = UserDAO.fromCursor (cursor);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return user;
    }

    public ArrayList<User> selectAll () {
        String[] columns = {
                UserContract.Columns.ID,
                UserContract.Columns.NAME,
                UserContract.Columns.LAST_NAME,
                UserContract.Columns.EMAIL,
                UserContract.Columns.PSW,
                UserContract.Columns.BIRTHDAY,
                UserContract.Columns.GENDER
        };

        ArrayList<User> users = new ArrayList<>();

        try (Cursor c = db.query( UserContract.TABLE_NAME, columns, null, null, null, null, UserContract.Columns.NAME)) {
            if (c.moveToNext()) {
                do {
                    User user = UserDAO.fromCursor (c);
                    users.add(user);
                } while (c.moveToNext());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return users;
    }

    private static User fromCursor ( Cursor c ) throws ParseException {
        int id          = c.getInt( c.getColumnIndex( UserContract.Columns.ID ));
        String name     = c.getString( c.getColumnIndex( UserContract.Columns.NAME ));
        String l_name   = c.getString( c.getColumnIndex( UserContract.Columns.LAST_NAME ));
        String email    = c.getString( c.getColumnIndex( UserContract.Columns.EMAIL ));
        String psw      = c.getString( c.getColumnIndex( UserContract.Columns.PSW ));
        String birthday = c.getString( c.getColumnIndex( UserContract.Columns.BIRTHDAY ));
        String gender   = c.getString( c.getColumnIndex( UserContract.Columns.GENDER ));

        DateFormat formato = new SimpleDateFormat( "yyyy-MM-dd");
        Date birthday_Date = formato.parse(birthday);

        return new User (id, name, l_name, email, psw, birthday_Date, gender);
    }

    @Override
    public void delete(User user) {
        db.delete( UserContract.TABLE_NAME, UserContract.Columns.ID + "=?", new String[]{String.valueOf(user.getId())} );
    }

    @Override
    public void update(User user) {

    }

    @Override
    public User select(String email, String password) {
        User user = select( email );
        if ((user.getPassword()).equals(password)) return user;
        return null;
    }

    @Override
    public User select(String email) {
        String[] columns = {
                UserContract.Columns.ID,
                UserContract.Columns.NAME,
                UserContract.Columns.LAST_NAME,
                UserContract.Columns.EMAIL,
                UserContract.Columns.PSW,
                UserContract.Columns.BIRTHDAY,
                UserContract.Columns.GENDER
        };

        User user = null;
        try (Cursor cursor = db.query( UserContract.TABLE_NAME, columns, UserContract.Columns.EMAIL + "=?", new String[]{String.valueOf( email )}, null, null, null)) {
            if (cursor.moveToNext()) {
                user = UserDAO.fromCursor (cursor);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void addSeries (int idUser, int idSerie) {
        ContentValues values = new ContentValues();
        values.put ( UserSeriesContract.Columns.ID_FK_USER, idUser );
        values.put ( UserSeriesContract.Columns.ID_FK_SERIES, idSerie );

        db.insert( UserSeriesContract.TABLE_NAME, null, values );
    }

    @Override
    public ArrayList<Serie> selectSeriesByUser(int id) {
        ArrayList<Serie> series = null;

        String[] columns = {
          UserSeriesContract.Columns.ID_FK_USER,
          UserSeriesContract.Columns.ID_FK_SERIES
        };

        try (Cursor c = db.query( UserSeriesContract.TABLE_NAME, columns, UserSeriesContract.Columns.ID_FK_USER + "=?", new String[]{String.valueOf(String.valueOf( id ))}, null, null, null)) {
            if (c.moveToFirst()) {
                series = new ArrayList<>();
                SerieDAO serieDAO = SerieDAO.getInstance(context);

                do {
                    int idSerie = c.getInt(c.getColumnIndex( UserSeriesContract.Columns.ID_FK_SERIES ));
                    Serie serie = serieDAO.select( idSerie );
                    series.add(serie);
                } while (c.moveToNext());
            }
        }

        return series;
    }

    @Override
    public void removeSeries (int idUser, int idSerie) {
        String whereClause = UserSeriesContract.Columns.ID_FK_USER + "=? and " + UserSeriesContract.Columns.ID_FK_SERIES + "=?";
        String[] whereArgs = new String[]{String.valueOf( idUser ), String.valueOf( idSerie )};
        db.delete( UserSeriesContract.TABLE_NAME, whereClause, whereArgs);
    }

    @Override
    public void addEpisode(int idUser, int idEpisode) {
        ContentValues values = new ContentValues();
        values.put ( UserEpisodeContract.Columns.ID_FK_USER, idUser );
        values.put ( UserEpisodeContract.Columns.ID_FK_EPISODE, idEpisode );

        db.insert( UserEpisodeContract.TABLE_NAME, null, values );
    }

    @Override
    public void removeEpisode(int idUser, int idEpisode) {
        String whereClause = UserEpisodeContract.Columns.ID_FK_USER + "=? and " + UserEpisodeContract.Columns.ID_FK_EPISODE + "=?";
        String[] whereArgs = new String[]{String.valueOf( idUser ), String.valueOf( idEpisode )};
        db.delete( UserEpisodeContract.TABLE_NAME, whereClause, whereArgs);
    }

    @Override
    public ArrayList<Episode> selectEpisodesByUser(int idUser) {
        String[] columns = {
                UserEpisodeContract.Columns.ID_FK_USER,
                UserEpisodeContract.Columns.ID_FK_EPISODE
        };

        ArrayList<Episode> episodes = null;

        String selection = UserSeriesContract.Columns.ID_FK_USER + "=?";
        String[] selectionArgs = {String.valueOf(String.valueOf( idUser ))};
        try (Cursor c = db.query( UserEpisodeContract.TABLE_NAME, columns, selection, selectionArgs, null, null, null)) {
            if (c.moveToFirst()) {
                episodes = new ArrayList<>();
                EpisodeDAO episodeDAO = EpisodeDAO.getInstance(context);

                do {
                    int idEpsiode = c.getInt(c.getColumnIndex( UserEpisodeContract.Columns.ID_FK_EPISODE ));
                    Episode episode = episodeDAO.select( idEpsiode );
                    episodes.add(episode);
                } while (c.moveToNext());
            }
        }

        return episodes;
    }
}