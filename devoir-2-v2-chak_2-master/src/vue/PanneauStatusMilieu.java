package vue;


import equipements.*;
import modele.PlanDeJeu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Panneau status milieu
 * <p>
 * définit le panneau status mileu du panneau
 * status. Ce panneau montre l'image du héro à
 * gauche et son inventaire à droite, ainsi que
 * défense et son attaque
 *
 * @author Ging Sze Chan
 * @author Frédérique Kirkey
 * @author Nick Hosi
 * @author Georgio Abdel-Nour
 * @version 16 août 2020
 */
public class PanneauStatusMilieu extends JPanel {

    //Les sous-panneaux du sous-panneau status milieu
    private JPanel panneauGauche;
    private JPanel panneauDroite;

    private JLabel attaqueTotale;
    private JLabel defenseTotale;

    private PlanDeJeu planDeJeu;

    private JLabel casque;
    private JComboBox<Casque> lesCasques;

    private JLabel armure;
    private JComboBox<Armure> lesArmures;

    private JLabel arme;
    private JComboBox<Arme> lesArmes;

    private JLabel potion;
    private JButton boutonPotion;


    /**
     * Constructeur par défaut qui produit
     * le sous-panneau status milieu
     */
    public PanneauStatusMilieu() {

        // référence au plan de jeu
        planDeJeu = PlanDeJeu.getInstance();

        // faire le panneau sous forme de GridLayout
        this.setLayout(new GridLayout(0, 2));

        // ajouter une bordure noire autour du panneau
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // initialisation des sous-panneaux du sous panneau status milieu
        panneauGauche = new JPanel();
        panneauDroite = new JPanel();

        // configuration de chacun des sous-panneaux du sous panneau status milieu
        configurationPanneauGauche();
        configurationPanneauDroite();


    }

    /**
     * Procédure qui configure le sous-panneau gauche dans lequel se trouve
     * l'image du héro
     */
    public void configurationPanneauGauche() {

        BufferedImage imageHero = null;

        try {

            // localiser l'image du héro et la mettre dans la variable d'image
            imageHero = ImageIO.read(new File("images\\hero.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ajouter l'image au sous-panneau gauche
        panneauGauche.add(new JLabel(new ImageIcon(imageHero)));

        // ajouter le sous-panneau gauche au sous-panneau status milieu
        this.add(panneauGauche);
    }

    /**
     * Procédure qui configure le sous-panneau droite dans lequel se trouve
     * l'inventaire du héro, ce qu'il peut utiliser comme équipements,
     * les statistiques sur sa défense et son attaque
     */
    public void configurationPanneauDroite() {


        // faire le panneau sous forme de GridLayout
        panneauDroite.setLayout(new GridLayout(10, 1));

        // éléments de défense
        // JLabel montrant la défense totale
        defenseTotale = new JLabel("Defense totale = ");

        // JLabel indiquant Casque
        casque = new JLabel("Casque:");

        // JComboBox pour choisir quel casque équiper
        lesCasques = new JComboBox();

        // JComboBox des casques ne garde pas le focus
        lesCasques.setFocusable(false);

        lesCasques.addItemListener(new ItemListener() {
            @Override
            /**
             * Surdéfinition de la méthode itemStateChanged.
             * Équiper le joueur à l'item qui est sélectionné
             * dans le JComboBox des casques
             */
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    Object item = event.getItem();
                    planDeJeu.getJoueur().equiper((AbstractEquipement) item);
                }
            }
        });

        // JLabel indiquant Armure
        armure = new JLabel("Armure:");

        // JComboBox pour choisir quelle armure équiper
        lesArmures = new JComboBox();

        // JComboBox des armures ne garde pas le focus
        lesArmures.setFocusable(false);

        lesArmures.addItemListener(new ItemListener() {
            @Override
            /**
             * Surdéfinition de la méthode itemStateChanged.
             * Équiper le joueur à l'item qui est sélectionné
             * dans le JComboBox des armures
             */
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    Object item = event.getItem();
                    planDeJeu.getJoueur().equiper((AbstractEquipement) item);
                }
            }
        });

        // éléments d'attaque
        // JLabel montrant l’attaque totale
        attaqueTotale = new JLabel("Attaque totale = ");

        // JLabel indiquant Arme
        arme = new JLabel("Arme:");

