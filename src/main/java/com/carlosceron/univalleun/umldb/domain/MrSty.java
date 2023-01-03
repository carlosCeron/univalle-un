package com.carlosceron.univalleun.umldb.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mrsty")
public class MrSty {

    private String cui;
    private String tui;
    private String stn;
    private String sty;
    @Id
    private String atui;
    private String cvf;

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public String getTui() {
        return tui;
    }

    public void setTui(String tui) {
        this.tui = tui;
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

    public String getAtui() {
        return atui;
    }

    public void setAtui(String atui) {
        this.atui = atui;
    }

    public String getCvf() {
        return cvf;
    }

    public void setCvf(String cvf) {
        this.cvf = cvf;
    }
}
