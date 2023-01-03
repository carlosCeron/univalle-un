package com.carlosceron.univalleun.dto;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "ngramrank")
public class NgramRank {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String documentId;
    private String sentenceId;
    @Column(length = 5000)
    private String ngrama;
    private String cui;
    private long score;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getSentenceId() {
        return sentenceId;
    }

    public void setSentenceId(String sentenceId) {
        this.sentenceId = sentenceId;
    }

    public String getNgrama() {
        return ngrama;
    }

    public void setNgrama(String ngrama) {
        this.ngrama = ngrama;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }
}