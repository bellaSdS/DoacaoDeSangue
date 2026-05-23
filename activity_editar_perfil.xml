package com.example.projetoweb;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetoweb.data.DBHelper;

public class EditarPerfilActivity extends AppCompatActivity {

    private EditText etNome, etIdade, etTipoSanguineo, etTelefone;
    private Button btnSalvar, btnCancelar;
    private String usuarioEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        etNome = findViewById(R.id.etNome);
        etIdade = findViewById(R.id.etIdade);
        etTipoSanguineo = findViewById(R.id.etTipoSanguineo);
        etTelefone = findViewById(R.id.etTelefone);

        btnSalvar = findViewById(R.id.btnSalvar);
        btnCancelar = findViewById(R.id.btnCancelar);

        usuarioEmail = getIntent().getStringExtra("usuarioEmail");

        DBHelper dbHelper = new DBHelper(this);

        // BUSCAR DADOS ATUAIS DO USUÁRIO
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
                "SELECT nome, idade, tipoSanguineo, telefone FROM usuarios WHERE email = ?",
                new String[]{usuarioEmail}
        );

        if (cursor.moveToFirst()) {
            etNome.setText(cursor.getString(0));
            etIdade.setText(String.valueOf(cursor.getInt(1)));
            etTipoSanguineo.setText(cursor.getString(2));
            etTelefone.setText(cursor.getString(3));
        }

        cursor.close();

        // SALVAR ALTERAÇÕES
        btnSalvar.setOnClickListener(v -> {
            String nome = etNome.getText().toString();
            int idade = Integer.parseInt(etIdade.getText().toString());
            String tipo = etTipoSanguineo.getText().toString();
            String telefone = etTelefone.getText().toString();

            boolean atualizado = dbHelper.atualizarUsuario(
                    nome, idade, tipo, telefone, usuarioEmail
            );

            if (atualizado) {
                Toast.makeText(this, "Perfil atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, PerfilActivity.class);
                intent.putExtra("usuarioEmail", usuarioEmail);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Erro ao atualizar perfil", Toast.LENGTH_SHORT).show();
            }
        });

        // CANCELAR
        btnCancelar.setOnClickListener(v -> finish());
    }
}
