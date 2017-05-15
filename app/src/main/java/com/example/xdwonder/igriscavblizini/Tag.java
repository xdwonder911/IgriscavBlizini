package com.example.xdwonder.igriscavblizini;

/**
 * Created by xdwonder on 4.3.2017.
 */

public class Tag {
    private String ime;//kosarka,odbojka,fuzbal,otroško
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Tag(String ime,String idx) {
        this.ime = ime;
        this.id=idx;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    @Override
    public String toString() {
        return ime;
    }
}
