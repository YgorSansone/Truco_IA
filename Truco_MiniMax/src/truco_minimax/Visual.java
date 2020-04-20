/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truco_minimax;

import java.awt.Toolkit;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import sun.awt.image.ToolkitImage;

/**
 *
 * @author ygor-pc
 */
public class Visual extends javax.swing.JFrame {

    /**
     * Creates new form Visual
     */
    private Scanner scanner;
    public Integer cartanumero = null;

    public Visual() {
        initComponents();
        carta01.setVisible(false);
        carta02.setVisible(false);
        carta03.setVisible(false);
        carta04.setVisible(false);
        carta05.setVisible(false);
        carta06.setVisible(false);
        turno_min.setVisible(false);
        turno_max.setVisible(false);
        Carta1.setVisible(false);
        Carta2.setVisible(false);
        Carta3.setVisible(false);
        LOSER.setVisible(false);
        WIN.setVisible(false);
        setIcon();
    }

    public void tete() {

    }

    public void mostrarCartas(ArrayList<Carta> listaCartasJogador) {
        System.out.println("\"/imagens/\" + listaCartasJogador.get(0).rank.toString() + \"-\" + listaCartasJogador.get(0).naipe.toString() + \".png\"");
        ImageIcon carta1 = new ImageIcon(getClass().getResource("/imagens/" + listaCartasJogador.get(0).rank.toString() + "-" + listaCartasJogador.get(0).naipe.toString() + ".png"));
        ImageIcon carta2 = new ImageIcon(getClass().getResource("/imagens/" + listaCartasJogador.get(1).rank.toString() + "-" + listaCartasJogador.get(1).naipe.toString() + ".png"));
        ImageIcon carta3 = new ImageIcon(getClass().getResource("/imagens/" + listaCartasJogador.get(2).rank.toString() + "-" + listaCartasJogador.get(2).naipe.toString() + ".png"));
        carta01.setIcon(carta1);
        carta02.setIcon(carta2);
        carta03.setIcon(carta3);
        //mostrando cartas jogador
        carta01.setVisible(true);
        carta02.setVisible(true);
        carta03.setVisible(true);
        //mostrando cartas da maquina
        carta04.setVisible(true);
        carta05.setVisible(true);
        carta06.setVisible(true);

    }

    public void mostrarCartasVira(Carta vira) {
        ImageIcon cartaMonte = new ImageIcon(getClass().getResource("/imagens/" + vira.rank.toString() + "-" + vira.naipe.toString() + ".png"));
        cartavira.setIcon(cartaMonte);
        cartavira.setVisible(true);
    }

    //mostra carta do jogador na tela
    public void mostrarCartasTurnoJogadorvirada() {
        ImageIcon cartaTurno = new ImageIcon(getClass().getResource("/imagens/" + "back-blue" + ".png"));
        turno_max.setIcon(cartaTurno);
        turno_max.setVisible(true);
    }

    public void mostrarCartasTurnoJogador(Carta jogador) {
        ImageIcon cartaTurno = new ImageIcon(getClass().getResource("/imagens/" + jogador.rank.toString() + "-" + jogador.naipe.toString() + ".png"));
        turno_max.setIcon(cartaTurno);
        turno_max.setVisible(true);
    }

    //mostra carta da maquina na tela
    public void mostrarCartasTurnoMaquina(Carta pc) {
        ImageIcon cartaTurno = new ImageIcon(getClass().getResource("/imagens/" + pc.rank.toString() + "-" + pc.naipe.toString() + ".png"));
        turno_min.setIcon(cartaTurno);
        turno_min.setVisible(true);
    }

    public void jogoucarta(int pos) {
        if (pos == 0) {
            Carta1.setVisible(false);
            carta01.setVisible(false);
        } else if (pos == 1) {
            Carta2.setVisible(false);
            carta02.setVisible(false);
        } else {
            Carta3.setVisible(false);
            carta03.setVisible(false);
        }
    }

    public void jogoucartapc(int pos) {
        if (pos == 0) {
            carta04.setVisible(false);
        } else if (pos == 1) {
            carta05.setVisible(false);
        } else {
            carta06.setVisible(false);
        }
    }

