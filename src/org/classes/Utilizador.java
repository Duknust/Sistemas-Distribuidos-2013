package org.classes;

import java.io.Serializable;

public class Utilizador implements Serializable {

    private String Nome;
    private String Pass;

    //Constructores
    public Utilizador(String Nome, String Pass) {
        this.Nome = Nome;
        this.Pass = Pass;
    }

    public Utilizador(Utilizador u) {
        this.Nome = u.getNome();
        this.Pass = u.getPass();
    }

    //GETTERS e SETTERS
    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    //FX
    public boolean login(String pass) {

        if (this.Pass.equals(pass)) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Utilizador other = (Utilizador) obj;
        if (this.Nome.compareTo(other.getNome()) != 0) {
            return false;
        }
        if (this.Pass.compareTo(other.getPass()) != 0) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Utilizador{" + "Nome=" + Nome + ", Pass=" + Pass + '}';
    }
}
