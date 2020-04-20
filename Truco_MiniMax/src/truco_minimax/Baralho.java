package truco_minimax;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baralho {

    public List<Carta> cartas;
    public List<Carta> hand;
    public List<Carta> handmax;
    public List<Carta> handmin;
    public List<Player> players;
    public List<Carta> manilha;
    public Carta vira;
    private Player max;
    private Player min;

    public List<Carta> geraBaralho() {
        cartas = new ArrayList<>();
        for (Carta.Rank rank : Carta.Rank.values()) {
            for (Carta.Naipe naipe : Carta.Naipe.values()) {
                //Carta a = new Carta(rank,naipe);
                //rank.next();
//                System.out.println(rank.name() + " " + naipe.name());
                cartas.add(new Carta(rank, naipe, 0));
            }
        }


        return cartas;
    }

    //gerando os dois players
    public void gerarPlayers() {
        this.max = new Player(Player.PlayerType.playerMax) {
        };
        handmax = distribuiCartas();
        max.setHand(handmax);
        
        this.min = new Player(Player.PlayerType.playerMin) {
        };
        handmin = distribuiCartas();
        min.setHand(handmin);
        players = new ArrayList<>();
        players.add(max);
        players.add(min);
    }

    //dando cartas por player
    public List<Carta> distribuiCartas() {
        hand = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            hand.add(cartas.remove(i));
        }
        return hand;
    }

    //vira 
    public Carta sortearvira() {
        vira = cartas.remove(0);
        System.out.println("a vira eh " + vira.toString());
        return vira;
    }

    //atribuindo pesos para as manilhas 
    public void atribuirManilhas() {
        manilha = new ArrayList<>();
        int peso = 1;
        for (Carta.Naipe naipe : Carta.Naipe.values()) {
            Carta a = new Carta(vira.rank.next(), naipe, 150 + peso++);
            manilha.add(a);
        }
        peso = 1;
        for (Carta other : cartas) {
            for (Carta otherm : manilha) {
                if (other.rank == otherm.rank && other.naipe == otherm.naipe) {
                    other.setValor(150 + peso++);
                }
            }
        }
        System.out.println("manilhas : " + manilha);

    }

    //embaralha duas vezes
    public void embaralhar() {
        Collections.shuffle(cartas);
        Collections.shuffle(cartas);
    }

    //pesos das cartas nao precisava pq eu estava usando enum
    public void atribuirPesos() {
        int peso = 0;
        for (Carta.Rank rank : Carta.Rank.values()) {
            peso = peso + 10;
            for (Carta carta : cartas) {
                if (carta.rank == rank) {
                    carta.setValor(peso);
                }
            }
        }

    }

}
