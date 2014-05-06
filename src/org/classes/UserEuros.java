/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.classes;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author MrFabio
 */
public class UserEuros implements Comparable<UserEuros>, Serializable {

    String nome;
    int euros;

    public UserEuros(String name, int eur) {
        this.nome = name;
        this.euros = eur;
    }

    UserEuros(UserEuros u1) {
        this.nome = u1.getNome();
        this.euros = u1.getEuros();
    }

    @Override
    public String toString() {
        return nome + " : " + euros;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEuros() {
        return euros;
    }

    public void setEuros(int euros) {
        this.euros = euros;
    }

    @Override
    public int compareTo(UserEuros o) {
        if (this.euros < o.getEuros()) {
            return 1;
        } else if (this.euros > o.getEuros()) {
            return -1;
        } else {
            return this.equals(o) ? 0 : -1;
        }
    }

    public synchronized void inceuros(int euros) {
        this.euros += euros;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserEuros other = (UserEuros) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }
}
