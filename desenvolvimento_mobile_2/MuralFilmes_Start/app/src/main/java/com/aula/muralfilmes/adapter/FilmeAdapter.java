package com.aula.muralfilmes.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aula.muralfilmes.R;
import com.aula.muralfilmes.model.Filme;

import java.util.List;

public class FilmeAdapter extends RecyclerView.Adapter<FilmeAdapter.FilmeViewHolder> {
    private List<Filme> listaFilmes;

    public interface Acao {
        void votar(Filme filme);
        void excluir(Filme filme);
    }

    private Acao acao;
    public FilmeAdapter(List<Filme> listaFilmes, Acao acao) {
        this.listaFilmes = listaFilmes;
        this.acao = acao;
    }

    @NonNull
    @Override
    public FilmeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_filme, parent, false);
        return new FilmeViewHolder(card);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmeViewHolder holder, int position) {
        Filme filme = listaFilmes.get(position);

        FilmeViewHolder vh = (FilmeViewHolder) holder;
        vh.txtTitulo.setText(filme.getTitulo());
        vh.txtGenero.setText(filme.getGenero());
        vh.txtIndicadoPor.setText(filme.getIndicadoPor());
        vh.txtVotos.setText(String.valueOf(filme.getVotos()));
        vh.btnVotar.setOnClickListener(v -> acao.votar(filme));
        vh.btnExcluir.setOnClickListener(v -> acao.excluir(filme));
    }

    @Override
    public int getItemCount() {
        return listaFilmes.size();
    }

    public class FilmeViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitulo, txtGenero, txtIndicadoPor, txtVotos;
        ImageButton btnVotar, btnExcluir;

        public FilmeViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            txtGenero = itemView.findViewById(R.id.txtGenero);
            txtIndicadoPor = itemView.findViewById(R.id.txtIndicadoPor);
            txtVotos = itemView.findViewById(R.id.txtVotos);
            btnVotar = itemView.findViewById(R.id.btnVotar);
            btnExcluir = itemView.findViewById(R.id.btnExcluir);
        }
    }
}
