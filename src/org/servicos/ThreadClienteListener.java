/*
 * Esta thread vai ler da socket os dados enviados pelo server, ex: actualizar dados...
 */
package org.servicos;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import org.Main;
import org.tipos.Pacote;
import org.tipos.TipoOP;

/**
 *
 * @author MrFabio
 */
public class ThreadClienteListener extends Thread {

    private Socket cli;

    public ThreadClienteListener(Socket s) {

        this.cli = s;
    }

    @Override
    public void run() {
        InputStream ins = null;

        try {
            ins = cli.getInputStream();
            // System.out.println("3");
        } catch (IOException i) {
            System.out.println("IOE_" + i.toString());
        }

//        System.out.println("4");
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(ins);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        Pacote p;
        TipoOP tp;
        while (true) {
            p = null;
            tp = TipoOP.NULL;
            try {
                System.out.println("ESTOU ESPERANDO CENAS");
                p = (Pacote) ois.readObject();
                System.out.println("RECEBI CENAS");
                if (p != null) {
                    System.out.println("TO" + p.toString());
                    tp = p.getTipo();
                    switch (tp) {
                        case ADDPROJECTO:
                            System.out.println("RECEBI " + tp);
                            Main.mapProjectos.vals.put(p.getProj().getNome(), p.getProj());
                            break;
                        case REPNOMEPROJ:
                            System.out.println("RECEBI " + tp + " " + p.getInteiro1());
                            Main.ReqProjectoInt = p.getInteiro1();
                            Main.mapProjectos.setListaProjectos(p.getMp());
                            synchronized (Main.inter) {
                                Main.inter.notifyAll();
                            }
                            break;
                        case REPMAPPROJ:
                            System.out.println("RECEBI " + tp);
                            Main.mapProjectos.setListaProjectos(p.getMp());
                            Main.ACTMAPInt = 1;
                            break;

                        case REPLOGIN:
                            System.out.println("RECEBI " + tp);
                            synchronized (Main.inicio) {
                                Main.ReqLoginInt = p.getInteiro1();
                                //Main.notifica_interface();
                                Main.inicio.notifyAll();
                            }
                            break;

                        case REPPROJ:
                            System.out.println("RECEBI " + tp);
                            Main.ReqProjectoInt = p.getInteiro1();
                            synchronized (Main.inter) {
                                Main.inter.notifyAll();
                            }
                            break;

                        case ACTPROJECTO:
                            System.out.println("RECEBI " + tp);
                            Main.mapProjectos.setProj(p.getProj());

                            //Main.notifica_interface();
                            break;

                        case REPADDEUROS:
                            System.out.println("RECEBI " + tp);
                            if (p.getInteiro1() == 1) {
                                JOptionPane.showMessageDialog(null, "Ofereceu com sucesso " + p.getInteiro2() + "€ ao Projecto " + p.getString1());
                            } else if (p.getInteiro1() == -1) {
                                JOptionPane.showMessageDialog(null, "Não pode ofercer dinheiro a projectos seus");
                            } else {
                                JOptionPane.showMessageDialog(null, "Houve um erro com a oferta ao Projecto " + p.getString1());
                            }
                            break;

                        case NOTIFEUROS:
                            System.out.println("RECEBI " + tp);

                            Main.inter.addNotif(p);

                            break;
                        case REPREGISTO:
                            System.out.println("RECEBI " + tp);

                            Main.ReqRegisto = p.getInteiro1();
                            synchronized (Main.inicio) {
                                Main.inicio.notifyAll();
                            }
                            break;

                    }

                }
//                System.out.println("3");
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("IOC_" + ex.toString());
                Main.stopligacao();
                break;
            }
        }

        //   out.close();
    }
}
