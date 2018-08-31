package com.android.ohara.telemaco.data.contract;

public class UserSeasonContract {
    public static final String TABLE_NAME = "user_season";

    public static final class Columns {
        public static final String ID_FK_USER   = "id_fk_user";
        public static final String ID_FK_SEASON = "id_fk_season";
    }

    public static final class Creation {
        public static final String CREATE =
                String.format( "CREATE TABLE %s ("+
                                "%s INT(11)      NOT NULL, " +
                                "%s INT(11)      NOT NULL, " +
                                "CONSTRAINT `%s` FOREIGN KEY (`%s`) REFERENCES `%s` (`%s`)" +
                                "CONSTRAINT `%s` FOREIGN KEY (`%s`) REFERENCES `%s` (`%s`))",
                        TABLE_NAME,
                        Columns.ID_FK_USER,
                        Columns.ID_FK_SEASON,

                        Columns.ID_FK_USER,
                        Columns.ID_FK_USER,
                        UserContract.TABLE_NAME,
                        UserContract.Columns.ID,

                        Columns.ID_FK_SEASON,
                        Columns.ID_FK_SEASON,
                        SeasonContract.TABLE_NAME,
                        SeasonContract.Columns.ID);

        public static String DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
