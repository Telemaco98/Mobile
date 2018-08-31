package com.android.ohara.telemaco.data.contract;

public class RatingContract {
    public static final String TABLE_NAME = "rating";

    public static final class Columns {
        public static final String ID           = "id";
        public static final String DATE         = "date";
        public static final String STARS        = "stars";
        public static final String RATING       = "rating";
        public static final String ID_FK_USER   = "id_fk_user";
        public static final String ID_FK_SERIES = "id_fk_series";
    }

    public static final class Creation {
        public static final String CREATE =
                String.format( "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                "%s DATE         NOT NULL, " +
                                "%s FLOAT        NOT NULL, " +
                                "%s LONGTEXT     NOT NULL, " +
                                "%s INT(11)      NOT NULL, " +
                                "%s INT(11)      NOT NULL, " +
                                "CONSTRAINT `%s` FOREIGN KEY (`%s`) REFERENCES `%s` (`%s`)" +
                                "CONSTRAINT `%s` FOREIGN KEY (`%s`) REFERENCES `%s` (`%s`))",
                        TABLE_NAME,
                        Columns.ID,
                        Columns.DATE,
                        Columns.STARS,
                        Columns.RATING,
                        Columns.ID_FK_USER,
                        Columns.ID_FK_SERIES,

                        Columns.ID_FK_USER,
                        Columns.ID_FK_USER,
                        UserContract.TABLE_NAME,
                        UserContract.Columns.ID,


                        Columns.ID_FK_SERIES,
                        Columns.ID_FK_SERIES,
                        SeriesContract.TABLE_NAME,
                        SeriesContract.Columns.ID);

        public static String DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
