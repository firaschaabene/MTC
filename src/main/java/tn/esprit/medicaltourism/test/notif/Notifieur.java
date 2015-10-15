package tn.esprit.medicaltourism.test.notif;

import javax.swing.JWindow;


/**
 * <b>Notifieur est la classe qui permet de g�n�rer les notification</b>
 * <p>
 * Cette classe est caract�ris�e par les informations suivantes :
 * <ul>
 * <li>Le titre du message</li>
 * <li>Le texte du message</li>
 * <li>Le type de notification</li>
 * </ul>
 * </p>
 * <p>
 * Cette classe h�rite de Thread
 * </p>
 * 
 * @see fr.julien.notification.TypeNotification
 * @see fr.julien.notification.FenetreNotification
 * 
 * @author Julien
 * @version 1.0
 *
 */
public class Notifieur extends Thread {
	
	/**
	 * La fen�tre de notification
	 */
	private JWindow fenetre;
	

	/**
	 * <b>Constructeur de notifieur</b>
	 * @param titre
	 * 	Le titre de la fen�tre
	 * @param texte
	 * 	Le texte � afficher
	 * @param type
	 * 	Le type de notification
	 * 	@see fr.julien.notification.TypeNotification
	 */
	public Notifieur(String titre, String message, TypeNotification type) {
		fenetre = new FenetreNotification(titre, message, type);
	}

	@Override
	public void run(){
		fenetre.setVisible(true);
		try {
			Thread.sleep(9000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		fenetre.setVisible(false);
		fenetre.dispose();
	}

}
