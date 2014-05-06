package org;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.classes.Projecto;
import org.servicos.ThreadClienteListener;
import org.tipos.HashMapObs;
import org.tipos.Pacote;
import org.view.Inicio;
import org.view.InterfaceSD;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author MrFabio
 */
public class Main {

    public static String formato;
    public static HashMapObs mapProjectos;
    public static Socket s;
    public static OutputStream os;
    public static ObjectOutputStream oos;
    public static String userlogado = "";
    public static Thread ReqProjThread;
    public static Thread iniciaDadosThread;
    public static int ReqProjectoInt;
    public static int ReqLoginInt;//0 Pass errada - 1 OK - 2 Não existe esse user
    public static int ReqProjectoNomeInt;
    public static int ACTMAPInt;
    public static int ReqRegisto;
    public static int activa;
    public static ThreadClienteListener tcl;
    public static InterfaceSD inter;
    public static Inicio inicio;

    public static void notifica_interface() {

        inter.notify();
    }

    public static synchronized void submeteProjecto(String Nome, int Necessario, String Descricao) throws IOException {

        Projecto p = new Projecto(Nome, Necessario, userlogado, Descricao);

        Pacote pa = new Pacote();
        pa.criaREQPROJ(p.clone());
        enviaPacote(pa);

        try {

            while (Main.ReqProjectoInt == -1) {
                synchronized (Main.inter) {
                    Main.inter.wait();
                }
            }
        } catch (InterruptedException ex) {
            System.out.println(ex.toString());
        }

        if (ReqProjectoInt == 1) {
            JOptionPane.showMessageDialog(null, "Projecto Publicado");
            mapProjectos.insereProjecto(p.clone());
        } else {
            JOptionPane.showMessageDialog(null, "Já existe nome igual a esse");
        }

        Main.ReqProjectoInt = -1;

        //mapProjectos.insereProjecto(p);
    }

    public static void iniciaMapProjectos() {
        //Login feito entao actualizar os maps....

        //synchronized (iniciaDadosThread) {
        //Pedir os maps
        Pacote p = new Pacote();
        p.criaREQMAPPROJ();
        enviaPacote(p);
//        iniciaDadosThread = new Thread();
//        iniciaDadosThread.start();
        try {
            while (Main.ACTMAPInt == 0) {
                synchronized (Main.inter) {
                    Main.inter.wait();
                }
            }
            Main.ACTMAPInt = 0;

        } catch (InterruptedException ex) {
            System.out.println(ex.toString());
        }
        // }
    }

    public static void iniciaInterfaceSD() {

        inter = new InterfaceSD();
        inter.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        inter.setVisible(true);
        Main.iniciaMapProjectos();

    }

    public static void iniciathreadcliente() {
        //Iniciar a thread de leitura do socket
        tcl = new ThreadClienteListener(s);
        tcl.start();
    }

    public static void iniciasocket() throws IOException {
        s = new Socket("localhost", 1337);
        os = s.getOutputStream();
        oos = new ObjectOutputStream(os);
    }

    public static void stopligacao() {
        try {
            Main.inter.addNotif("-----------------------------------------");
        } catch (NullPointerException n) {
        }
        Main.activa = 0;
        Main.stopthread();
        Main.stopsocket();
        JOptionPane j = null;
        int showOptionDialog = 0;//SIM
        while (showOptionDialog == 0) {
            showOptionDialog = j.showOptionDialog(Main.inter, "Perdeu-se a Ligação ao Servidor! Tentar de novo?", "Erro", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
            if (showOptionDialog == 0) {//SIM
                Main.retrylig();
            }

            if (Main.activa == 1) {
                break;
            }
        }

        if (Main.activa != 1) {

            System.exit(-1);
        }

    }

    private static void stopthread() {

        if (Main.tcl.isAlive()) {
            Main.tcl.interrupt();
        }
    }

    private static void stopsocket() {
        try {
            Main.s.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void retrylig() {
        try {
            Main.iniciasocket();
            Main.activa = 1;
        } catch (IOException ex) {
            Main.activa = 0;
        }

        if (Main.activa == 1)//esta ligado
        {
            Main.iniciathreadcliente();
            Pacote p = new Pacote();
            p.criaREQRETRY(Main.userlogado);
            Main.enviaPacote(p);

        }
    }

    public void addProjecto(Projecto p) {
        mapProjectos.vals.put(p.getNome(), p);
    }

    public static void main(String[] args) {
        ReqProjectoInt = -1;
        ReqLoginInt = -1;
        ReqProjectoNomeInt = -1;
        ReqProjectoInt = -1;
        ACTMAPInt = -1;
        ReqRegisto = -1;
        //Interface
        //Fazer o connect com o socket
        try {
            iniciasocket();
            activa = 1;
        } catch (IOException ex) {
            activa = 0;
        }
        if (activa == 1) {
            iniciathreadcliente();
        }

        //inicializar os dados
        formato = "dd/MM/yyyy HH:mm:ss";

        mapProjectos = new HashMapObs();

        inicio = new Inicio();
        inicio.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        inicio.setVisible(true);
    }

    public static void enviaPacote(Pacote p) {//So envia pedidos sem dados

        if (p != null) {

            try {
                oos.writeObject(p);
                System.out.println("envia " + p.getTipo());
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }

        }

    }
    /*
     public static synchronized int getinccodU() {
     codUtilizadores++;
     return codUtilizadores - 1;
     }

     public static synchronized int getinccodP() {
     codProjectos++;
     return codProjectos - 1;
     }

     public static void teste() {
     Utilizador u1 = new Utilizador("USER1", "PASS");
     Utilizador u2 = new Utilizador("USER2", "PASS");
     Utilizador u3 = new Utilizador("USER3", "PASS");
     p1 = new Projecto("Caneta de Chocolate", 500, 1, 10);

     Timer timer = new Timer();
     //
     //        timer.schedule(new TimerTask() {
     //
     //            @Override
     //            public void run() {
     //              Calendar date1 = Calendar.getInstance();
     //              if(p1.isFinalizado()==false)//JA ACABOU ?
     //                  System.out.println("date1:" + date1.getTime() + "p1:" + p1.getFim().getTime() + "\n" + date1.before(p1.getFim().getTime()));
     //                if(date1.getTimeInMillis()>p1.getFim().getTimeInMillis()){//SE DATA FIM > ACTUAL -> ACABAR
     //                    p1.setFinalizado(true);
     //                    System.out.println("P1-ACABOU!");
     //                    System.out.println(p1.toString());
     //                    this.cancel();//PARA O TIMER
     //                }
     //            }
     //          }, 1000,1000);

     System.out.println(u1.toString());
     System.out.println(u2.toString());
     System.out.println(u3.toString());
     System.out.println(p1.toString());

     while (p1.isFinalizado() == false) {
     System.out.println(p1.isFinalizado());
     Scanner keyboard = new Scanner(System.in);
     System.out.println(p1.printEstado());
     System.out.println("Quanto?:");
     int myint = keyboard.nextInt();
     if (p1.addeuros(myint)) {
     System.out.println("Adicionas-te " + myint + " €");
     } else {
     System.out.println("P1 já finalizado");
     }
     }

     }*/
}
