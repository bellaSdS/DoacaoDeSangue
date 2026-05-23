package com.example.projetoweb;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetoweb.data.DBHelper;

public class PerfilActivity extends AppCompatActivity {

    private TextView tvNome, tvIdade, tvTipoSanguineo, tvTelefone;
    private String usuarioEmail;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Referências
        tvNome = findViewById(R.id.tvNome);
        tvIdade = findViewById(R.id.tvIdade);
        tvTipoSanguineo = findViewById(R.id.tvTipoSanguineo);
        tvTelefone = findViewById(R.id.tvTelefone);

        Button btnEditarPerfil = findViewById(R.id.btnEditarPerfil);
        Button btnVoltar = findViewById(R.id.btnVoltar);
        Button btnExcluir = findViewById(R.id.btnExcluirConta); // NOVO

        usuarioEmail = getIntent().getStringExtra("usuarioEmail");

        dbHelper = new DBHelper(this);

        carregarDadosUsuario();

        // EDITAR PERFIL
        btnEditarPerfil.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilActivity.this, EditarPerfilActivity.class);
            intent.putExtra("usuarioEmail", usuarioEmail);
            startActivity(intent);
        });

        // VOLTAR AO MENU
        btnVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilActivity.this, MainActivity.class);
            intent.putExtra("usuarioEmail", usuarioEmail);
            startActivity(intent);
            finish();
        });

        // 🔥 EXCLUIR CONTA (COM CONFIRMAÇÃO)
        btnExcluir.setOnClickListener(v -> {

            new AlertDialog.Builder(PerfilActivity.this)
                    .setTitle("Excluir Conta")
                    .setMessage("Tem certeza que deseja excluir sua conta?")

                    // SIM
                    .setPositiveButton("Sim", (dialog, which) -> {

                        boolean sucesso = dbHelper.excluirUsuario(usuarioEmail);

                        if (sucesso) {
                            Toast.makeText(PerfilActivity.this, "Conta excluída com sucesso", Toast.LENGTH_LONG).show();

                            // Volta para login
                            Intent intent = new Intent(PerfilActivity.this, FormeLogin.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(PerfilActivity.this, "Erro ao excluir conta", Toast.LENGTH_SHORT).show();
                        }
                    })

                    // NÃO
                    .setNegativeButton("Não", (dialog, which) -> dialog.dismiss())

                    .show();
        });
    }

    private void carregarDadosUsuario() {
        Cursor cursor = dbHelper.buscarUsuarioPorEmail(usuarioEmail);

        if (cursor.moveToFirst()) {
            tvNome.setText("Nome: " + cursor.getString(0));
            tvIdade.setText("Idade: " + cursor.getInt(1));
            tvTipoSanguineo.setText("Tipo sanguíneo: " + cursor.getString(2));
            tvTelefone.setText("Telefone: " + cursor.getString(3));
        }

        cursor.close();
    }
}