    public int jogou(State state, int rodada) {
        int posCartaEscolhida = 0;
        System.out.println("Rodada : : : " + rodada);
        if (rodada == 3) {
            Object[] options = {
                state.max.cartamao.get(0).rank + " " + state.max.cartamao.get(0).naipe.toString(),
                state.max.cartamao.get(1).rank + " " + state.max.cartamao.get(1).naipe.toString(),
                state.max.cartamao.get(2).rank + " " + state.max.cartamao.get(2).naipe.toString()};
            posCartaEscolhida = JOptionPane.showOptionDialog(null,
                    "Escolha uma carta",
                    "Cartas Jogador",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[2]);
            jogoucarta(posCartaEscolhida);
        } else if (rodada == 2) {
            turno_max.setVisible(false);
            Object[] options = {
                state.max.cartamao.get(0).rank + " " + state.max.cartamao.get(0).naipe.toString(),
                state.max.cartamao.get(1).rank + " " + state.max.cartamao.get(1).naipe.toString()};
            posCartaEscolhida = JOptionPane.showOptionDialog(null,
                    "Escolha uma carta",
                    "Cartas Jogador",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);
        } else {
            Object[] options = {
                state.max.cartamao.get(0).rank + " " + state.max.cartamao.get(0).naipe.toString()};
            posCartaEscolhida = JOptionPane.showOptionDialog(null,
                    "Escolha uma carta",
                    "Cartas Jogador",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            Carta3.setVisible(false);
            carta03.setVisible(false);
        }
        return posCartaEscolhida;

    }

    public void truco(State state) {
        try {
            MiniMax minimax = new MiniMax(state);
//            scanner = new Scanner(System.in);
            boolean howstart = true;

            while (state.howWins() == 0) {
                System.out.println();
                System.out.println("Score: " + state.max.score);
                scoremax.setText("" + state.max.score);
                scoremin.setText("" + state.min.score);
                if (state.max.cartamao.size() == 3) {
                    Carta1.setText(state.max.cartamao.get(0).toString());
                    Carta2.setText(state.max.cartamao.get(1).toString());
                    Carta3.setText(state.max.cartamao.get(2).toString());
                } else if (state.max.cartamao.size() == 2) {
                    Carta1.setText(state.max.cartamao.get(0).toString());
                    Carta2.setText(state.max.cartamao.get(1).toString());

                } else {
                    Carta1.setText(state.max.cartamao.get(0).toString());
                }

                if (state.isstart) {
                    clearConsole();
                    System.out.println();
                    System.out.println("Escolha sua carta para jogar, vc tem : " + state.max.cartamao.size() + " cartas");
                    System.out.println("" + state.max.cartamao);
                    int carta = jogou(state, state.max.cartamao.size());
                    Carta cartaadd = state.max.cartamao.get(carta);
                    state.activecartasMax.add(cartaadd);
                    jogoucarta(carta);
                    mostrarCartasTurnoJogador(cartaadd);
                    System.out.println();
                    System.out.println("A sua carta : " + state.max.cartamao.remove(carta).toString());
                    Carta cartapc = minimax.expandstate(state.rodada, howstart);
                    state.activecartasMin.add(cartapc);
                    int cartapcc = state.activecartasMin.indexOf(cartapc);

                    mostrarCartasTurnoMaquina(cartapc);
                    sleep(1000);
                    jogoucartapc(cartapcc);
                    System.out.println("A carta do PC : " + cartapc.toString());

                } else if (!state.isstart) {
                    clearConsole();
                    Carta cartapc = minimax.expandstate(state.rodada, howstart);
                    state.activecartasMin.add(cartapc);
                    int cartapccc = state.activecartasMin.indexOf(cartapc);
                    System.out.println("A carta do PC :" + cartapc.toString());
                    System.out.println();
                    System.out.println("Escolha sua carta para jogar, vc tem : " + state.max.cartamao.size() + " cartas");
                    System.out.println("" + state.max.cartamao);
                    mostrarCartasTurnoMaquina(cartapc);
                    sleep(1000);
                    jogoucartapc(cartapccc);
                    int carta = jogou(state, state.max.cartamao.size());
                    jogoucarta(carta);
                    Carta cartaadd = state.max.cartamao.get(carta);
                    state.activecartasMax.add(cartaadd);
                    mostrarCartasTurnoJogador(cartaadd);
                    System.out.println();
                    System.out.println("A carta que voc� escolheu foi: " + state.max.cartamao.remove(carta).toString());
                }
                state.atualizaScore();
                state.rodada++;
            }
            if (state.howWins() == 1) {
                System.out.println("O vencedor foi o Computador!");
                LOSER.setVisible(true);
                Carta1.setVisible(false);
                Carta2.setVisible(false);
                Carta3.setVisible(false);
                carta01.setVisible(false);
                carta02.setVisible(false);
                carta03.setVisible(false);
                carta04.setVisible(false);
                carta05.setVisible(false);
                carta06.setVisible(false);
            } else {
                System.out.println("Voce ganhou !!!");
                WIN.setVisible(true);
                Carta1.setVisible(false);
                Carta2.setVisible(false);
                Carta3.setVisible(false);
                carta01.setVisible(false);
                carta02.setVisible(false);
                carta03.setVisible(false);
                carta04.setVisible(false);
                carta05.setVisible(false);
                carta06.setVisible(false);
            }

        } catch (Exception e) {
            System.out.println("O erro eh " + e);
        }
    }

    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows 10")) {
                Runtime.getRuntime().exec("cls");
            } else {
                //linus e mac
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        WIN = new javax.swing.JLabel();
        LOSER = new javax.swing.JLabel();
        carta02 = new javax.swing.JLabel();
        carta03 = new javax.swing.JLabel();
        carta01 = new javax.swing.JLabel();
        carta05 = new javax.swing.JLabel();
        carta06 = new javax.swing.JLabel();
        carta04 = new javax.swing.JLabel();
        turno_min = new javax.swing.JLabel();
        cartavira = new javax.swing.JLabel();
        turno_max = new javax.swing.JLabel();
        Carta3 = new javax.swing.JButton();
        Carta1 = new javax.swing.JButton();
        Carta2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        scoremax = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        scoremin = new javax.swing.JLabel();
        jogar = new javax.swing.JButton();
        fundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Truco_MiniMax");
        setBackground(new java.awt.Color(51, 51, 51));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(51, 51, 51));
        setIconImages(null);
        setMinimumSize(new java.awt.Dimension(727, 537));
        setSize(new java.awt.Dimension(700, 700));
        getContentPane().setLayout(null);

