package vue;

import creature.AbstractCreature;
import creature.Araigne;
import creature.Dragon;
import creature.Minotaure;
import joueur.Joueur;
import modele.GestionnaireCombat;
import observer.MonObserver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * Fenêtre de combat
 * <p>
 * définit la fenêtre de combat entre le
 * héro et la créature
 *
 * @author Ging Sze Chan
 * @author Frédérique Kirkey
 * @author Nick Hosi
 * @author Georgio Abdel-Nour
 * @version 16 août 2020
 */
public class FenetreCombat extends JFrame implements MonObserver {

    private JFrame frame;

    private Joueur hero;

    private AbstractCreature creature;

    private GestionnaireCombat gestionnaire;

    private JTextArea texte;

    private JScrollPane scroll;

    private JPanel panneauPrincipal;


    /**
     * Contructeur par copie de paramètres de la classe qui configure
     * la fenêtre de combat
     *
     * @param hero         la référence au héro
     * @param creature     la référence à la créature
     * @param gestionnaire la référence au gestionnaire de combat
     */
    public FenetreCombat(Joueur hero, AbstractCreature creature, GestionnaireCombat gestionnaire) {

        this.hero = hero;
        this.creature = creature;
        this.gestionnaire = gestionnaire;

        frame = new JFrame();

        // appel des méthode qui configure la fenêtre de combat
        configurationDuFrame();
        configurationImageHero();
        configurationBoiteMessage();
        configurationImageCreature();

        frame.requestFocus();

        frame.setVisible(true);


    }

    /**
     * Méthode qui configure le frame
     */
    public void configurationDuFrame() {

        // initialisation du JPanel
        panneauPrincipal = new JPanel();

        // ajout du panneauPrincipal au frame
        frame.getContentPane().add(panneauPrincipal);

        // taille de la fenêtre de combat
        frame.setSize(800, 400);

        // position de la fenêtre de combat
        frame.setLocation(600, 300);

        // la fenêtre apparaît au top en terme de couche
        frame.setAlwaysOnTop(true);

        // définir un GridLayout au panneau principal
        panneauPrincipal.setLayout(new GridLayout(0, 3));




        /* WindowListener qui fait un appel à la méthode qui termine le combat
           lors d'un évenement de type windowClosing */
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                gestionnaire.combatTermine();
            }
        });


    }

    /**
     * Procédure qui configure l'image du héro
     */
    public void configurationImageHero() {

        BufferedImage image = null;

        try {

            // localiser l'image du héro et la mettre dans la variable d'image
            image = ImageIO.read(new File("images\\hero.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ajouter l'image au panneau principal
        panneauPrincipal.add(new JLabel(new ImageIcon(image)));

    }

    /**
     * Procédure qui configure la boîte de message du combat
     */
    public void configurationBoiteMessage() {

        // créer la zone de texte de 16 lignes et 20 colonnes
        texte = new JTextArea(16, 20);

        // rendre la zone non-éditable
        texte.setEditable(false);

        // initialiser la barre de défilement et lui attacher la zone de texte
        scroll = new JScrollPane(texte);

        // détermine quand la barre vertical apparaît dans le panneau de la barre
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        // ajout de la barre au panneau
        panneauPrincipal.add(scroll);

    }

    /**
     * Procédure qui configure l'image de la créature
     * que le héro affronte
     */
    public void configurationImageCreature() {

        BufferedImage image = null;

        /* chercher le type de la créature parmi l'araigné, le dragon ou le minotaure.
           Si c'est l'araigné, localiser l'image de l'araigné et la mettre dans la variable
           d'image. Sinon, si c'est le dragon, localiser l'image du dragon et la mettre dans
            la variable d'image. Sinon, si c'est le minotaure, localiser l'image du minotaure
            et la mettre dans la variable d'image. */
        if (creature instanceof Araigne) {

            try {
                image = ImageIO.read(new File("images\\spider.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (creature instanceof Dragon) {
            try {
                image = ImageIO.read(new File("images\\dragon.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (creature instanceof Minotaure) {
            try {
                image = ImageIO.read(new File("images\\minotaur.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // ajouter l'image de la créature au panneau
        panneauPrincipal.add(new JLabel(new ImageIcon(image)));

    }


    @Override
    /**
     * Procédure qui surdéfinit la méthode avertir
     * en obtenant la liste des messages du combat
     * du gestionnaire de combat
     */
    public void avertir() {
        texte.append(gestionnaire.getMsg());
    }
}