        // JComboBox pour choisir quelle arme équiper
        lesArmes = new JComboBox();

        // JComboBox des armes ne garde pas le focus
        lesArmes.setFocusable(false);

        lesArmes.addItemListener(new ItemListener() {
            @Override
            /**
             * Surdéfinition de la méthode itemStateChanged.
             * Équiper le joueur à l'item qui est sélectionné
             * dans le JComboBox des armes
             */
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    Object item = event.getItem();
                    planDeJeu.getJoueur().equiper((AbstractEquipement) item);
                }
            }
        });

        // JLabel indiquant le nombre de poitions
        potion = new JLabel("Nb Potions = " + 0);

        // bouton pour utiliser potion
        boutonPotion = new JButton("Utiliser Potion");

        boutonPotion.addActionListener(new ActionListener() {
            @Override
            /**
             * Surdéfinition de la méthode actionPerformed.
             * Donner les points de vie au joueur lorsqu'il
             * clique sur le bouton Utiliser Potion
             */
            public void actionPerformed(ActionEvent e) {
                planDeJeu.getJoueur().utiliserPotion();
            }
        });

        // bouton initiallement inactif
        boutonPotion.setEnabled(false);


        /* ajouter toutes les étiquettes, JComboBox et bouton
           créés précedemment au sous-panneau droite*/
        panneauDroite.add(defenseTotale);

        panneauDroite.add(casque);
        panneauDroite.add(lesCasques);

        panneauDroite.add(armure);
        panneauDroite.add(lesArmures);

        panneauDroite.add(attaqueTotale);

        panneauDroite.add(arme);
        panneauDroite.add(lesArmes);

        panneauDroite.add(potion);
        panneauDroite.add(boutonPotion);

        // ajouter le sous-panneau droite au sous-panneau status milieu
        this.add(panneauDroite);
    }

    /**
     * Procédure qui met à jour l'inventaire du héro,
     * ce qu'il peut utiliser comme équipements,
     * les statistiques sur sa défense et son attaque
     */
    public void mettreJoursInfo() {

        // obtenir et afficher l'attaque et la défense totale du héro
        attaqueTotale.setText("Attaque totale = " + planDeJeu.getJoueur().getForce());
        defenseTotale.setText("Defense totale = " + planDeJeu.getJoueur().getArmure());

        // enlever tous les items des JComboBox
        lesArmes.removeAllItems();
        lesArmures.removeAllItems();
        lesCasques.removeAllItems();

        int compteurPotions = 0;

        if (planDeJeu.getJoueur().getArmeEquipe() != null) {

            // ajouter l'item au JComboBox s'il y a une référence à l'arme équipée
            lesArmes.addItem(planDeJeu.getJoueur().getArmeEquipe());
        }

        if (planDeJeu.getJoueur().getArmureEquipe() != null) {

            // ajouter l'item au JComboBox s'il y a une référence à l'armure équipée
            lesArmures.addItem(planDeJeu.getJoueur().getArmureEquipe());
        }

        if (planDeJeu.getJoueur().getCasqueEquipe() != null) {

            // ajouter l'item au JComboBox  s'il y a une référence au casque équipé
            lesCasques.addItem(planDeJeu.getJoueur().getCasqueEquipe());
        }


        // référence à la liste d'équipements
        Vector<AbstractEquipement> equipements = planDeJeu.getJoueur().getEquipement();

       /* pour chaque élément de la liste d'équipements,
          vérifier le type de l'équipement et l'ajouter
          à son JComboBox respectif. Si c'est une potion,
          augmenter le compteur*/
        for (AbstractEquipement equipement : equipements) {
            if (equipement instanceof Arme) {
                lesArmes.addItem((Arme) equipement);
            } else if (equipement instanceof Armure) {
                lesArmures.addItem((Armure) equipement);
            } else if (equipement instanceof Casque) {
                lesCasques.addItem((Casque) equipement);
            } else if (equipement instanceof Potion) {
                compteurPotions++;
            }

            // obtenir le nombre de potions et l'afficher
            potion.setText("Nb Potions = " + compteurPotions);

            /* laisser l'utilisateur cliquer sur le bouton
               s'il y a au minimum 1 potion dans l'inventaire*/
            boutonPotion.setEnabled(compteurPotions > 0);

        }

    }
}


