package br.edu.unisenai.rangonaregua;

import static br.edu.unisenai.rangonaregua.MainActivity.listaLugar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.edu.unisenai.rangonaregua.model.Lugar;


public class NovoLugarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_novo_lugar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // importante: Toolbar do androidX (import)
        Toolbar toolbar = findViewById(R.id.toolbarNovo);
        setSupportActionBar(toolbar);

        // o comando "finish()" simplesmente fecha a activity, aí volta para a anterior
        toolbar.setNavigationOnClickListener(v -> finish());

        // carregando os inputs
        EditText edtNome = findViewById(R.id.edtNome);
        EditText edtCategoria = findViewById(R.id.edtCategoria);
        EditText edtPreco = findViewById(R.id.edtPreco);
        EditText edtObservacao = findViewById(R.id.edtObservacao);
        Button btnSalvar = findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(v -> {
            // validacao de campos nulos
            if(edtNome.getText().toString().isEmpty()) {
                edtNome.setError("Obrigatório");
            } else if(edtCategoria.getText().toString().isEmpty()) {
                edtCategoria.setError("Obrigatório");
            } else if(edtPreco.getText().toString().isEmpty()) {
                edtPreco.setError("Obrigatório");
            } else {
                // criando um novo objeto lugar
                Lugar newLugar = new Lugar(
                        edtNome.getText().toString(),
                        edtCategoria.getText().toString(),
                        // text para double
                        Double.parseDouble(edtPreco.getText().toString()),
                        edtObservacao.getText().toString(),
                        0
                );

                // inputando o novo objeto na lista de lugares q ta na main
                listaLugar.add(newLugar);
                finish();
            }
        });
    }
}
