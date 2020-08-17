package interfaceUtilisateur;

/**
 * Cette classe est un listener pour les événements claviers utilisé pour contrôler
 * les éléments du jeu.
 * <p>
 * Ces méthodes hérités ne sont pas utilisées:
 * public void keyReleased(KeyEvent e) {}
 * public void keyTyped(KeyEvent e) {}
 *
 * @author Fred Simard | ETS
 * @version ETE 2018 - TP2
 */


import java.awt.event.*;
import java.util.Observable;

import joueur.Joueur;
import modele.GestionnaireCombat;
import modele.PlanDeJeu;
import physique.Direction;

public class ControleurClavier implements KeyListener {

    // référence au plan de jeu
    private PlanDeJeu planDeJeu;
    private GestionnaireCombat gestCombat;

    /**
     * Méthode pour attacher le plan de jeu
     * @param planDeJeu, référence au plan de jeu
     */
    public void attacherPlanDeJeu(PlanDeJeu planDeJeu) {
        this.planDeJeu = PlanDeJeu.getInstance();
        this.gestCombat = this.planDeJeu.getGestionnaireCombat();
    }

    /**
     * gestionnaire d'événement associé au clavier
     * @param e(KeyEvent), evénement clavier
     */
    @Override
    public void keyPressed(KeyEvent e) {

        // obtient une référence au joueur courant
        Joueur joueurControlle = planDeJeu.getJoueur();

        // s'assure qu'un joueur a été initialisé
        if (joueurControlle != null && !this.gestCombat.combatEstEnCours()) {

            int keyCode = e.getKeyCode();

            // gestion de l'action en fonction de l'événement clavier
            switch (KeyEvent.getKeyText(keyCode)) {

                case "Up":
                    joueurControlle.seDeplacer(Direction.HAUT);
                    break;
                case "Down":
                    joueurControlle.seDeplacer(Direction.BAS);
                    break;
                case "Left":
                    joueurControlle.seDeplacer(Direction.GAUCHE);
                    break;
                case "Right":
                    joueurControlle.seDeplacer(Direction.DROITE);
                    break;

            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
