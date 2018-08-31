package br.imd.ufrn.database.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Shirley Ohara Telemaco de Freitas
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "productsdb";
    public static final int DB_VERSION = 1;

    private static String SQL_DROP = "DROP TABLE IF EXISTS " + ProdutosContract.TABLE_NAME;
    private static String SQL_CREATE = String.format("CREATE TABLE %s " +
            "(%s INTEGER PRIMARY KEY AUTOINCREMENT," +
            " %s TEXT NOT NULL," +
            " %s DOUBLE NOT NULL)",
            ProdutosContract.TABLE_NAME,
            ProdutosContract.Columns._ID,
            ProdutosContract.Columns.NOME,
            ProdutosContract.Columns.VALOR);

    private static DBHelper instance;

    private DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static DBHelper getInstance (Context context) {
        if (instance == null) instance = new DBHelper(context);
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_DROP);
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
