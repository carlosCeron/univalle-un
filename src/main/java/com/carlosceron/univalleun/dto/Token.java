package com.carlosceron.univalleun.dto;

import javax.persistence.*;

@Entity
public class Token {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String cue;
    private String scope;
    private String sentence;
    private String type;
    private Integer sentenceId;

    public String getCue() {
        return cue;
    }

    public void setCue(String cue) {
        this.cue = cue;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public Integer getSentenceId() {
        return sentenceId;
    }

    public void setSentenceId(Integer sentenceId) {
        this.sentenceId = sentenceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
