package com.example.projetoweb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AgendamentoAdapter extends ArrayAdapter<Agendamento> {

    private Context context;
    private ArrayList<Agendamento> agendamentos;

    public AgendamentoAdapter(Context context, ArrayList<Agendamento> agendamentos) {
        super(context, 0, agendamentos);
        this.context = context;
        this.agendamentos = agendamentos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_agendamento, parent, false);
        }

        Agendamento ag = agendamentos.get(position);

        TextView tvAtividade = convertView.findViewById(R.id.tvAtividade);
        TextView tvData = convertView.findViewById(R.id.tvData);
        TextView tvHora = convertView.findViewById(R.id.tvHora);
        TextView tvLocal = convertView.findViewById(R.id.tvLocal);

        tvAtividade.setText(ag.getAtividade());
        tvData.setText("Data: " + ag.getData());
        tvHora.setText("Hora: " + ag.getHorario());
        tvLocal.setText("Local: " + ag.getLocal());

        return convertView;
    }
}
