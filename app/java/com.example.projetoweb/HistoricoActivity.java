package com.example.projetoweb;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetoweb.data.DBHelper;

import java.util.ArrayList;

public class HistoricoActivity extends AppCompatActivity {

    private ListView listView;
    private Button btnExcluir, btnVoltar;
    private DBHelper dbHelper;

    private ArrayList<String> lista;
    private ArrayAdapter<String> adapter;

    private String usuarioEmail;

    // 🔥 controle de seleção
    private int idSelecionado = -1;
    private int posicaoSelecionada = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        listView = findViewById(R.id.listViewAgendamentos);
        btnExcluir = findViewById(R.id.btnExcluir);
        btnVoltar = findViewById(R.id.btnVoltar);

        dbHelper = new DBHelper(this);

        usuarioEmail = getIntent().getStringExtra("usuarioEmail");

        carregarLista();

        // 🔹 SELECIONAR ITEM
        listView.setOnItemClickListener((parent, view, position, id) -> {

            String item = lista.get(position);

            idSelecionado = Integer.parseInt(item.split(" - ")[0]);
            posicaoSelecionada = position;

            Toast.makeText(this, "Agendamento selecionado", Toast.LENGTH_SHORT).show();
        });

        // 🔥 BOTÃO CANCELAR
        btnExcluir.setOnClickListener(v -> {

            if (idSelecionado == -1) {
                Toast.makeText(this, "Selecione um agendamento primeiro", Toast.LENGTH_SHORT).show();
                return;
            }

            new AlertDialog.Builder(this)
                    .setTitle("Cancelar Agendamento")
                    .setMessage("Tem certeza que deseja cancelar?")

                    .setPositiveButton("Sim", (dialog, which) -> {

                        boolean sucesso = dbHelper.excluirAgendamento(idSelecionado);

                        if (sucesso) {
                            Toast.makeText(this, "Agendamento cancelado", Toast.LENGTH_SHORT).show();

                            lista.remove(posicaoSelecionada);
                            adapter.notifyDataSetChanged();

                            idSelecionado = -1;
                        } else {
                            Toast.makeText(this, "Erro ao cancelar", Toast.LENGTH_SHORT).show();
                        }
                    })

                    .setNegativeButton("Não", null)
                    .show();
        });

        // 🔹 VOLTAR
        btnVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(HistoricoActivity.this, MainActivity.class);
            intent.putExtra("usuarioEmail", usuarioEmail);
            startActivity(intent);
            finish();
        });
    }

    private void carregarLista() {
        lista = dbHelper.listarAgendamentos(usuarioEmail);

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, lista);

        listView.setAdapter(adapter);
    }
}