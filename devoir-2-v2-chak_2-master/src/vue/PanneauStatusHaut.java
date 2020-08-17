package vue;


import modele.PlanDeJeu;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Panneau status haut
 * <p>
 * définit le panneau status haut du panneau
 * status. Ce panneau montre le nom du héro,
 * sa barre de vie, le niveau, le nombre
 * d'ennemis tués et le temps de jeu
 *
 * @author Ging Sze Chan
 * @author Frédérique Kirkey
 * @author Nick Hosi
 * @author Georgio Abdel-Nour
 * @version 16 août 2020
 */
public class PanneauStatusHaut extends JPanel {

    // constante indiquand le nom du héro
    private static final String NOM_HERO = "Leeroy Jenkins";

    // barre de vie du héro
    private JProgressBar barreVieHero = new JProgressBar(0, 100);

    private PlanDeJeu planDeJeu;

    private JLabel numeroNiveau;

    private JLabel nbEnnemisTues;

    private JLabel tempsDeJeu;


    /**
     * Constructeur par défaut qui
     * produit le sous-panneau status
     * du haut
     */
    public PanneauStatusHaut() {

        // obtenir la référence au plan de jeu
        planDeJeu = PlanDeJeu.getInstance();

        // faire le panneau sous forme de GridLayout
        this.setLayout(new GridLayout(5, 1));

        // ajouter une bordure noire autour du panneau
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // appel aux méthode qui mettront les composantes au panneau status haut
        configurationNomHero();
        configurationBarreVieHero();
        configurationNiveauCourant();
        configurationNbEnnemisTues();
        configurationTempsDeJeu();


    }

    /**
     * Procédure qui montre le nom
     * du héro sur le panneau status haut
     */
    public void configurationNomHero() {

        // JLabel affichant le nom du héro
        JLabel nomHero = new JLabel(NOM_HERO);

        // font du nom du héro de taille 24 en gras et italic
        Font fontHero = new Font(NOM_HERO, Font.BOLD + Font.ITALIC, 24);

        // mettre le font du nom du héro au nom du héro
        nomHero.setFont(fontHero);

        // centrer le nom du héro
        nomHero.setHorizontalAlignment(SwingConstants.CENTER);

        // ajouter le nom du héro au sous-panneau du haut
        this.add(nomHero);

    }

    /**
     * Procédure qui montre la barre de vie du héro
     * sur le panneau status haut
     */
    public void configurationBarreVieHero() {

        // mettre la couleur d'avant en vert de la vie du héro
        barreVieHero.setForeground(Color.GREEN);

        // mettre la couleur de fond en rouge de la vie du héro
        barreVieHero.setBackground(Color.RED);

        // mettre toute la barre de vie de couleur verte au début
        barreVieHero.setValue(100);

        // ajouter la barre de vie du héro au sous-panneau du haut
        this.add(barreVieHero);

    }

    /**
     * Procédure qui indique le niveau courant
     * sur le panneau status haut
     */
    public void configurationNiveauCourant() {

        // JLabel indiquant le numéro du niveau
        numeroNiveau = new JLabel("Niveau: " + planDeJeu.getNiveau());

        // centrer le numéro du niveau
        numeroNiveau.setHorizontalAlignment(SwingConstants.CENTER);

        // ajouter le numéro du niveau au sous-panneau du haut
        this.add(numeroNiveau);

    }

    /**
     * Procédure qui indique le nombre d'ennemis tués par
     * le héro sur le panneau status haut
     */
    public void configurationNbEnnemisTues() {

        // Jlabel indiquant le nombre d'ennemis tués
        nbEnnemisTues = new JLabel("Nb Ennemis Tues: ");

        // centrer l'affichage du nombre d'ennemis tués
        nbEnnemisTues.setHorizontalAlignment(SwingConstants.CENTER);

        // ajouter le nombre d'ennemis tués au sous-panneau du haut
        this.add(nbEnnemisTues);
    }

    /**
     * Procédure qui configure le temps écoulé depuis le début
     * de la partie
     */
    public void configurationTempsDeJeu() {

        // JLabel indiquant le temps de jeu écoulé
        tempsDeJeu = new JLabel("Temps de jeu: " + " secondes");

        // center l'affichage du temps de jeu écoulé
        tempsDeJeu.setHorizontalAlignment(SwingConstants.CENTER);

        // ajouter le temps de jeu au sous-panneau du haut
        this.add(tempsDeJeu);
    }

    /**
     * Procédure qui met à jour la barre de vie du héro
     * le niveau auquel le joueur est rendu, le temps de jeu
     * et le nombre de créatures tuées par le héro
     */
    public void mettreJoursInfo() {

        // format du temps
        String format = "mm:ss";

        // variable contenant le format du temps
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        
        /* temps courant depuis 1970 en millisecondes - celui depuis 1970 jusqu'au moment de cliquer sur run
           converti selon le format minutes:secondes */
        String date = simpleDateFormat.format(new Date(System.currentTimeMillis() - planDeJeu.getJoueur().getStartTime()));

        // obtenir le nombre d'ennemis tués et l'afficher
        nbEnnemisTues.setText("Nb Ennemis Tues: " + planDeJeu.getJoueur().getNbEnnemisTues());

        // obtenir le temps de jeu et l'afficher
        tempsDeJeu.setText("Temps de jeu: " + date);

        // changer la valeur de la barre de vie du héro
        barreVieHero.setValue(planDeJeu.getJoueur().getPointDeVie() * 100 / planDeJeu.getJoueur().getPointDeVieMax());

        // obtenir le niveau courant et l'afficher
        numeroNiveau.setText("Niveau: " + planDeJeu.getNiveau());
    }
}
