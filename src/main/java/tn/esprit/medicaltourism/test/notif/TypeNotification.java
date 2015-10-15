package tn.esprit.medicaltourism.test.notif;

import java.awt.Color;

public class TypeNotification {
	
	
	public static TypeNotification ERREUR = new TypeNotification(Color.black, Color.WHITE);
	
	public static TypeNotification INFO = new TypeNotification(new Color(94, 151, 203), Color.WHITE);
	
	public static TypeNotification VALIDATION = new TypeNotification(Color.GREEN, Color.GREEN);
	
	
	private Color couleur1;
	
	private Color couleur2;

	
	public TypeNotification(Color couleur1, Color couleur2) {
		this.couleur1 = couleur1;
		this.couleur2 = couleur2;
	}
	
	/**
	 * @return the couleur1
	 */
	public Color getCouleur1() {
		return couleur1;
	}
	
	/**
	 * @return the couleur2
	 */
	public Color getCouleur2() {
		return couleur2;
	}
	

}
