package com.aula.muralfilmes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aula.muralfilmes.adapter.FilmeAdapter;
import com.aula.muralfilmes.data.FilmeRepository;
import com.aula.muralfilmes.model.Filme;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.ListenerRegistration;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements FilmeAdapter.Acao {

    static List<Filme> listaFilmes = new ArrayList<>();
    FilmeAdapter adapter;
    private String meuNome;

    private FilmeRepository repository;
    private ListenerRegistration registro;

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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Carregar dados do usuario
        carregarNome();

        // Ação do botão de adicionar
        FloatingActionButton fab = findViewById(R.id.fabAdicionar);
        fab.setOnClickListener(v -> mostrarDialogoNovoFilme());


        // Adicionar SUA IMPLEMENTAÇÃO AQUI
        RecyclerView rvFilmes = findViewById(R.id.listaFilmes);

        rvFilmes.setLayoutManager(new LinearLayoutManager(this));

        adapter = new FilmeAdapter(listaFilmes, this);
        rvFilmes.setAdapter(adapter);

        repository = new FilmeRepository();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registro = repository.lerRealTime((value, error) -> {
            if (error != null) {
                Toast.makeText(this, "Erro ao ler filmes", Toast.LENGTH_LONG).show();
            }

            listaFilmes.clear();

            // evita nullpointer segundo a IDEA
            assert value != null;
            listaFilmes.addAll(value.toObjects(Filme.class));

            adapter.notifyDataSetChanged();
        });
    }

    private void carregarNome() {
        SharedPreferences localNome = getSharedPreferences("nomeUsuario", MODE_PRIVATE);
        meuNome = localNome.getString("nomeUsuario", null);

        if (meuNome != null && !meuNome.equals("")) {
            Toast.makeText(this, "Bem-vindo(a) " + meuNome, Toast.LENGTH_LONG).show();
            return;
        }

        // Ativar a caixa de texto para digitar o nome
        EditText campo = new EditText(this);
        campo.setHint("Seu nome");

        new AlertDialog.Builder(this)
                .setTitle("Quem é você")
                .setMessage("Vai aparecer ao lado dos filmes que você indicar.")
                .setView(campo)
                .setCancelable(false)
                .setPositiveButton("Pronto",(dialogInterface, i) -> {
                    meuNome = campo.getText().toString();
                    localNome.edit().putString("nomeUsuario", meuNome).apply();

                }).show();

    }


    private void mostrarDialogoNovoFilme() {
        View form = LayoutInflater.from(this).inflate(R.layout.tela_filme, null);
        EditText campoTitulo = form.findViewById(R.id.campoTitulo);
        EditText campoGenero = form.findViewById(R.id.campoGenero);

        new AlertDialog.Builder(this)
                .setTitle("Indicar filme")
                .setView(form)
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("Adicionar", (d, w) -> {
                    String titulo = campoTitulo.getText().toString().trim();
                    String genero = campoGenero.getText().toString().trim();

                    if (TextUtils.isEmpty(titulo) || TextUtils.isEmpty(genero)) {
                        Toast.makeText(this, "Preencha título e gênero", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Filme newFilme = new Filme(titulo, genero, meuNome, 0L);

                    repository.salvar(newFilme);
                })
                .show();
    }

    @Override
    public void votar(Filme filme) {
        repository.votar(filme);
    }

    @Override
    public void excluir(Filme filme) {
        repository.excluir(filme);
    }
}