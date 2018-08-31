package br.imd.ufrn.database.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.imd.ufrn.database.R;
import br.imd.ufrn.database.data.Produto;
import br.imd.ufrn.database.data.ProdutoDAO;

public class EditProdutoActivity extends Activity {

    private ProdutoDAO produtoDAO;
    private EditText edtNome;
    private EditText edtValor;
    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_produto);

        edtNome = findViewById(R.id.edt_nome);
        edtValor = findViewById(R.id.edt_valor);

        produtoDAO = ProdutoDAO.getInstance(this);

        produto = (Produto) getIntent().getSerializableExtra("produto");

        if (produto != null) {
            edtNome.setText(produto.getNome());
            edtValor.setText(String.valueOf(produto.getValor()));
        }
    }

    public void cancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void process(View view) {
        String nome = edtNome.getText().toString();
        double valor = Double.parseDouble(edtValor.getText().toString());
        String msg;

        if (produto == null) {
            Produto produto = new Produto(nome, valor);
            produtoDAO.save(produto);
            msg = "Produto gravado com ID = " + produto.getId();

        } else {
            produto.setNome(nome);
            produto.setValor(valor);
            produtoDAO.update(produto);
            msg = "Produto atualizado com ID = " + produto.getId();
        }

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }
}
