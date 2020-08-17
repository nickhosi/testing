package physique;

/**
 * La classe position permet de garde une position de labyrinthe en mémoire.
 * La classe est également utilisé pour encode une position en pixel à l'écran.
 * <p>
 * La position est définit dans un plan cartésien et utilise la convention I, J
 * <p>
 * services offerts:
 * - contructeur par paramètre
 * - contructeur par copie
 * - accesseur/mutateur
 * - additionner, soustraire, multiplier
 *
 * @author Fred Simard | ETS
 * @version ETE 2018 - TP2
 */

public class Position {

    // Coordonnée de l'axe vertical
    private int i;

    // Coordonnée de l'axe horizontal
    private int j;

    /**
     * Constructeur par défaut
     */
    public Position() {
    }

    /**
     * Constructeur par paramètre
     *
     * @param i coordonnée de l'axe vertical
     * @param j coordonnée de l'axe horizontale
     */
    public Position(int i, int j) {
        this.i = i;
        this.j = j;
    }

    /**
     * Constructeur par copie d'objet
     *
     * @param position une référence sur un objet Position
     */
    public Position(Position position) {
        this.i = position.i;
        this.j = position.j;
    }

    /**
     * Procédure qui sert à modifier la coordonnée de l'axe vertical
     *
     * @param i la nouvelle coordonnée de l'axe vertical
     */
    public void setI(int i) {
        this.i = i;
    }

    /**
     * Procédure qui sert à modifier la coordonnée de l'axe horizontal
     *
     * @param j la nouvelle coordonnée de l'axe horizontal
     */
    public void setJ(int j) {
        this.j = j;
    }

    /**
     * Fonction qui sert à obtenir la coordonnée de l'axe vertical
     *
     * @return i la coordonée de l'axe vertical
     */
    public int getI() {
        return i;
    }

    /**
     * Fonction qui sert à obtenir la coordonnée de l'axe horizontal
     *
     * @return j la coordonée de l'axe horizontal
     */
    public int getJ() {
        return j;
    }

    /**
     * Procédure qui additionne aux coordonnées actuelles
     * d'autres coordonnées reçues par le paramètre d'une
     * référence à un objet Position
     *
     * @param pos une référence à un objet Position
     */
    public void additionnerPos(Position pos) {
        i += pos.i;
        j += pos.j;
    }

    /**
     * Procédure qui soustrait aux coordonnées actuelles
     * d'autres coordonnées reçues par le paramètre d'une
     * référence à un objet Position
     *
     * @param pos une référence à un objet Position
     */
    public void soustrairePos(Position pos) {
        i -= pos.i;
        j -= pos.j;
    }


    /**
     * Procédure qui multiplie aux coordonnées actuelles
     * d'autres coordonnées i et j reçues en paramètre
     *
     * @param multiI coordonnée de l'axe vertical
     * @param multiJ coordonnée de l'axe horizontal
     */
    public void multiplierPos(double multiI, double multiJ) {
        i *= multiI;
        j *= multiJ;
    }

    /**
     * Fonction qui retourne si les coordonnées i et j de
     * l'objet Position actuel sont les mêmes que l'objet
     * Position qui sera placé en paramètre
     *
     * @param pos une référence à un objet Position
     * @return true    vrai si les coordonnées sont les mêmes
     * false   false si les coordonnées diffèrent
     */
    public boolean equals(Position pos) {
        if (i == pos.i && j == pos.j) {
            return true;
        }
        return false;
    }
}
