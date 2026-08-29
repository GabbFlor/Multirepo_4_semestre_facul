package com.gabbflor.projeto_2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterNota extends RecyclerView.Adapter<AdapterNota.ViewHolder> {
    // construtor que recebe a lista de notas
    private List<Nota> listaNotas;
    public AdapterNota(List<Nota> dados) {
        this.listaNotas = dados;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // faz o vínculo do card que vamos usar (cria a tela basicamente)
        View card = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_nota, parent, false);

        // parece um construtor de tela
        return new ViewHolder(card);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // preenche os campos de cada card com o conteúdo da lista, semelhante a um for
        // "position" é tipo a posição na array

        Nota item = listaNotas.get(position);

        // holder é tipo o card que isso aqui ta carregando
        holder.txtTitulo.setText(item.getTitulo());
        holder.txtDescricao.setText(item.getDescricao());
    }

    @Override
    public int getItemCount() {
        return listaNotas.size();
    }

    // permite vincular os objetos xml no código java
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitulo, txtDescricao;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txt_titulo);
            txtDescricao= itemView.findViewById(R.id.txt_descricao);
        }
    }
}
