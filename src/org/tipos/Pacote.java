/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tipos;

import java.io.Serializable;
import java.util.HashMap;
import org.classes.Projecto;
import org.classes.Utilizador;

/**
 *
 * @author MrFabio
 */
public class Pacote implements Serializable {

    private TipoOP tipo;
    private Projecto proj;
    private Utilizador user;
    private HashMap<String, Projecto> mp;
    private String string1;
    private String string2;
    private int inteiro1;
    private int inteiro2;

    public Pacote() {
        this.tipo = TipoOP.NULL;
        this.proj = null;
        this.user = null;
        this.mp = new HashMap<>();
        this.string1 = "";
        this.string2 = "";
        this.inteiro1 = -1;
        this.inteiro2 = -1;
    }

    public Pacote(Pacote p) {
        this.tipo = p.getTipo();
        this.proj = p.getProj();
        this.user = p.getUser();
        this.mp = p.getMp();
        this.string1 = p.getString1();
        this.string2 = p.getString2();
        this.inteiro1 = p.getInteiro1();
        this.inteiro2 = p.getInteiro2();
    }

    public Pacote(TipoOP tipo, Projecto proj, Utilizador user, HashMap<String, Projecto> mp, int inteiro1, int inteiro2, String str, String str2) {
        this.tipo = tipo;
        this.proj = proj;
        this.user = user;
        this.mp.putAll(mp);

        this.string1 = str;
        this.string2 = str2;
        this.inteiro1 = inteiro1;
        this.inteiro2 = inteiro2;
    }

    public String getString1() {
        return string1;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public Projecto getProj() {
        return proj;
    }

    public void setProj(Projecto proj) {
        this.proj = proj;
    }

    public Utilizador getUser() {
        return user;
    }

    public void setUser(Utilizador user) {
        this.user = user;
    }

    public HashMap<String, Projecto> getMp() {
        return mp;
    }

    public void setMp(HashMap<String, Projecto> mp) {
        this.mp.clear();
        this.mp.putAll(mp);
    }

    public TipoOP getTipo() {
        return tipo;
    }

    public void setTipo(TipoOP tipo) {
        this.tipo = tipo;
    }

    public int getInteiro1() {
        return inteiro1;
    }

    public void setInteiro1(int inteiro1) {
        this.inteiro1 = inteiro1;
    }

    public int getInteiro2() {
        return inteiro2;
    }

    public void setInteiro2(int inteiro2) {
        this.inteiro2 = inteiro2;
    }

    public String getString2() {
        return string2;
    }

    public void setString2(String string2) {
        this.string2 = string2;
    }

    //----------------
    public void criaADDPROJECTO(Projecto p) {
        this.tipo = TipoOP.ADDPROJECTO;
        this.proj = p.clone();
    }

    public void criaREQLOGIN(Utilizador login) {
        this.tipo = TipoOP.REQLOGIN;
        this.user = login;
    }

    public void criaREPLOGIN(int log) {
        this.setTipo(TipoOP.REPLOGIN);
        this.setInteiro1(log);
    }

    public void criaREQMAPPROJ() {
        this.tipo = TipoOP.REQMAPPROJ;
    }

    public void criaREPMAPPROJ(HashMap<String, Projecto> map) {
        this.setTipo(TipoOP.REPMAPPROJ);
        this.setMp(map);
    }

    public void criaREQPROJ(Projecto p) {
        this.setTipo(TipoOP.REQPROJ);
        this.setProj(p);
    }

    public void criaREPPROJ(int i) {
        this.setTipo(TipoOP.REPPROJ);
        this.setInteiro1(i);
    }

    public void criaREQADDEUROS(int i, String nome, String user) {
        this.setTipo(TipoOP.REQADDEUROS);
        this.setInteiro1(i);
        this.setString1(nome);
        this.setString2(user);
    }

    public void criaREPADDEUROS(int i, int euros, String nome) {
        this.setTipo(TipoOP.REPADDEUROS);
        this.setInteiro1(i);//1 OK _ 0 FALHOU
        this.setInteiro2(euros);
        this.setString1(nome);
    }

    public void criaACTPROJ(Projecto p) {
        this.setTipo(TipoOP.ACTPROJECTO);
        this.setProj(p);
    }

    public void criaNOTIFEUROS(String nome, String proj, int euros) {
        this.setTipo(TipoOP.NOTIFEUROS);
        this.setString1(nome);
        this.setString2(proj);
        this.setInteiro1(euros);
    }

    public void criaREQREGISTO(String nome, String pass) {
        this.setTipo(TipoOP.REQREGISTO);
        this.setString1(nome);
        this.setString2(pass);
    }

    public void criaREPREGISTO(String nome, int n) {
        this.setTipo(TipoOP.REPREGISTO);
        this.setString1(nome);
        this.setInteiro1(n);
    }

    public void criaREQRETRY(String nome) {
        this.setTipo(TipoOP.REQRETRY);
        this.setString1(nome);
    }

    @Override
    public String toString() {
        return "Pacote{" + "tipo=" + tipo + ", proj=" + proj + ", user=" + user + ", mp=" + mp + ", inteiro1=" + inteiro1 + ", inteiro2=" + inteiro2 + ", string=" + '}';
    }

    @Override
    public Pacote clone() {

        return new Pacote(this);
    }

}
