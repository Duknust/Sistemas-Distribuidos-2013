package org.view;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.Main;
import org.classes.Projecto;
import org.tipos.Pacote;

public class InterfaceSD extends javax.swing.JFrame implements Observer/*, Runnable */ {

    private DefaultTableModel dtm = new DefaultTableModel();
    private DefaultListModel listProjsModel;
    private DefaultListModel listNotifsModel;

    public void update(Observable observable, Object obj) {
        this.acttabela();
        this.acttexts();
        this.actmeusprojs();
    }

    public synchronized void espera() throws InterruptedException {

        this.wait();

    }

    public void actmeusprojs() {
        listProjsModel.clear();
        if (Main.userlogado.compareTo("") != 0) {
            for (Projecto p : Main.mapProjectos.vals.values()) {
                if (p.getUtilizador().compareTo(Main.userlogado) == 0) {
                    listProjsModel.addElement(p.getNome());
                }
            }
        }
    }

    public void acttexts() {

        String cena = jTextFieldProjUser.getText();

        if (Main.mapProjectos.vals.containsKey(cena)) {
            Projecto p = Main.mapProjectos.vals.get(cena);
            try {

                jTextFieldProjUser.setText(p.getUtilizador());
                jTextFieldProjNome.setText(p.getNome());
                jTextField2.setText(String.valueOf(p.getNecessario()));
                jTextField3.setText(String.valueOf(p.getActual()));
                jTextField4.setText("1");
                jTextPaneDescricao.setText(p.getDescricao());

            } catch (IndexOutOfBoundsException e) {
            }
        }

    }

    public void acttabela() {

        //jTextFieldProjUser.setText("AAA");
        //int con = jTableListaProjectos.getRowCount();
        //JOptionPane.showMessageDialog(null, con);
//        jTextFieldProjUser.setText(Integer.toString(con));
        //
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }

