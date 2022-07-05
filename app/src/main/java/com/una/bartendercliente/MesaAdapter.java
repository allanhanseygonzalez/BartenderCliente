package com.una.bartendercliente;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MesaAdapter extends RecyclerView.Adapter<MesaViewHolder>{

    List<Mesa> items;

    public MesaAdapter(List<Mesa> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public MesaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new MesaViewHolder(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull MesaViewHolder holder, int position) {
        holder.textView.setText("Mesa: "+items.get(position).getNumero());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class MesaViewHolder extends RecyclerView.ViewHolder {

    TextView textView;
    private MesaAdapter adapter;

    public MesaViewHolder(@NonNull View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.textView);

    }

    public MesaViewHolder linkAdapter(MesaAdapter adapter){
        this.adapter = adapter;
        return this;
    }


}