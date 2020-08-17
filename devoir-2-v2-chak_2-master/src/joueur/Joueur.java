package joueur;

/**
 * Définition du Joueur.
 *
 * @author Fred Simard | ETS
 * @version ETE 2018 - TP2
 */


import java.util.Vector;

/**
 * Cette classe représente le joueur humain. Elle surcharge le
 * personnage abstrait pour le déplacement et propose une méthode
 * pour mettre à jours la visibilité des cases en fonction de la vision.
 *
 * @author Fred Simard | ETS
 * @version ETE 2018 - TP2
 */


import dongon.Case;
import equipements.AbstractEquipement;
import equipements.Arme;
import equipements.Armure;
import equipements.Casque;
import equipements.Potion;
import personnage.AbstractPersonnage;
import physique.Direction;
import vue.PanneauStatusBas;


public class Joueur extends AbstractPersonnage {

    private final int PROFONDEUR_VISION = 2;

    // le nombre de créatures que le héro a tué
    private int nbEnnemisTues;

    // le temps depuis 1970 jusqu'au moment du clic sur run
    private long startTime = System.currentTimeMillis();

    // collection qui gardera tous les équipements ramassés par le joueur
    private Vector<AbstractEquipement> equipement = new Vector<>();

    // les références aux équipements équipés du hér0
    private Casque casqueEquipe;
    private Arme armeEquipe;
    private Armure armureEquipe;


    /**
     * Construceur par paramètre
     * @param pos, position du joueur
     */
    public Joueur() {
        pointDeVie = 100;
        pointDeVieMax = 100;
    }

    /**
     * surcharge de la méthode pour déplacer le joueur dans la direction donnée
     * @param direction(int), direction du mouvement
     */
    public void seDeplacer(int direction) {

        // se déplacer
        super.seDeplacer(direction);

        // mise à jour de la vision
        mettreAJourVision();
    }


    /**
     * surcharge de la méthode pour placer le joueur à sa case de départ
     * @param caseCourante(Case), case courante
     */
    public void setCase(Case caseCourante) {

        // assigne la case
        super.setCase(caseCourante);

        // mise à jour de la vision
        mettreAJourVision();
    }

    /**
     * méthode qui mets à jour la vision
     */
    private void mettreAJourVision() {

        // rend visible la case courante
        super.caseCourante.setDecouverte(true);

        // dans toutes les directions
        for (int i = 0; i < Direction.NB_DIRECTIONS; i++) {

            // dévoile les voisins jusqu'à la profondeur de la vision
            Case voisin = super.caseCourante.getVoisin(i);
            for (int j = 0; j < PROFONDEUR_VISION; j++) {
                if (voisin != null) {
                    voisin.setDecouverte(true);
                    voisin = voisin.getVoisin(i);
                }
            }
        }
    }


    /**
     * Remise à zéro du joueur
     * - remet les points de vie à max
     * - vide équipement
     */
    public void remiseAZero() {

        // mettre la vie du héro à max en début de partie
        this.pointDeVie = this.pointDeVieMax;

		/* vider la collection d'équipements ramassés du joueur
		   si une nouvelle partie est lancée */
        equipement.clear();

        // vider la boîte de message du joueur si remise à zéro
        PanneauStatusBas.getMessage().clear();

        // initialiser le temps à 0
        startTime = System.currentTimeMillis();

        // affecter le nombre de créatures tuées par le héro à 0
        nbEnnemisTues = 0;

		/* mettre des références null aux équipements, car aucun
		   équipement est dans l'inventaire du joueur en début de partie */
        casqueEquipe = null;
        armureEquipe = null;
        armeEquipe = null;

        // regénérer les calculs
        equiper(null);
    }

    /**
     * Procédure qui indique que la pièce d'équipement n'est
     * plus au sol, car elle a été ramassée et ajoutée à
     * la collection d'équipement du joueur
     *
     * @param unEquipement la référence de la pièce d'équipement à ramasser
     */
    public void rammasserPiece(AbstractEquipement unEquipement) {

        // L'équipement n'est plus au sol, car il a été ramassé
        unEquipement.setAuSol(false);

		/* Ajout de la référence de la pièce d'équipement à la collection
		   des équipements ramassés par le joueur */
        equipement.add(unEquipement);
    }

