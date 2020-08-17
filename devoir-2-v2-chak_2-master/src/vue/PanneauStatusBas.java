package vue;

import modele.PlanDeJeu;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * Panneau status bas
 * <p>
 * définit le panneau status bas du panneau
 * status. Ce panneau contient une console qui
 * affiche des messages sur les actions que le joueur
 * prend durant son aventure
 *
 * @author Ging Sze Chan
 * @author Frédérique Kirkey
 * @author Nick Hosi
 * @author Georgio Abdel-Nour
 * @version 16 août 2020
 */
public class PanneauStatusBas extends JPanel {

    private static JTextArea texte;
    private PlanDeJeu planDeJeu;
    private static Vector<String> message = new Vector<>();

    /**
     * Constructeur par défaut qui produit le
     * sous-panneau status du bas
     */
    public PanneauStatusBas() {

        // obtenir la référence au plan de jeu
        planDeJeu = PlanDeJeu.getInstance();

        // ajouter une bordure noire autour du panneau
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // configurer la zone de texte
        configurationZoneTexte();
    }

    /**
     * Méthode qui configure la boite message du panneau du bas
     */
    public void configurationZoneTexte() {

        // création d'une zone de texte
        texte = new JTextArea(18, 54);

        // rendre la zone de texte non-éditable
        texte.setEditable(false);

        // initialiser la barre de défilement et lui attacher la zone de texte
        JScrollPane scroll = new JScrollPane(texte);

        // détermine quand la barre vertical apparaît dans le panneau de la barre
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        // ajout de la barre au panneau
        this.add(scroll);
    }

    /**
     * Fonction qui retourne la référence au vecteur des messages
     *
     * @return message référence au vecteur des messages
     */
    public static Vector<String> getMessage() {
        return message;
    }

    /**
     * Procédure qui ajoute un message au vecteur de message
     *
     * @param msg le message à ajouter
     */
    public static void ajouteConsole(String msg) {
        message.add(msg);
    }

    /**
     * Procédure qui affiche des messages
     * au joueur dans la zone de texte selon
     * ses actions dans le jeu
     */
    public void mettreJoursInfo() {

        texte.setText("");

        for (String messageJoueur : message) {
            texte.append(messageJoueur + "\n");
        }
    }
}