        WIN.setBackground(new java.awt.Color(51, 51, 51));
        WIN.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        WIN.setForeground(new java.awt.Color(33, 209, 49));
        WIN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        WIN.setText("WIN");
        WIN.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(WIN);
        WIN.setBounds(0, 250, 730, 290);

        LOSER.setBackground(new java.awt.Color(51, 51, 51));
        LOSER.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        LOSER.setForeground(new java.awt.Color(255, 0, 0));
        LOSER.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LOSER.setText("LOSER");
        LOSER.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(LOSER);
        LOSER.setBounds(0, 0, 730, 160);

        carta02.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/back-blue.png"))); // NOI18N
        getContentPane().add(carta02);
        carta02.setBounds(240, 310, 80, 130);

        carta03.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/back-blue.png"))); // NOI18N
        getContentPane().add(carta03);
        carta03.setBounds(420, 310, 80, 130);

        carta01.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/back-blue.png"))); // NOI18N
        getContentPane().add(carta01);
        carta01.setBounds(70, 310, 80, 130);

        carta05.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/back-blue.png"))); // NOI18N
        getContentPane().add(carta05);
        carta05.setBounds(240, 10, 80, 130);

        carta06.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/back-blue.png"))); // NOI18N
        getContentPane().add(carta06);
        carta06.setBounds(420, 10, 80, 130);

        carta04.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/back-blue.png"))); // NOI18N
        getContentPane().add(carta04);
        carta04.setBounds(70, 10, 80, 130);

        turno_min.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/back-blue.png"))); // NOI18N
        turno_min.setFocusable(false);
        getContentPane().add(turno_min);
        turno_min.setBounds(70, 160, 80, 130);

        cartavira.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/back-blue.png"))); // NOI18N
        getContentPane().add(cartavira);
        cartavira.setBounds(240, 160, 80, 130);

        turno_max.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/back-blue.png"))); // NOI18N
        getContentPane().add(turno_max);
        turno_max.setBounds(420, 160, 80, 130);

