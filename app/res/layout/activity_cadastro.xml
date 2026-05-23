package com.example.projetoweb.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "app.db";
    private static final int DATABASE_VERSION = 2;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // TABELA USUÁRIOS
        db.execSQL("CREATE TABLE usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, " +
                "idade INTEGER, " +
                "tipoSanguineo TEXT, " +
                "telefone TEXT, " +
                "email TEXT UNIQUE, " +
                "senha TEXT)");

        // TABELA AGENDAMENTOS
        db.execSQL("CREATE TABLE agendamentos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "usuarioEmail TEXT, " +
                "data TEXT, " +
                "horario TEXT, " +
                "local TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("DROP TABLE IF EXISTS agendamentos");
        onCreate(db);
    }

    // =========================
    // 👤 USUÁRIOS
    // =========================

    // CREATE
    public boolean inserirUsuario(String nome, int idade, String tipoSanguineo,
                                  String telefone, String email, String senha) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nome", nome);
        values.put("idade", idade);
        values.put("tipoSanguineo", tipoSanguineo);
        values.put("telefone", telefone);
        values.put("email", email);
        values.put("senha", senha);

        return db.insert("usuarios", null, values) != -1;
    }

    // READ - Login
    public boolean validarLogin(String email, String senha) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT id FROM usuarios WHERE email=? AND senha=?",
                new String[]{email, senha}
        );

        boolean existe = cursor.moveToFirst();
        cursor.close();
        return existe;
    }

    // READ - Verificar email
    public boolean emailExiste(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT id FROM usuarios WHERE email=?",
                new String[]{email}
        );

        boolean existe = cursor.moveToFirst();
        cursor.close();
        return existe;
    }

    // READ - Buscar dados
    public Cursor buscarUsuarioPorEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT nome, idade, tipoSanguineo, telefone FROM usuarios WHERE email=?",
                new String[]{email}
        );
    }

    // UPDATE
    public boolean atualizarUsuario(String nome, int idade, String tipoSanguineo,
                                    String telefone, String email) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nome", nome);
        values.put("idade", idade);
        values.put("tipoSanguineo", tipoSanguineo);
        values.put("telefone", telefone);

        int linhas = db.update("usuarios", values, "email=?",
                new String[]{email});

        return linhas > 0;
    }

    // DELETE (completo)
    public boolean excluirUsuario(String email) {

        SQLiteDatabase db = this.getWritableDatabase();

        // apaga agendamentos primeiro
        db.delete("agendamentos", "usuarioEmail=?",
                new String[]{email});

        // depois usuário
        int linhas = db.delete("usuarios", "email=?",
                new String[]{email});

        return linhas > 0;
    }

    // =========================
    // 🩸 AGENDAMENTOS
    // =========================

    // CREATE
    public boolean inserirAgendamento(String usuarioEmail, String data,
                                      String horario, String local) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("usuarioEmail", usuarioEmail);
        values.put("data", data);
        values.put("horario", horario);
        values.put("local", local);

        return db.insert("agendamentos", null, values) != -1;
    }

    // READ
    public ArrayList<String> listarAgendamentos(String usuarioEmail) {

        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT id, data, horario, local FROM agendamentos WHERE usuarioEmail=?",
                new String[]{usuarioEmail}
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String data = cursor.getString(1);
                String horario = cursor.getString(2);
                String local = cursor.getString(3);

                lista.add(id + " - " + data + " - " + horario + " - " + local);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return lista;
    }

    // DELETE
    public boolean excluirAgendamento(int idAgendamento) {

        SQLiteDatabase db = this.getWritableDatabase();

        int linhas = db.delete("agendamentos", "id=?",
                new String[]{String.valueOf(idAgendamento)});

        return linhas > 0;
    }
}

