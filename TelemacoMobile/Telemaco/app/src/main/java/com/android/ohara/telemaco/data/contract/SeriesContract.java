package com.android.ohara.telemaco.data.contract;

public final class SeriesContract {
    public static final String TABLE_NAME = "series";

    public static final class Columns {
        public static final String ID       = "id";
        public static final String NAME     = "name";
        public static final String YEAR     = "year";
        public static final String STATUS   = "status";
        public static final String CREATOR  = "creator";
        public static final String CLASSIF  = "classification";
        public static final String GENDER    = "gender";
        public static final String SYNOPSIS = "synopsis";
        public static final String IMAGE    = "image";
    }

    public static final class Creation {
        public static final String CREATE =
            String.format( "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, "+
                            "%s VARCHAR(45) NOT NULL, " +
                            "%s INT(11)     NOT NULL, " +
                            "%s VARCHAR(45) NOT NULL, " +
                            "%s VARCHAR(45) NOT NULL, " +
                            "%s VARCHAR(2)  NOT NULL, " +
                            "%s VARCHAR(30) NOT NULL, " +
                            "%s LONGTEXT    NOT NULL, " +
                            "%s VARCHAR(30) DEFAULT NULL)",
                    TABLE_NAME,
                    Columns.ID,
                    Columns.NAME,
                    Columns.YEAR,
                    Columns.STATUS,
                    Columns.CREATOR,
                    Columns.CLASSIF,
                    Columns.GENDER,
                    Columns.SYNOPSIS,
                    Columns.IMAGE);

        public static String DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
