package vue;

/**
 * Panneau principal du jeu
 * <p>
 * contient:
 * - le panneau du donjon
 *
 * @author Fred Simard | ETS
 * @version ETE 2018 - TP2
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.border.Border;

public class PanneauPrincipal extends JPanel {

    Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();

    // Panneaux
    public PanneauDonjon panDonjon;
    private PanneauStatus panStatus;

    /**
     * Constructeur
     * @param taille La taille de l'ecran
     */
    public PanneauPrincipal() {

        // assigne la tâche
        setSize(tailleEcran);
        setPreferredSize(tailleEcran);

        // initialise les composantes
        initComposantes();
    }


    /*
     * Dimensionne et ajoute les differents panneaux e leur place.
     */
    private void initComposantes() {

        // définit le layout
        setLayout(new BorderLayout());

        // définit le panneau de donjon
        panDonjon = new PanneauDonjon(tailleEcran);

        panStatus = new PanneauStatus();

        int largeurPan = this.getSize().width;

        panStatus.setPreferredSize(new Dimension(largeurPan / 3, this.getSize().height));

        add(panDonjon, BorderLayout.CENTER);

        add(panStatus, BorderLayout.LINE_END);

        panDonjon.requestFocus();

    }


}
