package dongon;


/**
 * Définition d'un objet du labyrinthe.
 * L'objet peut-être:
 * un personnage, une pièce d'équipement, etc
 *
 * @author Fred Simard | ETS
 * @version ETE 2018 - TP3
 */

import observer.MonObservable;
import physique.Position;

public abstract class AbstractObjet extends MonObservable {

    // propriétés d'un personnage
    protected Position pos;
    protected Case caseCourante;

    /**
     * Procédure qui modifie la case courante
     *
     * @param caseCourante la case courante
     */
    public void setCase(Case caseCourante) {
        this.caseCourante = caseCourante;
    }

    /**
     * Fonction qui retroune une référence à la case
     *  courante
     *
     * @return this.caseCourante la référence à la case courante
     */
    public Case getCase() {
        return this.caseCourante;
    }

    /**
     * Fonction qui retourne une référence à la
     *  copie de la position
     *
     * @return new Position(pos) référence à une copie de la position
     */
    public Position getPos() {
        return new Position(pos);
    }

    /**
     * Procédure qui modifie la position
     *
     * @param pos la position
     */
    public void setPos(Position pos) {
        this.pos = pos;
    }


}
