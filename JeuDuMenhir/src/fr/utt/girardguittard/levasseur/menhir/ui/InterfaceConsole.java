package fr.utt.girardguittard.levasseur.menhir.ui;

import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.Partie;
import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.cartes.Action;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteAllies;
import fr.utt.girardguittard.levasseur.menhir.cartes.ChiensDeGarde;
import fr.utt.girardguittard.levasseur.menhir.cartes.TaupesGeantes;
import fr.utt.girardguittard.levasseur.menhir.joueurs.ChoixCarteAllies;
import fr.utt.girardguittard.levasseur.menhir.joueurs.ChoixCarteIngredient;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;
import fr.utt.girardguittard.levasseur.menhir.util.Console;

/**
 * Classe permettant de faire communiquer la console avec le moteur de jeu du menhir.
 * Note : les méthodes ne sont pas réexpliquer, se référer à la documentation de InterfaceUtilisateur
 * pour avoir plus de détails sur elles.
 */
public class InterfaceConsole implements InterfaceUtilisateur {
	
	private Partie partieEnCours;
	private Manche mancheEnCours;
	private Saison saisonEnCours;
	private int joueurEnCours;
	
	public InterfaceConsole() {
		this.partieEnCours = null;
		this.mancheEnCours = null;
		this.saisonEnCours = Saison.AUTOMNE;
		this.joueurEnCours = 0;
	}

	public void notifierDebutJeu() {
		System.out.println("Jeu du Menhir");
		System.out.println("Bienvenu(e) !");
	}

	public boolean demanderTypePartie() {
		String resultat;
		do {
			System.out.println("Choisissez le type de partie : S pour partie simple ou A pour partie avancée");
			resultat = Console.getInstance().readln().toUpperCase();
		} while(resultat.length() != 1 && resultat.charAt(0) != 'S' && resultat.charAt(0) != 'A');
		
		return (resultat.equals("A"));
	}

	public int demanderNombreJoueurs() {
		System.out.println("Saisissez le nombre de joueurs (entre 2 et 6) : ");
		String nombreJoueursStr = Console.getInstance().readln();
		
		try {
			int nombreJoueurs = Integer.parseInt(nombreJoueursStr);
			return nombreJoueurs;
		} 
		catch(NumberFormatException e) {
			System.out.println("Ceci n'est pas un nombre valide !");
			return demanderNombreJoueurs();
		}
	}

	public void notifierValidationParametres(boolean nombreJoueursOk) {
		if(!nombreJoueursOk) {
			System.out.println("Le nombre de joueur est invalide : entre 2 et 6 joueurs seulement");
		}
	}

	public void notifierDebutPartie(Partie partie) {
		this.partieEnCours = partie;
		System.out.println("Début de la partie " + (partie.isPartieAvancee() ? "avancée" : "simple") + " avec " + partie.getNombreJoueurs() + " joueurs.");
	}

	public void notifierDebutManche(int numeroManche, Manche manche) {
		this.mancheEnCours = manche;
		System.out.println("Manche #" + (numeroManche+1) + " :");
		System.out.println("  4 cartes ingrédients ont été distribuées à chaque joueurs.");
		System.out.println("  Le joueur #" + (manche.getPremierJoueur()+1) + " commence à jouer en premier.");
	}

	public void notifierDebutSaison(Saison saison) {
		System.out.println("  C'est la saison " + saison.name() + " : ");
		this.saisonEnCours = saison;
	}

	public void notifierDebutTour(int numeroJoueur) {
		if(numeroJoueur == 0) {
			System.out.println("    C'est à votre tour : ");
		} else {
			System.out.println("    Au tour du joueur #" + (numeroJoueur+1) + " : ");
		}
		
		String designationJoueur;
		if(numeroJoueur == 0) {
			designationJoueur = "Vous avez";
		} else {
			designationJoueur = "Le joueur a";
		}
		
		System.out.println("      " + designationJoueur + " " + 
				this.mancheEnCours.getJoueur(numeroJoueur).getMain().getNombreGraine() + " graine(s) et " + 
				this.mancheEnCours.getJoueur(numeroJoueur).getMain().getNombreMenhir() + " menhir(s).");
		
		this.joueurEnCours = numeroJoueur;
	}

