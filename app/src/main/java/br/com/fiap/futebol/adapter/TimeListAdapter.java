package br.com.fiap.futebol.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.fiap.futebol.R;
import br.com.fiap.futebol.model.Time;

/**
 * Created by rtourinho on 28/11/2016.
 */

public class TimeListAdapter extends RecyclerView.Adapter<TimeListAdapter.TimeViewHolder>{

    private Context context;
    private List<Time> times;

    public TimeListAdapter(Context context, List<Time> times) {
        this.context = context;
        this.times = times;
    }

    @Override
    public TimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.time_row, parent, false);
        return new TimeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TimeViewHolder holder, int position) {
        Time t = times.get(position);
        holder.tvNome.setText(t.getNome());
        holder.tvFundacao.setText(Integer.toString(t.getAnoFundacao()));
        holder.tvEstado.setText(t.getEstado());
        Picasso.with(context)
                .load(t.getEscudo())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.ivTime);
    }

    @Override
    public int getItemCount() {
        return times.size();
    }

    public static class TimeViewHolder extends RecyclerView.ViewHolder {

        TextView tvNome;
        TextView tvFundacao;
        TextView tvEstado;
        ImageView ivTime;

        public TimeViewHolder(View view) {
            super(view);
            tvNome = (TextView) view.findViewById(R.id.tvNome);
            tvFundacao = (TextView) view.findViewById(R.id.tvFundacao);
            tvEstado = (TextView) view.findViewById(R.id.tvEstado);
            ivTime = (ImageView) view.findViewById(R.id.ivTime);
        }
    }
}
