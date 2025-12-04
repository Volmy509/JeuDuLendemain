package fr.esic.jeu;

import javax.swing.JOptionPane;
import java.util.Random;

public class JeuDuLendemain {
	// INTIALISATION DU SCORE A 0
	private static int score = 0;
	//"Random": PERMET DE GENER UN NOMBRE ALEATOIREMENT 
	private static Random random = new Random();
	// DEBUT DU PROGRAMME
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "Bienvenue dans le Jeu du Lendemain!");
		//BOUCLE PERMETTANT DE GARDER LE PROGRAMME "OUVERT" TANT QUE LE BOOLEAN EST "VRAI"
		boolean continuerProgramme = true;
		while (continuerProgramme) {
			// MENU PRINCIPALE
			String[] optionsMenu = { "Jeu du Calcul Mental", "Devine le nombre", "Quitter" };
			int choixJeu = JOptionPane.showOptionDialog(null, "Choisissez un jeu:", "Menu Principal",
					//PERMET DE CHOISIR QUEL "ACTIVITE" FLANCER
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionsMenu, optionsMenu[0]);
			switch (choixJeu) {
			case 0: //APPEL DE LA METHODE JEU DU CALCUL MENTAL
				menuCalculMental();
				break;
			case 1: //APPEL DE LA METHODE DU JEU DU "DEVINE LE NOMBRE"
				menuDevineNombre();
				break;
			case 2: // QUITTER
			case JOptionPane.CLOSED_OPTION:
				//LE BOOLEAN DEVIENT "FALSE" ALORS LE PROGRAMME CE FERME
				continuerProgramme = false;
				break;
			}
		}

		JOptionPane.showMessageDialog(null, "Merci d'avoir joué!\nScore final: " + score);
	}

	private static void menuCalculMental() {
		score = 0; // INITIALISE LE SCORE A 0

		// CHOIX DU NIVEAU 
		String[] optionsNiveau = { "Niveau 1 (Addition/Soustraction)", "Niveau 2 (Multiplication/Division)", "Retour" };
		int choixNiveau = JOptionPane.showOptionDialog(null, "Choisissez votre niveau:", "Jeu de Calcul Mental",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionsNiveau, optionsNiveau[0]);
		//NIVEAU 1 OU 2 OU REVENIR AU MENU PRECEDENT
		if (choixNiveau == 2 || choixNiveau == JOptionPane.CLOSED_OPTION) {
			return; // RETOUR AU MENU PRINCIPAL
		}

		boolean continuerNiveau = true;
		while (continuerNiveau) {
			if (choixNiveau == 0) {
				jouerNiv1();
			} else if (choixNiveau == 1) {
				jouerNiv2();
			}

			int reponse = JOptionPane.showConfirmDialog(null,
					"Score actuel: " + score + "\n\nVoulez-vous continuer ce niveau?", "Continuer?",
					JOptionPane.YES_NO_OPTION);
			continuerNiveau = (reponse == JOptionPane.YES_OPTION);
		}

		JOptionPane.showMessageDialog(null, "Partie terminée!\nScore: " + score);
	}

	private static void jouerNiv1() {
		int num1 = random.nextInt(100) + 1;
		int num2 = random.nextInt(100) + 1;
		int operation = random.nextInt(2);

		int resultat = 0;
		String signe = "";

		switch (operation) {
		case 0:
			resultat = num1 + num2;
			signe = "+";
			break;
		case 1:
			resultat = num1 - num2;
			signe = "-";
			break;
		}

		String question = "Niveau 1 - Quel est le résultat de:\n" + num1 + " " + signe + " " + num2 + " ?";
		String reponseUtilisateur = JOptionPane.showInputDialog(question);

		if (reponseUtilisateur == null)
			return; // Annulé

		try {
			int reponse = Integer.parseInt(reponseUtilisateur);
			if (reponse == resultat) {
				JOptionPane.showMessageDialog(null, "Correct! +1 point");
				score++;
			} else {
				JOptionPane.showMessageDialog(null, "Incorrect! La réponse était: " + resultat);
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Entrée invalide!");
		}
	}

	private static void jouerNiv2() {
		int num1 = random.nextInt(10) + 1;
		int num2 = random.nextInt(10) + 1;
		int operation = random.nextInt(2);

		int resultat = 0;
		String signe = "";

		switch (operation) {
		case 0:
			resultat = num1 * num2;
			signe = "*";
			break;
		case 1:
			// Pour la division, on s'assure d'avoir un résultat entier
			num1 = num1 * num2;
			resultat = num1 / num2;
			signe = "/";
			break;
		}

		String question = "Niveau 2 - Quel est le résultat de:\n" + num1 + " " + signe + " " + num2 + " ?";
		String reponseUtilisateur = JOptionPane.showInputDialog(question);

		if (reponseUtilisateur == null)
			return;

		try {
			int reponse = Integer.parseInt(reponseUtilisateur);
			if (reponse == resultat) {
				JOptionPane.showMessageDialog(null, "Correct! +1 point");
				score++;
			} else {
				JOptionPane.showMessageDialog(null, "Incorrect! La réponse était: " + resultat);
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Entrée invalide!");
		}
	}

	private static void menuDevineNombre() {
		score = 0; // itialise le score a 0

		// Menu de sélection du niveau
		String[] optionsNiveau = { "Niveau 1 (entre 0 et 20)", "Niveau 2 (0 et 100) + (3 Essai)", "Retour" };
		int choixNiveau = JOptionPane.showOptionDialog(null, "Choisissez votre niveau:", "Devine le nombre",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionsNiveau, optionsNiveau[0]);

		if (choixNiveau == 2 || choixNiveau == JOptionPane.CLOSED_OPTION) {
			return; // Retour au menu principal
		}

		boolean continuerNiveau = true;
		while (continuerNiveau) {
			if (choixNiveau == 0) {
				devineLeNombre();
			} else if (choixNiveau == 1) {
				devineLeNombreNiv2();
			}

			int reponse = JOptionPane.showConfirmDialog(null,
					"Score actuel: " + score + "\n\nVoulez-vous continuer ce niveau?", "Continuer?",
					JOptionPane.YES_NO_OPTION);
			continuerNiveau = (reponse == JOptionPane.YES_OPTION);
		}

		JOptionPane.showMessageDialog(null, "Partie terminée!\nScore: " + score);
	}

	private static void devineLeNombre() {
		// JOptionPane.showMessageDialog(null, "Bienvenue sur le jeu" + " Devine le
		// nombre niveau facile" + " !!");

		int nombre = random.nextInt(20);
		System.out.println(nombre);
		int resultat = nombre;
		String question = "Devine le nombre au quel je pense (compris entre 0 et 20)";

		String reponseUtilisateur = JOptionPane.showInputDialog(question);
		if (reponseUtilisateur == null)
			return;

		try {
			int reponse = Integer.parseInt(reponseUtilisateur);
			if (reponse == resultat) {
				JOptionPane.showMessageDialog(null, "Correct! +1 point");
				score++;
				return;
			} else {
				JOptionPane.showMessageDialog(null, "Incorrect! La réponse était: " + resultat);
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Entrée invalide!");
		}
	}

	private static void devineLeNombreNiv2() {
		// JOptionPane.showMessageDialog(null, "Bienvenue sur le jeu" + " Devine le
		// nombre niveau 2" + " !!");

		int nombre = random.nextInt(100);
		System.out.println(nombre);
		int resultat = nombre;
		int tentatives = 0;
		int tentativeMax = 3;

		while (tentatives < tentativeMax) {

			String question = "Devine le nombre au quel je pense (compris entre 0 et 100)\ntentatives "
					+ (tentatives + 1) + "/ " + tentativeMax;

			String reponseUtilisateur = JOptionPane.showInputDialog(question);
			if (reponseUtilisateur == null)
				return;

			try {
				int reponse = Integer.parseInt(reponseUtilisateur);
				tentatives++;

				if (reponse == resultat) {
					JOptionPane.showMessageDialog(null,
							"Correct! +1 point" + "\nTrouvé en: " + tentatives + " tentative(s)!");
					score++;
					return;// SORTIR CAR REPONSE TROUVE
				} else if (reponse < resultat) {
					JOptionPane.showMessageDialog(null,
							"Faux C'est +" + "\nIl vous reste " + (tentativeMax - tentatives) + " essai(s)");
				} else {
					JOptionPane.showMessageDialog(null,
							"Faux C'est -" + "\nIl vous reste " + (tentativeMax - tentatives) + " essai(s)");
				}
				if (tentatives == tentativeMax) {
					JOptionPane.showMessageDialog(null,
							"Vous avez perdue le résultat attendu était " + resultat + " :/");
					return;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Entrée invalide!");
			}
		}
	}
}