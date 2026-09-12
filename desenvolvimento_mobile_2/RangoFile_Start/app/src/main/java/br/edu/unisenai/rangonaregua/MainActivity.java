package br.edu.unisenai.rangonaregua;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.ListenerRegistration;

import java.util.ArrayList;
import java.util.List;

import br.edu.unisenai.rangonaregua.adapter.LugarAdapter;
import br.edu.unisenai.rangonaregua.data.Catalogo;
import br.edu.unisenai.rangonaregua.data.LugarRepository;
import br.edu.unisenai.rangonaregua.model.Lugar;


public class MainActivity extends AppCompatActivity implements LugarAdapter.Acao {

    private LugarRepository repository;
    private ListenerRegistration registro;

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
        // listaLugar = Catalogo.inicial();
        repository = new LugarRepository();

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

        //configurar o deslizar
        configDeslizar();
    }

    private void configDeslizar() {
        // essa aqui é a criação do comportamento
        ItemTouchHelper.SimpleCallback deslizar =
                // diz q pode movimentar para a esquerda ou para a direita
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        // pega o card em especifico q sofreu a ação "arrastar"
                        int posicao = viewHolder.getAdapterPosition();
                        // pega o item na lista correspondente ao card
                        Lugar lugar = listaLugar.get(posicao);
                        // chama a função do repository para apagar o card no firebase
                        repository.deletar(lugar);

                        // desfazer (voltar o item)
                        //oq eu entendi, ele guarda as informações do item em cache por um tempo antes de deletar de fato, e se for clicado, ele usa esse cache para restaurar
                        Snackbar.make(findViewById(R.id.rvLugares),"Lugar removido",Snackbar.LENGTH_LONG)
                                .setAction("Desfazer", v ->  repository.restaurar(lugar))
                                .show();
                    }
                };

        // adiciona esse comportamento ao recycle view
        new ItemTouchHelper(deslizar).attachToRecyclerView(findViewById(R.id.rvLugares));
    }

    @Override
    protected void onResume() {
        super.onResume();
        registro = repository.lerRealTime((value, error) -> {
            if (error != null) {
                Log.e("ERRO", error.getMessage());
                return;
            }

            // limpa lista antiga
            listaLugar.clear();

            // adiciona todos os itens em "value" mapeando para o objeto do tipo "Lugar"
            listaLugar.addAll(value.toObjects(Lugar.class));

            adapter.notifyDataSetChanged();
        });
    }

    @Override
    public void votar(Lugar lugar) {
        //lugar.setVotos(lugar.getVotos() + 1);
        //Catalogo.ordenarPorVotos(listaLugar);
        repository.votar(lugar);
    }

    @Override
    public void detalhes(Lugar lugar) {
        Intent rota = new Intent(this, DetalheActivity.class);
        // manda o obj em questão (o que foi clicado) para a nova activity
        rota.putExtra("obj",lugar);
        startActivity(rota);
    }
}
