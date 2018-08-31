package br.imd.ufrn.database.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private static ProdutoDAO instance;
    private SQLiteDatabase db;

    private ProdutoDAO (Context context) {
        DBHelper dbHelper = DBHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
    }

    public static ProdutoDAO getInstance(Context context) {
        if (instance == null) instance = new ProdutoDAO(context.getApplicationContext());
        return instance;
    }

    public List<Produto> list () {
        String[] colums = {
                ProdutosContract.Columns._ID,
                ProdutosContract.Columns.NOME,
                ProdutosContract.Columns.VALOR
        };

        List<Produto> produtos = new ArrayList<>();
        try ( Cursor c = db.query(ProdutosContract.TABLE_NAME, colums, null, null, null, null, ProdutosContract.Columns.NOME)) {
            if (c.moveToFirst()) {
                do {
                    produtos.add(fromCursor(c));
                } while (c.moveToNext());
            }
        }
        return produtos;
    }

    private static Produto fromCursor (Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(ProdutosContract.Columns._ID));
        String nome = cursor.getString(cursor.getColumnIndex(ProdutosContract.Columns.NOME));
        Double valor = cursor.getDouble(cursor.getColumnIndex(ProdutosContract.Columns.VALOR));

        return new Produto(id, nome, valor);
    }

    public void save (Produto p) {
        ContentValues values = new ContentValues();
        values.put(ProdutosContract.Columns.NOME, p.getNome());
        values.put(ProdutosContract.Columns.VALOR, p.getValor());

        db.insert(ProdutosContract.TABLE_NAME, null, values);
    }

    public void update (Produto p) {
        ContentValues values = new ContentValues();
        values.put(ProdutosContract.Columns.NOME, p.getNome());
        values.put(ProdutosContract.Columns.VALOR, p.getValor());

        db.update(ProdutosContract.TABLE_NAME, values, ProdutosContract.Columns._ID + " =?",
                new String[] {String.valueOf(p.getId())});
    }

    public void delete (Produto p) {
        ContentValues values = new ContentValues();
        values.put(ProdutosContract.Columns.NOME, p.getNome());
        values.put(ProdutosContract.Columns.VALOR, p.getValor());

        db.delete(ProdutosContract.TABLE_NAME, ProdutosContract.Columns._ID + " =?",
                new String[] {String.valueOf(p.getId())});
    }
}