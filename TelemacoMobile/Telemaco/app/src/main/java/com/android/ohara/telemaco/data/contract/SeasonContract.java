package com.android.ohara.telemaco.data.contract;

public final class SeasonContract {
    public static final String TABLE_NAME = "season";

    public static final class Columns {
        public static final String ID           = "id";
        public static final String NUMBER       = "number";
        public static final String ID_FK_SERIES = "id_fk_series";
    }

    public static final class Creation {
        public static final String CREATE =
                String.format( "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                "%s INT(11) NOT NULL, " +
                                "%s INT(11) NOT NULL, " +
                                "CONSTRAINT `%s` FOREIGN KEY (`%s`) REFERENCES `%s` (`%s`))",
                        TABLE_NAME,
                        Columns.ID,
                        Columns.NUMBER,
                        Columns.ID_FK_SERIES,
                        Columns.ID_FK_SERIES,
                        Columns.ID_FK_SERIES,
                        SeriesContract.TABLE_NAME,
                        SeriesContract.Columns.ID);

        public static String DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
