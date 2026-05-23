package com.example.projetoweb;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetoweb.data.DBHelper;

import java.util.Calendar;

public class AgendarActivity extends AppCompatActivity {

    private EditText etData, etHorario;
    private Spinner spinnerLocal;
    private Button btnConfirmar;

    private DBHelper dbHelper;
    private String usuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar);

        etData = findViewById(R.id.etData);
        etHorario = findViewById(R.id.etHorario);
        spinnerLocal = findViewById(R.id.spinnerLocal);
        btnConfirmar = findViewById(R.id.btnConfirmar);

        dbHelper = new DBHelper(this);

        // Receber email do usuário logado
        usuarioLogado = getIntent().getStringExtra("usuarioEmail");

        // Configurar spinner de locais
        String[] locais = {"Unidade A", "Unidade B", "Unidade C"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, locais);
        spinnerLocal.setAdapter(adapter);

        // Selecionar Data
        etData.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            int ano = c.get(Calendar.YEAR);
            int mes = c.get(Calendar.MONTH);
            int dia = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dpd = new DatePickerDialog(
                    AgendarActivity.this,
                    (view, year, month, dayOfMonth) -> {
                        String dataSelecionada = dayOfMonth + "/" + (month + 1) + "/" + year;
                        etData.setText(dataSelecionada);
                    },
                    ano, mes, dia
            );
            dpd.show();
        });

        // Selecionar Horário
        etHorario.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            int hora = c.get(Calendar.HOUR_OF_DAY);
            int minuto = c.get(Calendar.MINUTE);

            TimePickerDialog tpd = new TimePickerDialog(
                    AgendarActivity.this,
                    (view, hourOfDay, minute2) ->
                            etHorario.setText(String.format("%02d:%02d", hourOfDay, minute2)),
                    hora, minuto, true
            );
            tpd.show();
        });

        // Botão Confirmar
        btnConfirmar.setOnClickListener(v -> {

            String data = etData.getText().toString();
            String horario = etHorario.getText().toString();
            String local = spinnerLocal.getSelectedItem().toString();

            if (data.isEmpty() || horario.isEmpty()) {
                Toast.makeText(this, "Selecione data e horário", Toast.LENGTH_SHORT).show();
                return;
            }

            // Salvar no banco de dados
            boolean sucesso = dbHelper.inserirAgendamento(usuarioLogado, data, horario, local);

            if (sucesso) {
                Toast.makeText(this,
                        "Agendamento realizado!\nData: " + data +
                                "\nHorário: " + horario +
                                "\nLocal: " + local,
                        Toast.LENGTH_LONG).show();

                finish(); // volta para a tela anterior
            } else {
                Toast.makeText(this, "Erro ao salvar agendamento.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
