package com.example.projetoweb;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projetoweb.data.DBHelper;

public class CadastroActivity extends AppCompatActivity {

    EditText etNome, etIdade, etTipoSanguineo, etTelefone, etEmail, etSenha, etConfirmarSenha;
    Button btnSalvarCadastro;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        dbHelper = new DBHelper(this);

        etNome = findViewById(R.id.etNome);
        etIdade = findViewById(R.id.etIdade);
        etTipoSanguineo = findViewById(R.id.etTipoSanguineo);
        etTelefone = findViewById(R.id.etTelefone);
        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        etConfirmarSenha = findViewById(R.id.etConfirmarSenha);

        btnSalvarCadastro = findViewById(R.id.btnSalvarCadastro);

        btnSalvarCadastro.setOnClickListener(v -> salvarCadastro());
    }

    private void salvarCadastro() {

        String nome = etNome.getText().toString();
        String idadeStr = etIdade.getText().toString();
        String tipo = etTipoSanguineo.getText().toString();
        String telefone = etTelefone.getText().toString();
        String email = etEmail.getText().toString();
        String senha = etSenha.getText().toString();
        String confirmarSenha = etConfirmarSenha.getText().toString();

        if (nome.isEmpty() || idadeStr.isEmpty() || tipo.isEmpty() || telefone.isEmpty()
                || email.isEmpty() || senha.isEmpty()) {

            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!senha.equals(confirmarSenha)) {
            Toast.makeText(this, "As senhas não coincidem!", Toast.LENGTH_SHORT).show();
            return;
        }

        int idade = Integer.parseInt(idadeStr);

        // Verificar se email já existe
        if (dbHelper.emailExiste(email)) {
            Toast.makeText(this, "E-mail já cadastrado!", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean inserido = dbHelper.inserirUsuario(
                nome,
                idade,
                tipo,
                telefone,
                email,
                senha
        );

        if (inserido) {
            Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();
            finish(); // volta para tela de login
        } else {
            Toast.makeText(this, "Erro ao salvar cadastro!", Toast.LENGTH_SHORT).show();
        }
    }
}



