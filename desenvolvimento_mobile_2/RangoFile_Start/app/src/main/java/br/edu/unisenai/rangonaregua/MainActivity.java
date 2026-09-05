package br.edu.unisenai.rangonaregua;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import br.edu.unisenai.rangonaregua.adapter.LugarAdapter;
import br.edu.unisenai.rangonaregua.data.Catalogo;
import br.edu.unisenai.rangonaregua.model.Lugar;


public class MainActivity extends AppCompatActivity implements LugarAdapter.Acao {

    static List<Lugar> listaLugar = new ArrayList<>();
    // dessa vez, o adapter fica no escopo da classe (n mais do "onCreate"), para usar no "onFocus" tbm
    LugarAdapter adapter;

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

        // carregar o database (mock com dados ficticios)
        listaLugar = Catalogo.inicial();

        // esse aqui abre o formulario para adicionar um novo lugra (abrir outra tela)
        FloatingActionButton btNovo = findViewById(R.id.fabNovo);
        btNovo.setOnClickListener(v -> {
            Intent intent = new Intent(this, NovoLugarActivity.class);
            startActivity(intent);
        });



        // SE REPETE PARA QUALQUER ADAPTER QUE QUEISER COLOCAR (RECEITA DE BOLO)


        // carregando o recycleView
        RecyclerView rvLugares = findViewById(R.id.rvLugares);

        // define se mostra em linhas ou colunas (nesse caso em linhas [padrao])
        rvLugares.setLayoutManager(new LinearLayoutManager(this));

        // criando o adapter do recycleView
        adapter = new LugarAdapter(listaLugar, this);
        rvLugares.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter.notifyDataSetChanged();
    }

    @Override
    public void votar(Lugar lugar) {
        lugar.setVotos(lugar.getVotos() + 1);

        // esse serve para ele recalcular qual vai primeiro (pq a ordem dos votos mudou)
        Catalogo.ordenarPorVotos(listaLugar);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void detalhes(Lugar lugar) {
        Intent rota = new Intent(this, DetalheActivity.class);
        // manda o obj em questão (o que foi clicado) para a nova activity
        rota.putExtra("obj",lugar);
        startActivity(rota);
    }
}
