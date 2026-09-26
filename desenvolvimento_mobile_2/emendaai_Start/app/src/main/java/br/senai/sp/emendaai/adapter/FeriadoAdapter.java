package br.senai.sp.emendaai.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.senai.sp.emendaai.R;
import br.senai.sp.emendaai.model.Feriado;
import br.senai.sp.emendaai.util.Datas;

public class FeriadoAdapter extends RecyclerView.Adapter<FeriadoAdapter.FeriadoViewHolder> {
    private List<Feriado> listaFeriados;

    public FeriadoAdapter(List<Feriado> listaFeriados) {
        this.listaFeriados = listaFeriados;
    }

    @NonNull
    @Override
    public FeriadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_feriado, parent, false);
        return new FeriadoViewHolder(card);
    }

    @Override
    public void onBindViewHolder(@NonNull FeriadoViewHolder holder, int position) {
        Feriado feriado = listaFeriados.get(position);

        FeriadoViewHolder vh = (FeriadoViewHolder) holder;
        vh.txtDia.setText(Datas.dia(feriado.getData()));
        vh.txtMes.setText(Datas.mesCurto(feriado.getData()));
        vh.txtNome.setText(feriado.getNome());
        vh.txtSemana.setText(Datas.diaDaSemana(feriado.getData()));
        vh.txtContagem.setText(Datas.contagem(feriado.getData()));

        boolean ehEmenda = Datas.ehEmenda(feriado.getData());
        if(ehEmenda) {
            holder.txtSelo.setVisibility(View.VISIBLE);
        } else {
            holder.txtSelo.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return listaFeriados.size();
    }

    public class FeriadoViewHolder extends RecyclerView.ViewHolder {
        TextView txtDia, txtMes, txtNome, txtSemana, txtContagem, txtSelo;

        public FeriadoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDia = itemView.findViewById(R.id.txtDia);
            txtMes = itemView.findViewById(R.id.txtMes);
            txtNome = itemView.findViewById(R.id.txtNome);
            txtSemana = itemView.findViewById(R.id.txtSemana);
            txtContagem = itemView.findViewById(R.id.txtContagem);
            txtSelo = itemView.findViewById(R.id.txtSelo);
        }
    }
}
