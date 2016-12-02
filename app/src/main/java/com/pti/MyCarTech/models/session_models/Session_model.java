package com.pti.MyCarTech.models.session_models;

import java.util.ArrayList;

/**
 * Created by fernando on 01-12-2016.
 */

public class Session_model {
    private String session_id;
    private Long data;
    private Long cons_medio;
    private ArrayList<String> depressoesList;
    private ArrayList<String> lombasList;
    private Long dist_percorrida;
    private Long duracao;
    private String fim;
    private String incio;
    private Long v_media;


    public Session_model(String session_id, Long data, Long cons_medio, ArrayList<String> depressoesList, ArrayList<String> lombasList, Long dist_percorrida, Long duracao, String fim, String incio, Long v_media) {
        this.session_id = session_id;
        this.data = data;
        this.cons_medio = cons_medio;
        this.depressoesList = depressoesList;
        this.lombasList = lombasList;
        this.dist_percorrida = dist_percorrida;
        this.duracao = duracao;
        this.fim = fim;
        this.incio = incio;
        this.v_media = v_media;
    }

    public Session_model() {
    }

    @Override
    public String toString() {
        return "Session_model{" +
                "session_id='" + session_id + '\'' +
                ", data=" + data +
                ", cons_medio=" + cons_medio +
                ", depressoesList=" + depressoesList +
                ", lombasList=" + lombasList +
                ", dist_percorrida=" + dist_percorrida +
                ", duracao=" + duracao +
                ", fim='" + fim + '\'' +
                ", incio='" + incio + '\'' +
                ", v_media=" + v_media +
                '}';
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public Long getV_media() {
        return v_media;
    }

    public void setV_media(Long v_media) {
        this.v_media = v_media;
    }

    public String getIncio() {
        return incio;
    }

    public void setIncio(String incio) {
        this.incio = incio;
    }

    public String getFim() {
        return fim;
    }

    public void setFim(String fim) {
        this.fim = fim;
    }

    public Long getDuracao() {
        return duracao;
    }

    public void setDuracao(Long duracao) {
        this.duracao = duracao;
    }

    public Long getDist_percorrida() {
        return dist_percorrida;
    }

    public void setDist_percorrida(Long dist_percorrida) {
        this.dist_percorrida = dist_percorrida;
    }

    public ArrayList<String> getLombasList() {
        return lombasList;
    }

    public void setLombasList(ArrayList<String> lombasList) {
        this.lombasList = lombasList;
    }

    public ArrayList<String> getDepressoesList() {
        return depressoesList;
    }

    public void setDepressoesList(ArrayList<String> depressoesList) {
        this.depressoesList = depressoesList;
    }

    public Long getCons_medio() {
        return cons_medio;
    }

    public void setCons_medio(Long cons_medio) {
        this.cons_medio = cons_medio;
    }

    public Long getData() {
        return data;
    }

    public void setData(Long data) {
        this.data = data;
    }
}
