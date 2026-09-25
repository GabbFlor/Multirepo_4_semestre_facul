package br.senai.sp.emendaai;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String CHAVE_ANO = "ano_selecionado";
    private static final String CHAVE_FERIADOS = "feriados_carregados";

    /** Os quatro estados da tela. */
    private enum Estado {
        CARREGANDO, CONTEUDO, VAZIO, ERRO
    }

    private ProgressBar progresso;
    private RecyclerView lista;
    private View blocoErro;
    private TextView txtMensagemErro;
    private TextView txtVazio;

    private final Button[] botoesAno = new Button[3];

    private int anoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progresso = findViewById(R.id.progresso);
        lista = findViewById(R.id.listaFeriados);
        blocoErro = findViewById(R.id.blocoErro);
        txtMensagemErro = findViewById(R.id.txtMensagemErro);
        txtVazio = findViewById(R.id.txtVazio);

        botoesAno[0] = findViewById(R.id.btnAno1);
        botoesAno[1] = findViewById(R.id.btnAno2);
        botoesAno[2] = findViewById(R.id.btnAno3);

        int anoAtual = LocalDate.now().getYear();
        for (int i = 0; i < botoesAno.length; i++) {
            final int ano = anoAtual + i;
            botoesAno[i].setText(String.valueOf(ano));

            botoesAno[i].setOnClickListener(v -> {
                anoSelecionado = ano;
                marcarBotaoDoAno();

            });
        }
    }



    private void mostrarErro(String mensagem) {
        txtMensagemErro.setText(mensagem);
        mostrar(Estado.ERRO);
    }

    private void mostrar(Estado estado) {
        progresso.setVisibility(estado == Estado.CARREGANDO ? View.VISIBLE : View.GONE);
        lista.setVisibility(estado == Estado.CONTEUDO ? View.VISIBLE : View.GONE);
        txtVazio.setVisibility(estado == Estado.VAZIO ? View.VISIBLE : View.GONE);
        blocoErro.setVisibility(estado == Estado.ERRO ? View.VISIBLE : View.GONE);
    }

    private void marcarBotaoDoAno() {
        for (Button botao : botoesAno) {
            boolean escolhido = botao.getText().toString().equals(String.valueOf(anoSelecionado));
            botao.setAlpha(escolhido ? 1f : 0.45f);
        }
    }

}
