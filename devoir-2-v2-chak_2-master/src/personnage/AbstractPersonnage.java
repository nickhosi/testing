package personnage;

/**
 * Classe abstraite d'un personnage d'un jeu
 *
 * @author Fred Simard | ETS
 * @version ETE 2018 - TP2
 */

import physique.Direction;
import physique.Position;

import java.util.Observable;

import dongon.AbstractObjet;
import dongon.Case;
import observer.MonObservable;

public abstract class AbstractPersonnage extends AbstractObjet {


    protected int armure = 0;
    protected int force = 10;
    protected int bonusAttaque = 0;
    protected int pointDeVie = 100;
    protected int pointDeVieMax = 100;

    /**
     * constructeur
     * @param pos, position I,J dans le labyrinthe
     */

    public AbstractPersonnage() {
    }

    public AbstractPersonnage(Position pos) {
        this.pos = pos;
    }

    /**
     * méthode pour déplacer un personnage
     * @param direction, direction du mouvement
     */
    public void seDeplacer(int direction) {

        // obtient une référence sur le voisin
        Case voisin = caseCourante.getVoisin(direction);

        // vérifie que le voisin est valide (ne l'est pas quand il y a un mur)
        if (voisin != null) {

            // met à jour la position
            caseCourante = voisin;
            pos.additionnerPos(Direction.directionAPosition(direction));
            this.avertirLesObservers();
        }
    }

    /**
     * Fonction qui retourne l'armure du personnage
     *
     * @return armure l'armure du personnage
     */
    public int getArmure() {
        return armure;
    }

    /**
     * Fonction qui retourne l'attaque du personnage
     *
     * @return force + bonusAttaque l'attaque du personnage
     */
    public int getForce() {
        return force + bonusAttaque;
    }

    /**
     * Fonction qui retourne les points de vie du personnage
     *
     * @return pointDeVie les points de vie du personnage
     */
    public int getPointDeVie() {
        return pointDeVie;
    }

    /**
     * Fonction qui retourne les points de vie max du personnage
     *
     * @return pointDeVieMax les points de vie max du personnage
     */
    public int getPointDeVieMax() {
        return pointDeVieMax;
    }

    /**
     * Procédure qui enlève des points de vie au personnage
     * selon la valeur du coup recu
     *
     * @param forceCoup la valeur du coup recu
     */
    public void recoitCoup(int forceCoup) {
        forceCoup -= armure;

        if (forceCoup > 0) {
            pointDeVie -= forceCoup;
        }
    }

    /**
     * Fonction qui retourne si un personnage est vivant
     *
     * @return true  si les points de vie sont supérieurs à 0
     * 		   false si les points de vie sont égales ou inférieurs à 0
     */
    public boolean estVivant() {
        return (pointDeVie > 0);
    }

}
