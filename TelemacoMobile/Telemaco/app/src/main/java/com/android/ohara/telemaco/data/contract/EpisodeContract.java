package com.android.ohara.telemaco.data.contract;

public final class EpisodeContract {
    public static final String TABLE_NAME = "episode";

    public static final class Columns {
        public static final String ID           = "id";
        public static final String NAME         = "name";
        public static final String SYNOPSIS     = "synopsis";
        public static final String TIME         = "time";
        public static final String NUMBER       = "number";
        public static final String ID_FK_SEASON = "id_fk_season";
    }

    public static final class Creation {
        public static final String CREATE =
                String.format( "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                "%s VARCHAR(45)  NOT NULL, " +
                                "%s LONGTEXT     NOT NULL, " +
                                "%s INT(11)      NOT NULL, " +
                                "%s INT(11)      NOT NULL, " +
                                "%s INT(11)      NOT NULL, " +
                                "CONSTRAINT `%s` FOREIGN KEY (`%s`) REFERENCES `%s` (`%s`))",
                        TABLE_NAME,
                        Columns.ID,
                        Columns.NAME,
                        Columns.SYNOPSIS,
                        Columns.TIME,
                        Columns.NUMBER,
                        Columns.ID_FK_SEASON,

                        Columns.ID_FK_SEASON,
                        Columns.ID_FK_SEASON,
                        SeasonContract.TABLE_NAME,
                        SeasonContract.Columns.ID);

        public static String DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