        Carta3.setText("Carta 3");
        Carta3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Carta3ActionPerformed(evt);
            }
        });
        getContentPane().add(Carta3);
        Carta3.setBounds(410, 450, 83, 29);

        Carta1.setText("Carta 1");
        Carta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Carta1ActionPerformed(evt);
            }
        });
        getContentPane().add(Carta1);
        Carta1.setBounds(60, 450, 83, 29);

        Carta2.setText("Carta 2");
        Carta2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Carta2ActionPerformed(evt);
            }
        });
        getContentPane().add(Carta2);
        Carta2.setBounds(230, 450, 83, 29);

        jLabel1.setFont(new java.awt.Font("Vineta BT", 0, 24)); // NOI18N
        jLabel1.setText("Score : ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(500, 340, 150, 80);

        scoremax.setFont(new java.awt.Font("Vineta BT", 0, 24)); // NOI18N
        scoremax.setText("0");
        getContentPane().add(scoremax);
        scoremax.setBounds(640, 340, 60, 80);

        jLabel12.setFont(new java.awt.Font("Vineta BT", 0, 24)); // NOI18N
        jLabel12.setText("Score : ");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(500, 50, 150, 80);

        scoremin.setFont(new java.awt.Font("Vineta BT", 0, 24)); // NOI18N
        scoremin.setText("0");
        getContentPane().add(scoremin);
        scoremin.setBounds(640, 50, 60, 80);

        jogar.setText("Jogar");
        jogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jogarActionPerformed(evt);
            }
        });
        getContentPane().add(jogar);
        jogar.setBounds(540, 220, 71, 29);

        fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fundo1.jpg"))); // NOI18N
        fundo.setText("Truco");
        getContentPane().add(fundo);
        fundo.setBounds(0, 0, 720, 520);

        getAccessibleContext().setAccessibleDescription("Truco MaxiMini"); // NOI18N

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jogarActionPerformed
        // TODO add your handling code here:
        Baralho baralho = new Baralho();
        baralho.geraBaralho();
        baralho.atribuirPesos();
        baralho.embaralhar();
        baralho.sortearvira();
        baralho.atribuirManilhas();
        baralho.gerarPlayers();
        baralho.distribuiCartas();
        System.out.println("Computador : " + baralho.handmin);
        System.out.println("Voce : " + baralho.handmax);
        mostrarCartas((ArrayList<Carta>) baralho.handmax);
        System.out.println("tenta esse " + (ArrayList<Carta>) baralho.handmax);
        mostrarCartasVira(baralho.vira);
        State state = new State(baralho.vira, baralho.manilha, baralho.players);
        MiniMax search = new MiniMax(state);
        jogar.setVisible(false);
        Carta1.setVisible(true);
        Carta2.setVisible(true);
        LOSER.setVisible(false);
        Carta3.setVisible(true);
        truco(state);
    }//GEN-LAST:event_jogarActionPerformed

    private void Carta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Carta1ActionPerformed
        // TODO add your handling code here:
        jogoucarta(0);
    }//GEN-LAST:event_Carta1ActionPerformed

    private void Carta2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Carta2ActionPerformed
        // TODO add your handling code here:
        jogoucarta(1);
    }//GEN-LAST:event_Carta2ActionPerformed

    private void Carta3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Carta3ActionPerformed
        // TODO add your handling code here:
        jogoucarta(2);
    }//GEN-LAST:event_Carta3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws InterruptedException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Visual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Visual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Visual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Visual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Visual().setVisible(true);

            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Carta1;
    private javax.swing.JButton Carta2;
    private javax.swing.JButton Carta3;
    private javax.swing.JLabel LOSER;
    private javax.swing.JLabel WIN;
    private javax.swing.JLabel carta01;
    private javax.swing.JLabel carta02;
    private javax.swing.JLabel carta03;
    private javax.swing.JLabel carta04;
    private javax.swing.JLabel carta05;
    private javax.swing.JLabel carta06;
    private javax.swing.JLabel cartavira;
    private javax.swing.JLabel fundo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JButton jogar;
    private javax.swing.JLabel scoremax;
    private javax.swing.JLabel scoremin;
    private javax.swing.JLabel turno_max;
    private javax.swing.JLabel turno_min;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
//        ImageIcon imv = new ImageIcon(getClass().getResource("/imagens/" +"img" + ".png"));
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagens/" + "img" + ".png")));
    }
}
