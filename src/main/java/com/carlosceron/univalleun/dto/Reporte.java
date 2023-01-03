package com.carlosceron.univalleun.dto;

import javax.persistence.*;

@Entity
@Table(name = "reporte")
public class Reporte {
    @Id
    private long id;
    @Column(length = 3000)
    private String ngrama;
    private String tipo;
    private String cui;
    private String sty;
    private String stn;
    private String sentenceID;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(length = 3000)
    public String getNgrama() {
        return ngrama;
    }

    public void setNgrama(String ngrama) {
        this.ngrama = ngrama;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public String getSty() {
        return sty;
    }

    public void setSty(String sty) {
        this.sty = sty;
    }

    public String getStn() {
        return stn;
    }

    public void setStn(String stn) {
        this.stn = stn;
    }

    public String getSentenceID() {
        return sentenceID;
    }

    public void setSentenceID(String sentenceID) {
        this.sentenceID = sentenceID;
    }
}