	public ChoixCarteIngredient demanderCarteIngredientAJouer(MainJoueur mainJoueur) {
		System.out.println("      Vous pouvez jouer les cartes suivantes : ");
		
		//Affichage des cartes
		for(int i = 0; i < mainJoueur.getNombreCarteIngredient(); i++) {
			System.out.println("CARTE " + (i+1) + " =>");
			System.out.println(mainJoueur.getCarteIngredient(i));
		}
		
		//Choix du numéro de la carte
		int numeroCarte = -1;
		while(numeroCarte == -1) {
			System.out.println("      Saisissez la carte à jouer [1-" + mainJoueur.getNombreCarteIngredient() + "] : ");
			try {
				numeroCarte = Integer.parseInt(Console.getInstance().readln());
				if(numeroCarte < 1 || numeroCarte > mainJoueur.getNombreCarteIngredient()) {
					numeroCarte = -1;
					System.out.println("La carte n'existe pas !");
				}
			} 
			catch(NumberFormatException e) {
				System.out.println("Ceci n'est pas un nombre valide !");
			}
		}
		
		//Choix de l'action
		System.out.println("      Choisissez l'action de la carte [GEANT, ENGRAIS, FARFADET] : ");
		String actionStr = Console.getInstance().readln().toUpperCase();;
		while(!actionStr.equals("GEANT") && !actionStr.equals("ENGRAIS") && !actionStr.equals("FARFADET")) {
			System.out.println("Ceci n'est pas une action valide !");
			actionStr = Console.getInstance().readln().toUpperCase();
		}
		
		Action actionCarte;
		if(actionStr.equals("GEANT")) {
			actionCarte = Action.GEANT;
		} else if(actionStr.equals("ENGRAIS")) {
			actionCarte = Action.ENGRAIS;
		} else {
			actionCarte = Action.FARFADET;
		}
		
		//Choix de la cible si l'action FARFADET est demandée
		int joueurCible = -1;
		if(actionCarte == Action.FARFADET) {
			System.out.println("      Veuillez saisir le joueur cible [1-" + partieEnCours.getNombreJoueurs() + "] : ");
			do {
				String joueurCibleStr = Console.getInstance().readln();
				try {
					joueurCible = Integer.parseInt(joueurCibleStr);
					
					if(joueurCible < 1 || joueurCible > partieEnCours.getNombreJoueurs()) {
						System.out.println("Ce n'est pas numéro de joueur valide !");
						joueurCible = -1;
					}
				}
				catch(NumberFormatException e) {
					System.out.println("Ceci n'est pas un nombre !");
					joueurCible = -1;
				}
			} while(joueurCible == -1);
		}
		
		return new ChoixCarteIngredient(mainJoueur.getCarteIngredient(numeroCarte-1), joueurCible-1, actionCarte);
	}
	
	public ChoixCarteAllies demanderCarteAllies(MainJoueur mainJoueur) {
		
		//Récupération de la volonté de l'utilisateur à utiliser sa carte
		System.out.println("      Voulez vous jouez votre carte " + mainJoueur.getCarteAllies().getNom() + "[O/N]");
		String actionStr = Console.getInstance().readln().toUpperCase();;
		while(!actionStr.equals("O") && !actionStr.equals("N") && !actionStr.equals("OUI") && !actionStr.equals("NON")) {
			System.out.println("Ceci n'est pas un choix valide !");
			actionStr = Console.getInstance().readln().toUpperCase();
		}
		
		if(actionStr.equals("O") || actionStr.equals("OUI")) {
			
			//Choix de la cible si le joueur dispose d'une carte Taupes géantes
			int joueurCible = -1;
			if (mainJoueur.getCarteAllies().getNom().equals("Taupes géantes")) {
				System.out.println("      Veuillez saisir le joueur cible [1-" + partieEnCours.getNombreJoueurs() + "] : ");
				do {
					String joueurCibleStr = Console.getInstance().readln();
					try {
						joueurCible = Integer.parseInt(joueurCibleStr);
						
						if(joueurCible < 1 || joueurCible > partieEnCours.getNombreJoueurs()) {
							System.out.println("Ce n'est pas numéro de joueur valide !");
							joueurCible = -1;
						}
					}
					catch(NumberFormatException e) {
						System.out.println("Ceci n'est pas un nombre !");
						joueurCible = -1;
					}
				} while(joueurCible == -1);
			}
			return new ChoixCarteAllies(true, joueurCible-1);
		}
		else {
			return new ChoixCarteAllies(false, -1);
		}
	}
	
