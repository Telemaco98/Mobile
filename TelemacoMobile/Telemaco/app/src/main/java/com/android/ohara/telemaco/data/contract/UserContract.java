package com.android.ohara.telemaco.data.contract;

public final class UserContract {
    public static final String TABLE_NAME = "user";

    public static final class Columns {
        public static final String ID        = "id";
        public static final String NAME      = "name";
        public static final String LAST_NAME = "last_name";
        public static final String EMAIL     = "email";
        public static final String PSW       = "password";
        public static final String BIRTHDAY  = "birthday";
        public static final String GENDER    = "gender";
    }

    public static final class Creation {
        public static final String CREATE =
            String.format( "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                        "%s VARCHAR(45) NOT NULL, " +
                                        "%s VARCHAR(45) NOT NULL, " +
                                        "%s VARCHAR(45) NOT NULL, " +
                                        "%s VARCHAR(45) NOT NULL, " +
                                        "%s DATE        NOT NULL, " +
                                        "%s VARCHAR(1)  NOT NULL)",
                TABLE_NAME,
                Columns.ID,
                Columns.NAME,
                Columns.LAST_NAME,
                Columns.EMAIL,
                Columns.PSW,
                Columns.BIRTHDAY,
                Columns.GENDER);

        public static String DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
