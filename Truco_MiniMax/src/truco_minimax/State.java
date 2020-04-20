package truco_minimax;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class State {

    public Carta mesa;
    public List<Carta> manilha;
    public List<Carta> activecartasMax = new ArrayList<Carta>();
    public List<Carta> activecartasMin = new ArrayList<Carta>();
    public List<Player> players = new ArrayList<>();
    public Player max;
    public Player min;
    public Player active;
    public int score;
    public int rodada;
    public int vez = 12;
    //troca entre os players
    public boolean isstart = true;
    public boolean isstartIa = false;

    //Populando o state
    public State(Carta mesa, List manilha, List<Player> Players) {
        this.mesa = mesa;
        this.manilha = manilha;
        this.players.addAll(Players);
        for (Player r : Players) {
            if (r.toString().equals("playerMax")) {
                this.max = r;
            } else {
                this.min = r;
            }
        }
    }

    //State
    public State(State state, int rodada) {
        this.max = state.max;
        this.min = state.min;
        this.activecartasMax = state.activecartasMax;
        this.activecartasMin = state.activecartasMin;
        this.mesa = state.mesa;
        this.manilha = state.manilha;
        this.rodada = rodada;
    }
    //pega o score dos players
    public Double getScore() {
        if (max.score < min.score) {
            return Double.valueOf(min.score);
        }
        if (max.score > min.score) {
            return -Double.valueOf(max.score);
        }
        return 0.0;
    }

    //ve quem ganhou
    public int howWins() {
        if (max.score == 2) {
            return -1;
        }
        if (min.score == 2) {
            return 1;
        }
        return 0;
    }
    //compara as cartas e da o ponto 
    public void atualizaScore() {
        max.score = 0;
        min.score = 0;
        for (int i = 0; i < (rodada + 1); i++) {
            if (activecartasMax.get(i).getValor() > activecartasMin.get(i).getValor()) {
                if (i == 0) {
                    isstartIa = true;
                }
                max.score++;
                max.quantidadescore++;
                isstart = true;
            }
            if (activecartasMax.get(i).getValor() < activecartasMin.get(i).getValor()) {
                if (i == 0) {
                    isstartIa = false;
                }
                min.score++;
                min.quantidadescore++;
                isstart = false;
            }
            if (activecartasMax.get(i).getValor() == activecartasMin.get(i).getValor()) {
                if (i >= 1) {
                    if (isstartIa) {
                        max.score = 2;
                    } else {
                        min.score = 2;
                    }
                } else if (i == 0) {
                    max.score++;
                    min.score++;
                }
            }
        }
    }
}
//    public void turno() {
//
//        if (isstart) {
//            this.active = max;
//            this.bestever = thebest(max.cartamao);
//            this.besteverOP = thebest(min.cartamao);
//            isstart = false;
//        } else if (isstart) {
//            this.active = min;
//            this.bestever = thebest(min.cartamao);
//            this.besteverOP = thebest(max.cartamao);
//        }
//        this.active = torna();
////        System.out.println("opa : " + active.toString());
//    }
//public Player torna() {
//        while (vez < 3) {
//            vez++;
//            if (this.bestever.getValor() > this.besteverOP.getValor()) {
//                if (this.active.equals(max)) {
//                    this.max.cartamao.remove(this.bestever);
//                    this.max.score++;
//                    System.out.println("max : " + max.cartamao);
//                } else {
//                    isstart = false;
//                    this.min.cartamao.remove(this.bestever);
//                    this.min.score++;
//                    System.out.println("min : " + min.cartamao);
//                }
//            } else {
//                started();
//            }
//
//        }
//        started();
//        System.out.println(" " + isstart);
////        vez++;
//        return active;
//_____________________________________________________________________________________________________
//_____________________________________________________________________________________________________
//_____________________________________________________________________________________________________
//_____________________________________________________________________________________________________
//            if (thebest(max.cartamao).getValor() > thebest(min.cartamao).getValor()) {
//                max.score = max.score + 1;
//                System.out.println(" a melhor eh : " + thebest(max.cartamao).toString());
//                max.cartamao.remove(thebest(max.cartamao));
//                active = max;
//                this.bestever = thebest(max.cartamao);
//                System.out.println("Max score: " + max.score + " Cartas : " + max.cartamao);
//                return max;
//            } else if (thebest(max.cartamao).getValor() < thebest(min.cartamao).getValor()) {
//                min.score = min.score + 1;
//                System.out.println(" a melhor eh : " + thebest(min.cartamao).toString());
//                min.cartamao.remove(thebest(min.cartamao));
//                this.bestever = thebest(min.cartamao);
//                System.out.println("Min score: " + min.score + " Cartas : " + min.cartamao);
//                active = min;
//                return min;
//            } else if (thebest(max.cartamao).getValor() == thebest(min.cartamao).getValor()) {
//                active.score = active.score + 1;
//                System.out.println(" a melhor eh : " + thebest(active.cartamao).toString());
//                this.bestever = thebest(active.cartamao);
//                active.cartamao.remove(thebest(active.cartamao));
//                System.out.println("Min score: " + active.score + " Cartas : " + active.cartamao);
//                return active;
//            }
//    }
//
//    public Carta thebest(List<Carta> cartas) {
//        int valormaior = 0;
//        Carta best = mesa;
//        for (Carta carta : cartas) {
//            if (carta.getValor() > valormaior) {
//                best = carta;
//                valormaior = carta.getValor();
//            }
//        }
//        return best;
//    }

