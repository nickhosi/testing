package vue;

import modele.PlanDeJeu;
import observer.MonObserver;

import javax.swing.*;
import java.awt.*;

/**
 * Panneau status
 * <p>
 * définit le panneau status qui contient
 * les sous-panneaux status qui contiennent
 * la bande d'information du héro
 *
 * @author Ging Sze Chan
 * @author Frédérique Kirkey
 * @author Nick Hosi
 * @author Georgio Abdel-Nour
 * @version 16 août 2020
 */
public class PanneauStatus extends JPanel implements MonObserver {

    // références aux sous-panneaux de panneau status
    private PanneauStatusHaut haut;
    private PanneauStatusMilieu milieu;
    private PanneauStatusBas bas;

    private PlanDeJeu planDeJeu;

    /**
     * Constructeur par défaut
     * qui produit le panneau status
     */
    public PanneauStatus() {

        // initialisation des sous-panneaux
        haut = new PanneauStatusHaut();
        milieu = new PanneauStatusMilieu();
        bas = new PanneauStatusBas();

        // faire le panneau sous forme de GridLayout
        setLayout(new GridLayout(3, 1));

        // ajout des sous-panneaux au panneau status
        this.add(haut);
        this.add(milieu);
        this.add(bas);

        // obtenir la référence au plan de jeu
        planDeJeu = PlanDeJeu.getInstance();

        // attache le panneau status comme observer
        planDeJeu.attacherObserver(this);


    }


    @Override
    /**
     * Procédure qui surdéfinit la méthode avertir
     * en mettant à jour les sous-panneaux status
     */
    public void avertir() {

        haut.mettreJoursInfo();
        milieu.mettreJoursInfo();
        bas.mettreJoursInfo();
    }
}
