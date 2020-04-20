package truco_minimax;

import java.util.Scanner;

public class MiniMax {

    State state;
    private Scanner scanner;

    public MiniMax(State state) {
        this.state = state;
    }

    public State MiniMax(State state, int rodada, boolean isMaximizing) {
        State bestState = state;
        if (rodada > 2) {
            return bestState;
        }
        if (state.howWins() != 0) {
            return bestState;
        }
        if (isMaximizing) {
            Double bestScore = Double.NEGATIVE_INFINITY;
            for (Carta carta : state.min.cartamao) {
                state.activecartasMin.add(carta);
                state.atualizaScore();
                State temp = MiniMax(state, rodada + 1, false);
                Double score = temp.getScore();
                state.activecartasMin.remove(carta);
                if (score > bestScore) {
                    bestScore = score;
                    bestState = temp;
                }
            }
            return bestState;
        } else {
            Double bestScore = Double.POSITIVE_INFINITY;
            for (Carta carta : state.max.cartamao) {
                state.activecartasMax.add(carta);
                state.atualizaScore();
                State temp = MiniMax(state, rodada + 1, true);
                Double score = temp.getScore();
                state.activecartasMax.remove(carta);
                if (score < bestScore) {
                    bestState = temp;
                    bestScore = score;
                }
            }
            return bestState;
        }
    }

    //espande os states e pega a melhor carta
    public Carta expandstate(int round, boolean startPlayer) {
        Double bestScore = Double.NEGATIVE_INFINITY;
        Carta best = null;
        State stateTemp = null;
        if (!startPlayer) {
            for (Carta carta : state.max.cartamao) {
                state.activecartasMax.add(carta);
                Carta bestTemp = expandstate(round, !startPlayer);
                stateTemp = MiniMax(state, round, !startPlayer);
                if (stateTemp.getScore() > bestScore) {
                    bestScore = stateTemp.getScore();
                    best = bestTemp;
                }
                state.activecartasMax.remove(carta);
            }
            state.max.cartamao.remove(best);
            return best;
        } else {
            for (Carta carta : state.min.cartamao) {
                state.activecartasMin.add(carta);
                stateTemp = MiniMax(state, round, !startPlayer);
                if (stateTemp.getScore() >= bestScore) {
                    bestScore = stateTemp.getScore();
                    if (best == null) {
                        best = carta;
                    } else if (carta.getValor() < best.getValor()) {
                        best = carta;
                    }
                }
                state.activecartasMin.remove(carta);
            }
            state.min.cartamao.remove(best);
            return best;
        }
    }

    //jogando
//    public void truco() {
//        try {
//            scanner = new Scanner(System.in);
//            boolean howstart = true;
//            while (state.howWins() == 0) {
//                System.out.println();
//                System.out.println("Score: " + state.max.score);
//                if (state.isstart) {
//                    clearConsole();
//                    System.out.println();
//                    System.out.println("Escolha sua carta para jogar, vc tem : " + state.max.cartamao.size() + " cartas");
//                    System.out.println("" + state.max.cartamao);
//                    int carta = scanner.nextInt() - 1;
//                    state.activecartasMax.add(state.max.cartamao.get(carta));
//                    System.out.println();
//                    System.out.println("A sua carta : " + state.max.cartamao.remove(carta).toString());
//                    Carta cartapc = expandstate(state.rodada, howstart);
//                    state.activecartasMin.add(cartapc);
//                    System.out.println("A carta do PC : " + cartapc.toString());
//                } else if (!state.isstart) {
//                    clearConsole();
//                    Carta cartapc = expandstate(state.rodada, howstart);
//                    state.activecartasMin.add(cartapc);
//                    System.out.println("A carta do PC :" + cartapc.toString());
//                    System.out.println();
//                    System.out.println("Escolha sua carta para jogar, vc tem : " + state.max.cartamao.size() + " cartas");
//                    System.out.println("" + state.max.cartamao);
//                    int cartap1 = scanner.nextInt() - 1;
//                    state.activecartasMax.add(state.max.cartamao.get(cartap1));
//                    System.out.println();
//                    System.out.println("A carta que voc� escolheu foi: " + state.max.cartamao.remove(cartap1).toString());
//                }
//                state.atualizaScore();
//                state.rodada++;
//            }
//            if (state.howWins() == 1) {
//                System.out.println("O vencedor foi o Computador!");
//            } else {
//                System.out.println("Voce ganhou !!!");
//            }
//        } catch (Exception e) {
//            System.out.println("O erro eh " + e);
//        }
//    }
//
//    public final static void clearConsole() {
//        try {
//            final String os = System.getProperty("os.name");
//            if (os.contains("Windows 10")) {
//                Runtime.getRuntime().exec("cls");
//            } else {
//                //linus e mac
//                Runtime.getRuntime().exec("clear");
//            }
//        } catch (final Exception e) {
//        }
//    }
}
