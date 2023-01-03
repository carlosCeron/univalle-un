package com.carlosceron.univalleun.dto;

import javax.persistence.*;

@Entity
@Table(name = "ngrams")
public class NGrama {

    @Id
    private Integer id;
    @Column(name = "sentence_id")
    private String sentenceId;
    private String scope;
    private String cue;
    private String bigrams;
    private String trigrams;
    private String fourgrams;
    private String fivegrams;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSentenceId() {
        return sentenceId;
    }

    public void setSentenceId(String sentenceId) {
        this.sentenceId = sentenceId;
    }

    public String getCue() {
        return cue;
    }

    public void setCue(String cue) {
        this.cue = cue;
    }

    public String getBigrams() {
        return bigrams;
    }

    public void setBigrams(String bigrams) {
        this.bigrams = bigrams;
    }

    public String getTrigrams() {
        return trigrams;
    }

    public void setTrigrams(String trigrams) {
        this.trigrams = trigrams;
    }

    public String getFourgrams() {
        return fourgrams;
    }

    public void setFourgrams(String fourgrams) {
        this.fourgrams = fourgrams;
    }

    public String getFivegrams() {
        return fivegrams;
    }

    public void setFivegrams(String fivegrams) {
        this.fivegrams = fivegrams;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
