package truco_minimax;

public class Carta {

    public enum Rank {
        quatro,
        cinco,
        seis,
        sete,
        dama,
        valete,
        rei,
        as,
        dois,
        tres;
        private static Rank[] vals = values();

        public Rank next() {
            return vals[(this.ordinal() + 1) % vals.length];
        }
    }
    

    public enum Naipe {
        ouro,
        espada,
        copas,
        paus
    }
    Rank rank;
    Naipe naipe;
    int valor;

    public Carta(Rank rank, Naipe naipe, int valor) {
        this.rank = rank;
        this.naipe = naipe;
        this.valor = valor;
    }
    
    //comparador de cartas 
    public int Comparar(Carta other, Carta manilha) {
        Rank manilharank = manilha.rank.next();
        if (rank == manilharank && other.rank != manilharank) {
            return 1;
        }
        if (rank != manilharank && other.rank == manilharank) {
            return -1;
        }
        if (rank == manilharank && other.rank == manilharank) {
            return naipe.compareTo(other.naipe);
        }
        return rank.compareTo(other.rank);
    }

    //colocar um valor na carta
    public void setValor(int valor) {
        this.valor = valor;
    }
    //pegar o valor da carta
    public int getValor() {
        return valor;
    }

    //para printar a carta
    @Override
    public String toString() {
        return rank.name() + " " + naipe.name();
    }
}