        HashMap<String, Projecto> lm = Main.mapProjectos.vals;
        boolean finalizado = jCheckBox1.isSelected();
        for (Projecto e : lm.values()) {
            if (e.isFinanciado() == finalizado) {
                dtm.addRow(new Object[]{e.getNome(), e.getNecessario(), e.getActual(), e.getDescricao(), e.getUtilizador()});
            }

            //JOptionPane.showMessageDialog(null, "add:" + e.getNome());
        }

    }

    public void addNotif(Pacote p) {
        listNotifsModel.addElement(p.getString2() + " _ " + p.getString1() + " -> " + p.getInteiro1() + " €");
    }

    public void addNotif(String p) {
        listNotifsModel.addElement(p);
    }

    public InterfaceSD() {
        initComponents();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        listProjsModel = new DefaultListModel();
        jListMeusProjs.setModel(listProjsModel);
        listNotifsModel = new DefaultListModel();
        jListNotifs.setModel(listNotifsModel);

        this.setLocationRelativeTo(null);
        String[] colName = new String[]{
            "Nome", "Nec", "Actual", "Desc", "User"};
        Object[][] data = {};

        dtm = new DefaultTableModel(data, colName);

        //jTableListaProjectos = new JTable(dtm);
        jTableListaProjectos.setModel(dtm);

        jTableListaProjectos.getColumnModel()
                .getColumn(0).setMinWidth(170);
        jTableListaProjectos.getColumnModel()
                .getColumn(0).setMaxWidth(170);

        jTableListaProjectos.getColumnModel()
                .getColumn(3).setMinWidth(0);
        jTableListaProjectos.getColumnModel()
                .getColumn(3).setMaxWidth(0);

        jTableListaProjectos.getColumnModel()
                .getColumn(4).setMinWidth(0);
        jTableListaProjectos.getColumnModel()
                .getColumn(4).setMaxWidth(0);
        tabelaaction();
        Main.mapProjectos.addObserver(this);
        mostrartudo();

        jLabel2.setText("Olá " + Main.userlogado);

    }

    public void escondertudo() {
        jLabel16.setVisible(false);
        jLabel21.setVisible(false);
        jLabel17.setVisible(false);
        jLabel18.setVisible(false);
        jLabel1.setVisible(
                false);
        jTableListaProjectos.setVisible(
                false);

        jTextPaneDescricao.setVisible(
                false);
        jLabel6.setVisible(
                false);
        jTextField2.setVisible(
                false);
        jLabelProjNome.setVisible(
                false);
        jLabel19.setVisible(
                false);
        jLabel7.setVisible(
                false);
        jTextField3.setVisible(
                false);
        jLabel8.setVisible(
                false);
        jTextField4.setVisible(
                false);
        jButtonOferecer.setVisible(
                false);
        jLabel14.setVisible(
                false);
        jListNotifs.setVisible(
                false);
        jTextFieldProjUser.setVisible(
                false);
        jTextFieldProjNome.setVisible(
                false);
        jButton3.setVisible(
                false);
        jListMeusProjs.setVisible(
                false);
        jLabel9.setVisible(
                false);
        jLabel11.setVisible(
                false);
        jTextFieldMPEstado.setVisible(
                false);
        jLabel12.setVisible(
                false);
        jTextFieldMPNec.setVisible(
                false);
        jLabel13.setVisible(
                false);
        jTextFieldMPActual.setVisible(
                false);
        jLabel15.setVisible(false);
        jLabel20.setVisible(false);
        jTextFieldMPNome.setVisible(false);
        jScrollPane6.setVisible(
                false);
        jScrollPane5.setVisible(
                false);
        jScrollPane4.setVisible(
                false);
        jScrollPane3.setVisible(
                false);
        jScrollPane2.setVisible(
                false);
        jScrollPane1.setVisible(
                false);
        jSeparator1.setVisible(
                false);
        jButtonLimparNotifs.setVisible(false);

    }

    public void mostrartudo() {
        jLabel16.setVisible(true);
        jLabel21.setVisible(true);
        jLabel17.setVisible(true);
        jLabel18.setVisible(true);
        jLabel1.setVisible(true);
        jTableListaProjectos.setVisible(true);
        jLabelProjNome.setVisible(true);
        jTextFieldProjUser.setVisible(true);
        jTextPaneDescricao.setVisible(true);
        jLabel6.setVisible(true);
        jTextField2.setVisible(true);
        jLabel7.setVisible(true);
        jTextField3.setVisible(true);
        jLabel8.setVisible(true);
        jTextField4.setVisible(true);
        jButtonOferecer.setVisible(true);
        jLabel14.setVisible(true);
        jListNotifs.setVisible(true);
        jTextFieldProjUser.setVisible(true);
        jButton3.setVisible(true);
        jListMeusProjs.setVisible(true);
        jLabel9.setVisible(true);
        jLabel11.setVisible(true);
        jTextFieldMPEstado.setVisible(true);
        jLabel12.setVisible(true);
        jTextFieldMPNec.setVisible(true);
        jLabel13.setVisible(true);
        jTextFieldMPActual.setVisible(true);
        jScrollPane6.setVisible(true);
        jScrollPane5.setVisible(true);
        jScrollPane4.setVisible(true);
        jScrollPane3.setVisible(true);
        jScrollPane2.setVisible(true);
        jScrollPane1.setVisible(true);
        jSeparator1.setVisible(true);
        jButtonLimparNotifs.setVisible(true);
        jLabel15.setVisible(true);
        jLabel20.setVisible(true);
        jLabel19.setVisible(true);
        jTextFieldProjNome.setVisible(true);
        jTextFieldMPNome.setVisible(true);

    }

    public void tabelaaction() {

        jTableListaProjectos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                // print first column value from selected row
                try {
                    jTextFieldProjUser.setText(jTableListaProjectos.getValueAt(jTableListaProjectos.getSelectedRow(), 4).toString());
                    jTextFieldProjNome.setText(jTableListaProjectos.getValueAt(jTableListaProjectos.getSelectedRow(), 0).toString());
                    jTextField2.setText(jTableListaProjectos.getValueAt(jTableListaProjectos.getSelectedRow(), 1).toString());
                    jTextField3.setText(jTableListaProjectos.getValueAt(jTableListaProjectos.getSelectedRow(), 2).toString());
                    jTextField4.setText("1");
                    jTextPaneDescricao.setText(jTableListaProjectos.getValueAt(jTableListaProjectos.getSelectedRow(), 3).toString());
                    //System.out.println(jTableListaProjectos.getValueAt(jTableListaProjectos.getSelectedRow(), 0).toString());
                    if (jTextFieldProjUser.getText().compareTo(Main.userlogado) == 0) {
                        jButtonOferecer.setToolTipText("Não pode oferecer a projectos seus!");
                        jButtonOferecer.setEnabled(false);
                    } else {
                        jButtonOferecer.setEnabled(true);
                    }
                } catch (IndexOutOfBoundsException e) {
                }
            }
        });

        jListMeusProjs.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                // print first column value from selected row
                try {
                    String nomeP = jListMeusProjs.getSelectedValue().toString();
                    Projecto p = Main.mapProjectos.vals.get(nomeP);
                    int actual = p.getActual(), necessario = p.getNecessario();

                    if (actual >= necessario) {
                        jTextFieldMPEstado.setText("FINANCIDADO");
                        jTextFieldMPEstado.setBackground(Color.GREEN);
                    } else {
                        jTextFieldMPEstado.setText("EM FINANCIAMENTO");
                        jTextFieldMPEstado.setBackground(Color.YELLOW);
                    }

                    jTextFieldMPNome.setText(nomeP);
                    jTextFieldMPActual.setText(String.valueOf(actual));
                    jTextFieldMPNec.setText(String.valueOf(necessario));
                    //System.out.println(jTableListaProjectos.getValueAt(jTableListaProjectos.getSelectedRow(), 0).toString());
                } catch (IndexOutOfBoundsException e) {
                } catch (NullPointerException n) {
                }
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        TitleLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabelProjNome = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButtonOferecer = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldMPEstado = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldMPNec = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldMPActual = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableListaProjectos = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListMeusProjs = new javax.swing.JList();
        jScrollPane5 = new javax.swing.JScrollPane();
        jListNotifs = new javax.swing.JList();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextPaneDescricao = new javax.swing.JTextPane();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButtonLimparNotifs = new javax.swing.JButton();
        jTextFieldProjNome = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextFieldProjUser = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldMPNome = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabelXSAIR = new javax.swing.JLabel();
        jTextFieldPesquisa = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextFieldMPMaisCon = new javax.swing.JTextField();
        jButtonVerContrib = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BananaStarter");
        setMaximumSize(new java.awt.Dimension(833, 489));
        setMinimumSize(new java.awt.Dimension(833, 489));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setPreferredSize(new java.awt.Dimension(833, 489));
        setResizable(false);

        TitleLabel.setBackground(new java.awt.Color(0, 0, 0));
        TitleLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        TitleLabel.setForeground(java.awt.Color.orange);
        TitleLabel.setText("BananaStarter");

        jLabel2.setText("USER");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Lista De Projectos");

        jLabelProjNome.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelProjNome.setText("Nome");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Necessário");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Actual");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Oferecer");

        jTextField2.setEditable(false);
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField2.setEnabled(false);

        jTextField3.setEditable(false);
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField3.setEnabled(false);

        jTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField4.setText("0");
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jButtonOferecer.setBackground(new java.awt.Color(153, 255, 153));
        jButtonOferecer.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonOferecer.setText("OFERECER");
        jButtonOferecer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOferecerActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Meus Projectos");

        jButton3.setText("Propor");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Estado");

        jTextFieldMPEstado.setEditable(false);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Necessário");

        jTextFieldMPNec.setEditable(false);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Actual");

        jTextFieldMPActual.setEditable(false);

        jTableListaProjectos.setAutoCreateRowSorter(true);
        jTableListaProjectos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nome", "Necessário", "Actual"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTableListaProjectos);

        jScrollPane1.setViewportView(jListMeusProjs);

        jListNotifs.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane5.setViewportView(jListNotifs);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Notificações");

        jLabel15.setText("Crowd Funding Manager");

        jTextPaneDescricao.setEnabled(false);
        jScrollPane6.setViewportView(jTextPaneDescricao);

        jLabel16.setText("€");

        jLabel17.setText("€");

        jLabel18.setText("€");

        jButtonLimparNotifs.setText("Limpar");
        jButtonLimparNotifs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimparNotifsActionPerformed(evt);
            }
        });

        jTextFieldProjNome.setEditable(false);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Utilizador");

        jTextFieldProjUser.setEditable(false);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Nome");

        jTextFieldMPNome.setEditable(false);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Descrição");

        jLabelXSAIR.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelXSAIR.setText("X");
        jLabelXSAIR.setToolTipText("SAIR");
        jLabelXSAIR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelXSAIRMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelXSAIRMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelXSAIRMouseExited(evt);
            }
        });

        jTextFieldPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldPesquisaKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel3.setText("Financiado");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Mais Contribuições");

        jTextFieldMPMaisCon.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldMPMaisCon.setText("0");

        jButtonVerContrib.setText("Ver");
        jButtonVerContrib.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerContribActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TitleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelXSAIR))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jTextFieldPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jCheckBox1))
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel19)
                                                    .addComponent(jLabelProjNome))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jTextFieldProjUser, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                                                    .addComponent(jTextFieldProjNome)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel6)
                                                    .addComponent(jLabel7)
                                                    .addComponent(jLabel21))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                                                            .addComponent(jTextField2))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel16)
                                                            .addComponent(jLabel18))
                                                        .addGap(105, 105, 105))))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addGap(18, 18, 18)
                                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel17)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButtonOferecer))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel22)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jTextFieldMPMaisCon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonVerContrib))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(288, 288, 288)
                                        .addComponent(jLabel3))
                                    .addComponent(jLabel9)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton3)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel20))
                                        .addGap(4, 4, 4)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextFieldMPNome, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldMPEstado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldMPNec, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldMPActual, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jSeparator1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel14)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButtonLimparNotifs)
                                .addGap(51, 51, 51)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel14))
                            .addComponent(jLabelXSAIR))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonLimparNotifs))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelProjNome)
                                    .addComponent(jTextFieldProjNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(jTextFieldProjUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldMPMaisCon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22)
                                    .addComponent(jButtonVerContrib))
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonOferecer)
                                    .addComponent(jLabel17)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TitleLabel)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jTextFieldPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jCheckBox1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldMPNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldMPEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldMPNec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldMPActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton3)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(53, 53, 53))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        SubmeterProjecto sp = new SubmeterProjecto(this);
        sp.setVisible(true);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButtonOferecerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOferecerActionPerformed

        int valor = 0;
        try {
            valor = Integer.parseInt(jTextField4.getText());
        } catch (NumberFormatException e) {
            //JOptionPane.showMessageDialog(null, e.toString());
        }
        if (valor <= 0) {
            JOptionPane.showMessageDialog(null, valor + " - Não é válido");
        } else {
            String nome = jTextFieldProjNome.getText();

            if (nome.compareTo("") != 0) {
                Pacote p = new Pacote();
                p.criaREQADDEUROS(valor, jTextFieldProjNome.getText(), Main.userlogado/*jTableListaProjectos.getValueAt(jTableListaProjectos.getSelectedRow(), 0).toString()*/);
                Main.enviaPacote(p);
            } else {
                JOptionPane.showMessageDialog(null, "Não é válido");
            }

        }
    }//GEN-LAST:event_jButtonOferecerActionPerformed

    private void jButtonLimparNotifsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimparNotifsActionPerformed

        listNotifsModel.clear();
    }//GEN-LAST:event_jButtonLimparNotifsActionPerformed

    private void jLabelXSAIRMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelXSAIRMouseEntered

        jLabelXSAIR.setForeground(Color.red);

    }//GEN-LAST:event_jLabelXSAIRMouseEntered

    private void jLabelXSAIRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelXSAIRMouseClicked

        try {
            Main.tcl.interrupt();
            Main.s.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
        }
        System.exit(1);
    }//GEN-LAST:event_jLabelXSAIRMouseClicked

    private void jLabelXSAIRMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelXSAIRMouseExited
        jLabelXSAIR.setForeground(Color.BLACK);

    }//GEN-LAST:event_jLabelXSAIRMouseExited

    private void jTextFieldPesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPesquisaKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Main.inter.pesquisa(jTextFieldPesquisa.getText());
        }
    }//GEN-LAST:event_jTextFieldPesquisaKeyPressed

    private void jButtonVerContribActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerContribActionPerformed
        // TODO add your handling code here:
        String nomeProj = jTextFieldProjNome.getText();
        int nc = -1;
        try {
            nc = Integer.parseInt(jTextFieldMPMaisCon.getText());
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(null, nc + " - Não é válido");
        }

        MaisContrib mc;
        if (nc > -1) {
            mc = new MaisContrib(nomeProj, nc);
        } else {
            JOptionPane.showMessageDialog(null, nc + " - Não é válido");
        }
    }//GEN-LAST:event_jButtonVerContribActionPerformed
    /*
     @Override
     public void run() {

     this.setVisible(true);
     }*/

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Metal look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfaceSD.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceSD.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceSD.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceSD.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //
        InterfaceSD inter = new InterfaceSD();

        inter.tabelaaction();


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfaceSD().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TitleLabel;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonLimparNotifs;
    private javax.swing.JButton jButtonOferecer;
    private javax.swing.JButton jButtonVerContrib;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelProjNome;
    private javax.swing.JLabel jLabelXSAIR;
    private javax.swing.JList jListMeusProjs;
    private javax.swing.JList jListNotifs;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTableListaProjectos;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextFieldMPActual;
    private javax.swing.JTextField jTextFieldMPEstado;
    private javax.swing.JTextField jTextFieldMPMaisCon;
    private javax.swing.JTextField jTextFieldMPNec;
    private javax.swing.JTextField jTextFieldMPNome;
    private javax.swing.JTextField jTextFieldPesquisa;
    private javax.swing.JTextField jTextFieldProjNome;
    private javax.swing.JTextField jTextFieldProjUser;
    private javax.swing.JTextPane jTextPaneDescricao;
    // End of variables declaration//GEN-END:variables

    public void pesquisa(String text) {
        HashMap<String, Projecto> result = new HashMap<>();
        boolean finalizado = jCheckBox1.isSelected();

        if (text.compareTo("") != 0) {
            result = Main.mapProjectos.getbynome(text, finalizado);

        } else {
            if (finalizado == false) {
                result = Main.mapProjectos.getactivos();
            } else {
                result = Main.mapProjectos.getfinalizados();
            }
        }

        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }

        if (result.size() > 0) {
            for (Projecto e : result.values()) {

                dtm.addRow(new Object[]{e.getNome(), e.getNecessario(), e.getActual(), e.getDescricao(), e.getUtilizador()});

                //JOptionPane.showMessageDialog(null, "add:" + e.getNome());
            }
        }

        acttexts();

    }
}
