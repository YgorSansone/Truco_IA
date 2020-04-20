package truco_minimax;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    public int score;
    public int quantidadescore;
    public List<Carta> cartamao;

    public enum PlayerType {
        playerMax,
        playerMin;
    };
    public PlayerType type;

    public Player(PlayerType tipo) {
        this.type = tipo;
        this.score = 0;
        this.quantidadescore = 0;
    }

    //add carta para o player
    public List<Carta> setHand(List<Carta> carta) {
        cartamao = new ArrayList<>();
        cartamao.addAll(carta);
        return cartamao;
    }

    @Override
    public String toString() {
        return type.name();
    }
}
