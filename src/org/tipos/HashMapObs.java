package org.tipos;

//import dss_empregados.Empregado;
import org.classes.Projecto;
import java.util.*;
import java.io.*;

public class HashMapObs extends Observable implements Serializable {

    public HashMap<String, Projecto> vals = new HashMap<>();

    public HashMapObs() {
        vals = new HashMap<String, Projecto>();
    }

    public HashMap<String, Projecto> getListaProjectos() {
        return vals;
    }

    public void setListaProjectos(HashMap<String, Projecto> listaProjectos) {
        this.vals.clear();
        this.vals.putAll(listaProjectos);
        setChanged();

        notifyObservers();
    }

    public void insereProjecto(Projecto pr) {

        vals.put(pr.getNome(), pr);

        setChanged();

        notifyObservers();

    }

    public void addeurosproj(String nome, int euros) {

        if (vals.containsKey(nome)) {//se existe projecto
            boolean ret = vals.get(nome).addeuros(euros);//contém LOCK
            if (ret) {
                setChanged();
                notifyObservers();
            }
        }

    }

    public String getuserprojecto(String nome) {

        if (vals.containsKey(nome)) {
            return vals.get(nome).getUtilizador();
        } else {
            return "";
        }

    }

    public HashMap<String, Projecto> getbynome(String nome, boolean finalizado) {
        HashMap<String, Projecto> pesq = new HashMap<>();

        for (Projecto p : this.vals.values()) {
            if ((p.getNome().contains(nome) || p.getDescricao().contains(nome)) && p.isFinanciado() == finalizado) {
                pesq.put(p.getNome(), p.clone());
            }

        }

        return pesq;
    }

    public void setProj(Projecto pr) {

        synchronized (this.vals) {
            if (pr != null) {
                vals.put(pr.getNome(), pr);
                setChanged();
                notifyObservers();
            }

        }

    }

    public void gravaObj(String fich) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(fich));
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }

    public void gravaTxt(String fich) throws IOException {
        PrintWriter pw = new PrintWriter(fich);
        pw.print(this);
        pw.flush();
        pw.close();

    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Lista de Projectos:\n");
        Iterator i = vals.values().iterator();
        Projecto p;
        while (i.hasNext()) {
            p = (Projecto) i.next();
            s.append(p.toString());
        }
        return s.toString();
    }

    public HashMap<String, Projecto> getactivos() {

        HashMap<String, Projecto> result = new HashMap<>();
        for (Projecto p : vals.values()) {
            if (p.isFinanciado() == false) {
                result.put(p.getNome(), p.clone());
            }
        }
        return result;
    }

    public HashMap<String, Projecto> getfinalizados() {

        HashMap<String, Projecto> result = new HashMap<>();
        for (Projecto p : vals.values()) {
            if (p.isFinanciado() == true) {
                result.put(p.getNome(), p.clone());
            }
        }
        return result;
    }
}
