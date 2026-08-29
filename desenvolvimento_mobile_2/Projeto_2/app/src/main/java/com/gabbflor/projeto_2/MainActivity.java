package com.gabbflor.projeto_2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // mock de notas
    private List<Nota> listaNotas = new ArrayList<>();
    private Button bt_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView rv_nota = findViewById(R.id.recyclerView);
        rv_nota.setLayoutManager(new LinearLayoutManager(this));

        //carregar os dados do recycleView
        AdapterNota adapter = new AdapterNota(listaNotas);
        rv_nota.setAdapter(adapter);

        bt_add = findViewById(R.id.bt_add);
        bt_add.setOnClickListener(v -> {
            // puxou uma outra activity como uma view (tipo uma tela [menor] dentro da outra)
            View tela = LayoutInflater.from(this).inflate(R.layout.activity_tela_adicionar, null, false);

            // dentro da View que foi importada em cima, puxa o id dos plainText e deixa elas dentro desse arquivo para ser usado nesse contexto
            EditText campoTitulo = tela.findViewById(R.id.campoTitulo);
            EditText campoDescricao = tela.findViewById(R.id.campoDescricao);

            // constroi o componente do modal
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Adicionar nota")
                    // aqui indica q aquela activity criada com o formulariozinho deve ser o conteudo do modal
                    .setView(tela)
                    .setNegativeButton("Cancelar", null)
                    // function chamada ao clicar no btn
                    .setPositiveButton("Adicionar", (dialog, which) -> {
                        // cria um novo objeto "nota" ccom as propriedades puxadas de dentro do modal
                        Nota item = new Nota(campoTitulo.getText().toString(), campoDescricao.getText().toString());

                        // adiciona o novo objeto dentro da lista q ta nesse contexto
                        listaNotas.add(item);

                        // avisa q os dados mudaram, aí ele atualiza
                        adapter.notifyDataSetChanged();
                    }).show();
        });
    }
}