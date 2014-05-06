/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.classes;

import java.io.ObjectOutputStream;

/**
 *
 * @author MrFabio
 */
public class OOSUSER {

    String User;
    ObjectOutputStream oos;

    public OOSUSER(String User, ObjectOutputStream oos) {
        this.User = User;
        this.oos = oos;
    }

    public OOSUSER() {
        this.User = "";
        this.oos = null;
    }

    public String getUser() {
        return User;
    }

    public synchronized void setUser(String User) {
        this.User = User;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public synchronized void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }

}
