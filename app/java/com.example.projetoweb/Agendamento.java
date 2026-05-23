package com.example.projetoweb;

public class Agendamento {

    private String atividade;
    private String data;
    private String horario;
    private String local;

    public Agendamento(String atividade, String data, String horario, String local) {
        this.atividade = atividade;
        this.data = data;
        this.horario = horario;
        this.local = local;
    }

    public String getAtividade() {
        return atividade;
    }

    public String getData() {
        return data;
    }

    public String getHorario() {
        return horario;
    }

    public String getLocal() {
        return local;
    }
}
