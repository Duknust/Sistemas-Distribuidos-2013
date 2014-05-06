package org.servicos;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.classes.OOSUSER;
import org.classes.Projecto;
import org.classes.Utilizador;
import org.tipos.Pacote;

public class Server {

    public static HashMap<String, Projecto> mp;
    public static HashMap<String, Utilizador> uz;
    public static ArrayList<OOSUSER> listaoosuser;

    public static void pr(String s) {

        System.out.println(s);
    }

    public static void remove(OOSUSER u) {

        listaoosuser.remove(u);
    }

    public static void enviaparatodos(Pacote p) {

        for (OOSUSER s : listaoosuser) {
            try {
                s.getOos().writeObject(p.clone());
            } catch (IOException ex) {
                pr("para todos_" + ex.toString());
            }

        }

    }

    public static void enviaparauser(Pacote p, String user) {

        for (OOSUSER s : listaoosuser) {

            if (s.getUser().compareTo(user) == 0) {
                try {
                    s.getOos().writeObject(p.clone());
                } catch (IOException ex) {
                    pr("para user " + user + " _ " + ex.toString());
                }
            }

        }

    }

    public static void apagaoosuser(OOSUSER s) {

        listaoosuser.remove(s);

    }

    public static synchronized void addoos(OOSUSER s) {

        listaoosuser.add(s);
    }

    public static synchronized void adduser(ObjectOutputStream o, String user) {

        for (OOSUSER s : listaoosuser) {

            if (s.getOos() == o) {

                s.setUser(user);

            }

        }
    }

    public static synchronized boolean addUtilizador(Utilizador u) {
        boolean res = false;
        if (!Server.uz.containsKey(u.getNome())) {
            Server.uz.put(u.getNome(), u);
            res = true;
        }

        return res;
    }

    public static synchronized boolean addProjecto(Projecto p) {
        boolean res = false;
        if (!Server.mp.containsKey(p.getNome())) {
            Server.mp.put(p.getNome(), p);
            res = true;
        }
        return res;
    }

    public static HashMap<String, Projecto> getMapProjectos() {
        return Server.mp;
    }

    public static Utilizador getUtilizador(String nome) {
        return uz.get(nome);
    }

    /**
     * *
     *
     * @param nomeProj
     * @param nomeUserDoador
     * @param euros
     * @return -1 próprio projecto, 0 projecto nao existe, 1 sucesso
     */
    public static int addEurosProj(String nomeProj, String nomeUserDoador, int euros) {
        int res = 0;
        Projecto p = Server.mp.get(nomeProj);
        synchronized (p) {
            if (p != null) {
                if (p.getUtilizador().compareTo(nomeUserDoador) == 0) //user=dono
                {
                    res = -1;
                } else {
                    p.addeuros(euros);
                    p.addhistorico(nomeUserDoador, euros);
                    res = 1;
                }
            }
        }
        return res;
    }

    public static Projecto getProjecto(String nomeProj) {
        Projecto res = null;
        synchronized (Server.mp) {
            res = Server.mp.get(nomeProj);
        }

        return res;
    }

    public static void main(String[] args) throws Exception {

        mp = new HashMap<>();
        uz = new HashMap<>();

        abrir();

        Utilizador u = new Utilizador("user", "pass");
        uz.put(u.getNome(), u);

        Utilizador u2 = new Utilizador("user2", "pass");
        uz.put(u2.getNome(), u2);

        Projecto p = new Projecto("CANETA", 100, 0, "user", "AAAAAAAA");
        mp.put(p.getNome(), p);

        listaoosuser = new ArrayList<>();

        //int port = Integer.parseInt(args[0]);
        int port = 1337;
        System.out.println("A ligar à porta " + port + ", espere  ...");
        ServerSocket ss;

        ss = new ServerSocket(port);
        System.out.println("Servidor iniciado: " + ss);

        Thread t = new Thread() {
//Isto é que mal começa uma thread nunca mais funca, o save só é feito quando há um disconnect
            @Override
            public void run() {
                Scanner s = new Scanner(System.in);
                String str;
                boolean sair = false;
                while (sair == false) {
                    pr("CA");
                    str = s.nextLine();
                    if (str.equals("sair")) {
                        sair = true;
                        Server.sair();
                    }
                    if (str.equals("quit")) {
                        sair = true;
                        Server.sair();
                        System.exit(0);
                    }
                }
            }
        };
        t.start();
        while (true) {
            System.out.println("À espera de Clientes ...");

            Socket soc = ss.accept();//fica à espera da ligação

            System.out.println("Cliente aceite: " + soc);

            new ServeCliente(soc).start();
        }

    }

    public synchronized static void sair() {
        ObjectOutputStream oos = null;
        pr("vou gravar");
        try {
            String fich = "ServerDados.sd";
            oos = new ObjectOutputStream(
                    new FileOutputStream(fich));
            savefile s = new savefile(mp, uz);
            oos.writeObject(s);
            oos.flush();
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                oos.close();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.INFO, null, ex);
            }
        }

    }

    private static void abrir() throws IOException {

        pr("vou abrir");
        String fich = "ServerDados.sd";
        savefile s = null;
        ObjectInputStream oos = null;
        try {
            oos = new ObjectInputStream(new FileInputStream(fich));

            s = (savefile) oos.readObject();
        } catch (StreamCorruptedException | EOFException | ClassNotFoundException | FileNotFoundException c) {
            pr(c.toString());
        }

        if (s != null) {
            Server.mp = new HashMap<>(s.getMp());
            Server.uz = new HashMap<>(s.getUz());
            oos.close();
        }

    }

    static class savefile implements Serializable {

        private HashMap<String, Projecto> mp;
        private HashMap<String, Utilizador> uz;

        public savefile(HashMap<String, Projecto> mp, HashMap<String, Utilizador> uz) {
            this.mp = mp;
            this.uz = uz;
        }

        public HashMap<String, Projecto> getMp() {
            return mp;
        }

        public void setMp(HashMap<String, Projecto> mp) {
            this.mp = mp;
        }

        public HashMap<String, Utilizador> getUz() {
            return uz;
        }

        public void setUz(HashMap<String, Utilizador> uz) {
            this.uz = uz;
        }
    }
}