    /**
     * Procédure qui augmente le nombre d'ennemis tués
     */
    public void itererNbEnnemisTues() {
        nbEnnemisTues++;
    }

    /**
     * Fonction qui retourne le nombre d'ennemis tués
     *
     * @return nbEnnemisTues le nombre d'ennemis tués
     */
    public int getNbEnnemisTues() {
        return nbEnnemisTues;
    }

    /**
     * Fonction qui retourne le temps de départ
     *
     * @return startTime le temps
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * Fonction qui retourne une référence sur la
     * collection d'équipement
     *
     * @return equipement la référence sur la collection d'équipement
     */
    public Vector<AbstractEquipement> getEquipement() {

        return equipement;
    }

    /**
     *	Fonction qui retourne une référence au casque équipé.
     *  La référence est null si aucun casque n'est équipé.
     *
     * @return casqueEquipe la référence au casque équipé
     */
    public Casque getCasqueEquipe() {

        return casqueEquipe;
    }

    /**
     *	Fonction qui retourne une référence à l'arme équipée.
     *  La référence est null si aucune arme n'est équipée.
     *
     * @return armeEquipe la référence à l'arme équipée
     */
    public Arme getArmeEquipe() {

        return armeEquipe;
    }

    /**
     *	Fonction qui retourne une référence à l'armure équipée.
     *  La référence est null si aucune armure n'est équipée.
     *
     * @return armureEquipe la référence à l'armure équipée
     */
    public Armure getArmureEquipe() {

        return armureEquipe;
    }

    /**
     * Procédure qui permet d'équiper une pièce d'équipement reçu
     * en paramètre
     *
     * @param unEquipement la référence à la pièce d'équipement
     */
    public void equiper(AbstractEquipement unEquipement) {

        // remettre la variable membre armure à 0
        armure = 0;

        if (unEquipement instanceof Casque) {

            // changer l'équipement en question en casque si c'est un casque
            casqueEquipe = (Casque) unEquipement;
        } else if (unEquipement instanceof Armure) {

            // changer l'équipement en question en armure si c'est une armure
            armureEquipe = (Armure) unEquipement;
        } else if (unEquipement instanceof Arme) {

            // changer l'équipement en question en arme si c'est une arme
            armeEquipe = (Arme) unEquipement;
        }

        // remettre la variable membre bonusAttaque à 0
        bonusAttaque = 0;


        if (casqueEquipe != null) {

            // ajouter à l'armure la valeur de défense du casque équipé s'il y en a un
            armure += casqueEquipe.getValeur();
        }

        if (armureEquipe != null) {

            // ajouter à l'armure la valeur de défense de l'armure équipée s'il y en a une
            armure += armureEquipe.getValeur();
        }

        if (armeEquipe != null) {

            // ajouter à l'attaque la valeur de l'arme équipée s'il y en a une
            bonusAttaque = armeEquipe.getValeur();
        }

    }

    /**
     * Procédure qui trouve la première potion dans la collection d’équipement,
     * l’enlève et remets les points de vie à point de vie max. Si cette procédure est
     * appelée mais qu’il n’y a aucune potion elle n’a aucun effet
     */
    public void utiliserPotion() {

        boolean trouve = false;

		/* parcourir la collection d'équipement jusqu'à la fin de la collection
		   et tant que la première potion n'a pas été trouvée */
        for (int i = 0; i < equipement.size() && trouve == false; i++) {

            // vérifier si l'équipement est une potion
            if (equipement.get(i) instanceof Potion) {

                // enlever la potion de la collection des équipements ramassés
                equipement.remove(equipement.get(i));

                // redonner le point de vie max au héro
                pointDeVie = pointDeVieMax;

                // la potion a été trouvée
                trouve = true;
            }
        }
    }
}
