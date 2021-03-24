package com.carlosceron.univalleun.dto;

import javax.persistence.*;

@Entity
@Table(name = "umls")
public class Uml {
    @Id
    private Integer id;
    private Integer document_id;
    private String ehr;
    private String document_date;
    private String category;
    private String sub_category;
    private String section;
    private Integer sentence_id;
    private String sentence;
    private Integer begin;
    private Integer end;
    private String date;
    private String date_day;
    private String date_month;
    private String date_year;
    private String cui;
    private String stn;
    private String sty;
    private Boolean negated;
    private String concept;
    private String jkes_id;




    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public Integer getDocument_id() {
        return document_id;
    }

    public void setDocument_id(Integer document_id) {
        this.document_id = document_id;
    }

    public String getEhr() {
        return ehr;
    }

    public void setEhr(String ehr) {
        this.ehr = ehr;
    }

    public String getDocument_date() {
        return document_date;
    }

    public void setDocument_date(String document_date) {
        this.document_date = document_date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSub_category() {
        return sub_category;
    }

    public void setSub_category(String sub_category) {
        this.sub_category = sub_category;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Integer getSentence_id() {
        return sentence_id;
    }

    public void setSentence_id(Integer sentence_id) {
        this.sentence_id = sentence_id;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public Integer getBegin() {
        return begin;
    }

    public void setBegin(Integer begin) {
        this.begin = begin;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate_day() {
        return date_day;
    }

    public void setDate_day(String date_day) {
        this.date_day = date_day;
    }

    public String getDate_month() {
        return date_month;
    }

    public void setDate_month(String date_month) {
        this.date_month = date_month;
    }

    public String getDate_year() {
        return date_year;
    }

    public void setDate_year(String date_year) {
        this.date_year = date_year;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public String getStn() {
        return stn;
    }

    public void setStn(String stn) {
        this.stn = stn;
    }

    public String getSty() {
        return sty;
    }

    public void setSty(String sty) {
        this.sty = sty;
    }

    public Boolean getNegated() {
        return negated;
    }

    public void setNegated(Boolean negated) {
        this.negated = negated;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getJkes_id() {
        return jkes_id;
    }

    public void setJkes_id(String jkes_id) {
        this.jkes_id = jkes_id;
    }
}