	public boolean demanderCarteOuGraines() {
		System.out.println("  Souhaitez vous prendre une carte allies ('C') ou 2 graines ('G')?");
		String actionStr = Console.getInstance().readln().toUpperCase();
		while(!actionStr.equals("C") && !actionStr.equals("G")) {
			System.out.println("Ce n'est pas une action valide.");
			System.out.println("    Souhaitez vous prendre une carte allies ('C') ou 2 graines ('G')?");
			actionStr = Console.getInstance().readln().toUpperCase();
		}
		if(actionStr.equals("C")) {
			return true;
		}
		else {
			return false;
		}
	}

	public void notifierAgissementCarte(ChoixCarteIngredient choixCarteIngr, int forceReelle) {
		
		String designationJoueur1;
		if(this.joueurEnCours == 0) {
			designationJoueur1 = "Vous jouez ";
		} else {
			designationJoueur1 = "Le joueur " + (this.joueurEnCours+1) + " joue ";
		}
		
		System.out.println("        --> " + designationJoueur1 + "la carte \"" + choixCarteIngr.getCarteChoisie().getNom() + "\"");
	
		
		String designationJoueur2;
		if(this.joueurEnCours == 0) {
			designationJoueur2 = "Vous avez ";
		} else {
			designationJoueur2 = "Le joueur " + (this.joueurEnCours+1) + " a ";
		}
		if(choixCarteIngr.getActionChoisie() == Action.GEANT) {
			System.out.println("            " + designationJoueur2 + "récupéré " + forceReelle + " graine(s).");
		} else if(choixCarteIngr.getActionChoisie() == Action.ENGRAIS) {
			System.out.println("            " + designationJoueur2 + "fait pousser " + forceReelle + " graine(s) en menhir(s).");
		} else {
			System.out.println("            " + designationJoueur2 + "volé " + forceReelle + " graine(s) au joueur " + (choixCarteIngr.getCible()+1) + ".");
		}
	}

	public void notifierAgissementCarte(ChoixCarteAllies choixCarteAllies, CarteAllies carteJouee, int forceReelle) {
		String designationJoueur1;
		if(this.joueurEnCours == 0) {
			designationJoueur1 = "Vous jouez ";
		} else {
			designationJoueur1 = "Le joueur " + (this.joueurEnCours+1) + " joue ";
		}
		
		System.out.println("        --> " + designationJoueur1 + "la carte \"" + carteJouee.getNom() + "\"");
		
		String designationJoueur2;
		if(this.joueurEnCours == 0) {
			designationJoueur2 = "Vous avez ";
		} else {
			designationJoueur2 = "Le joueur " + (this.joueurEnCours+1) + " a ";
		}
		if(carteJouee instanceof ChiensDeGarde) {
			System.out.println("            " + designationJoueur2 + "protégé " + forceReelle + " graine(s).");
		} else if(carteJouee instanceof TaupesGeantes) {
			System.out.println("            " + designationJoueur2 + "détruit " + forceReelle + " menhir(s) du joueur " + (choixCarteAllies.getCible()+1) + ".");
		}
	}

}
