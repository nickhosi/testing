package programme;

/**
 * Construire les dernières composantes d'un
 * engin de contrôle d'un jeu vidéo
 * <p>
 * Thématique:
 * un héro décide de s’enfoncer dans un donjon réputé
 * sans fin, dans le but d’ajouter un acte héroïque
 * à sa liste d’exploits. Dans son périple, il fera
 * face à de nombreux ennemis et profitera des équipements
 * abandonnés par ceux qui l’ont précédé.
 *
 * @author Ging Sze Chan
 * @author Frédérique Kirkey
 * @author Nick Hosi
 * @author Georgio Abdel-Nour
 * @version 16 août 2020
 */


public class programmePrincipal {

    /**
     * Programme principal, lance la vue du programme
     *
     * @param args, inutilisé
     */
    public static void main(String[] args) {

        Thread t = new Thread(new vue.CadrePrincipal());
        t.start();

    }

}

