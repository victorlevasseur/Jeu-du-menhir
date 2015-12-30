package fr.utt.girardguittard.levasseur.menhir.console;

import java.util.Observable;
import java.util.Observer;

import fr.utt.girardguittard.levasseur.menhir.ActionIllegaleException;
import fr.utt.girardguittard.levasseur.menhir.EtatManche;
import fr.utt.girardguittard.levasseur.menhir.EtatPartie;
import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.Partie;
import fr.utt.girardguittard.levasseur.menhir.cartes.Action;
import fr.utt.girardguittard.levasseur.menhir.joueurs.CarteInvalideException;
import fr.utt.girardguittard.levasseur.menhir.joueurs.ChoixCarteAllies;
import fr.utt.girardguittard.levasseur.menhir.joueurs.ChoixCarteIngredient;
import fr.utt.girardguittard.levasseur.menhir.joueurs.JoueurPhysique;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;
import fr.utt.girardguittard.levasseur.menhir.util.Console;

public class JeuConsole implements Observer {
	
	private static boolean demanderTypePartie() {
		String resultat;
		do {
			System.out.println("Choisissez le type de partie : S pour partie simple ou A pour partie avancée");
			resultat = Console.getInstance().readln().toUpperCase();
		} while(resultat.length() != 1 && resultat.charAt(0) != 'S' && resultat.charAt(0) != 'A');
		
		return (resultat.equals("A"));
	}

	private static int demanderNombreJoueurs() {
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

	public static void main(String[] args) {
		System.out.println("Jeu du Menhir");
		System.out.println("Bienvenu(e) sur la version console du jeu !");
		
		Partie partie = new Partie(demanderNombreJoueurs(), demanderTypePartie());
		JeuConsole jeu = new JeuConsole(partie);
		jeu.lancerPartie();
	}
	
	private Partie partie;
	
	private Manche mancheEnCours;
	
	private boolean carteAlliesJouees;
	
	public JeuConsole(Partie partie) {
		this.partie = partie;
		this.partie.addObserver(this);
		
		this.mancheEnCours = null;
		
		this.carteAlliesJouees = false;
	}
	
	void lancerPartie() {
		try {
			//Lancement de la première manche
			this.partie.demarrerManche();
		} catch (ActionIllegaleException e) { 
			e.printStackTrace();
		}
	}

	public void update(Observable arg0, Object arg1) {
		try {
			if(arg0 == this.partie) {
				//On a reçu une update de la partie
				if(this.partie.getEtat() == EtatPartie.MANCHE_EN_COURS) {
					//Une manche vient d'être lancée, on affiche le début de la manche
					this.afficherDebutManche();
					
					//On connecte le controlleur (this) à la manche qui vient d'être lancée
					this.mancheEnCours = this.partie.getMancheEnCours();
					this.mancheEnCours.addObserver(this);
					
					//On lance la manche
					this.mancheEnCours.distribuerCartesIngredients();
				}
			} else if (arg0 == this.partie.getMancheEnCours()) {
				if(this.mancheEnCours.getEtat() == EtatManche.EN_ATTENTE_CHOIX_CARTE_ALLIES) {
					//Le jeu attend de savoir si le joueur physique veut une carte alliés
					
					//On demande au joueur (si c'est une partie avancée uniquement)
					if(this.partie.isPartieAvancee()) {
						this.demanderSiJoueurVeutCarteAllies();
					}
					
					//On lance la suite du jeu
					this.mancheEnCours.distribuerCartesAllies();
				} else if(this.mancheEnCours.getEtat() == EtatManche.PRET_A_DEMARRER) {
					//Le jeu signale que la manche peut démarrer
					
					//On lance la première saison
					this.mancheEnCours.demarrerSaison();
				} else if(this.mancheEnCours.getEtat() == EtatManche.DEBUT_SAISON) {
					//Le jeu signale que c'est le début d'une saison
					System.out.println("  C'est la saison " + this.mancheEnCours.getSaisonActuelle().name() + " : ");
					
					//On lance le 1er tour
					this.mancheEnCours.demarrerTour();
				} else if(this.mancheEnCours.getEtat() == EtatManche.DEBUT_TOUR_JOUEUR) {
					int numeroJoueur = this.mancheEnCours.getJoueurTour();
					
					if(numeroJoueur == 0) {
						System.out.println("    C'est à votre tour : ");
					} else {
						System.out.println("    Au tour du joueur #" + (numeroJoueur+1) + " : ");
					}
					
					String designationJoueur;
					if(numeroJoueur == 0) {
						designationJoueur = "Vous avez";
					} else {
						designationJoueur = "Le joueur #" + (numeroJoueur+1) + " a";
					}
					
					System.out.println("      " + designationJoueur + " " + 
							this.mancheEnCours.getJoueur(numeroJoueur).getMain().getNombreGraine() + " graine(s) et " + 
							this.mancheEnCours.getJoueur(numeroJoueur).getMain().getNombreMenhir() + " menhir(s).");
					
					if(numeroJoueur == 0) {
						//C'est le joueur physique, on lui demande donc les cartes à jouer
						this.demanderCarteIngredient();
					}
					
					this.mancheEnCours.jouerTourJoueur();
				} else if(this.mancheEnCours.getEtat() == EtatManche.FIN_TOUR_JOUEUR) {
					//C'est la fin du tour d'un joueur
					Console.getInstance().attendreEntree();
					
					if(this.partie.isPartieAvancee() && !this.carteAlliesJouees) {
						this.carteAlliesJouees = true;
						//Si les cartes alliés n'ont pas été encore jouées :
						
						//On demande au joueur 1 (joueur physique) s'il veut jouer sa carte alliés (s'il en a une)
						if(this.mancheEnCours.getJoueur(0).getMain().getCarteAllies() != null) {
							this.demanderCarteAllies();
						}
						
						this.mancheEnCours.jouerCartesAllies();
					} else {
						this.carteAlliesJouees = false;
						
						//Si les cartes alliés ont été jouées, on passe au tour suivant :
						this.mancheEnCours.demarrerTour();
					}
				} else if(this.mancheEnCours.getEtat() == EtatManche.FIN_SAISON) {
					//C'est la fin d'une saison, on lance la suivante
					this.mancheEnCours.demarrerSaison();
				}
			}
		} catch (ActionIllegaleException | CarteInvalideException e) { 
			e.printStackTrace();
		}
	}
	
	private void afficherDebutManche() {
		System.out.println("Manche #" + (this.partie.getNumeroMancheEnCours()+1) + " :");
	}
	
	private void demanderSiJoueurVeutCarteAllies() {
		System.out.println("  Souhaitez vous prendre une carte allies ('C') ou 2 graines ('G')?");
		String actionStr = Console.getInstance().readln().toUpperCase();
		while(!actionStr.equals("C") && !actionStr.equals("G")) {
			System.out.println("Ce n'est pas une action valide.");
			System.out.println("    Souhaitez vous prendre une carte allies ('C') ou 2 graines ('G')?");
			actionStr = Console.getInstance().readln().toUpperCase();
		}
		
		boolean prendreCarteAllies = actionStr.equals("C");
		
		JoueurPhysique joueur = (JoueurPhysique)this.mancheEnCours.getJoueur(0);
		joueur.setVeutPrendreCarteAllies(prendreCarteAllies);
	}
	
	private void demanderCarteIngredient() {
		JoueurPhysique joueur = (JoueurPhysique)this.mancheEnCours.getJoueur(0);
		MainJoueur mainJoueur = joueur.getMain();
		
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
			System.out.println("      Veuillez saisir le joueur cible [1-" + this.partie.getNombreJoueurs() + "] : ");
			do {
				String joueurCibleStr = Console.getInstance().readln();
				try {
					joueurCible = Integer.parseInt(joueurCibleStr);
					
					if(joueurCible < 1 || joueurCible > this.partie.getNombreJoueurs()) {
						System.out.println("Ce n'est pas numéro de joueur valide !");
						joueurCible = -1;
					} else if(joueurCible == 1) {
						System.out.println("Vous ne pouvez pas vous voler des graines !");
						joueurCible = -1;
					}
				}
				catch(NumberFormatException e) {
					System.out.println("Ceci n'est pas un nombre !");
					joueurCible = -1;
				}
			} while(joueurCible == -1);
		}
		
		joueur.setProchainChoixIngredient(new ChoixCarteIngredient(mainJoueur.getCarteIngredient(numeroCarte-1), joueurCible-1, actionCarte));
	}
	
	private void demanderCarteAllies() {
		JoueurPhysique joueur = (JoueurPhysique)this.mancheEnCours.getJoueur(0);
		MainJoueur mainJoueur = joueur.getMain();
		
		//Récupération de la volonté de l'utilisateur à utiliser sa carte
		System.out.println("      Voulez vous jouez votre carte " + mainJoueur.getCarteAllies().getNom() + " ? [O/N]");
		System.out.println(mainJoueur.getCarteAllies());
		String actionStr = Console.getInstance().readln().toUpperCase();;
		while(!actionStr.equals("O") && !actionStr.equals("N") && !actionStr.equals("OUI") && !actionStr.equals("NON")) {
			System.out.println("Ceci n'est pas un choix valide !");
			actionStr = Console.getInstance().readln().toUpperCase();
		}
		
		if(actionStr.equals("O") || actionStr.equals("OUI")) {
			
			//Choix de la cible si le joueur dispose d'une carte Taupes géantes
			int joueurCible = -1;
			if (mainJoueur.getCarteAllies().getNom().equals("Taupes géantes")) {
				System.out.println("      Veuillez saisir le joueur cible [1-" + this.partie.getNombreJoueurs() + "] : ");
				do {
					String joueurCibleStr = Console.getInstance().readln();
					try {
						joueurCible = Integer.parseInt(joueurCibleStr);
						
						if(joueurCible < 1 || joueurCible > this.partie.getNombreJoueurs()) {
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
			joueur.setProchainChoixAllies(new ChoixCarteAllies(true, joueurCible-1));
		}
		else {
			joueur.setProchainChoixAllies(new ChoixCarteAllies(false, -1));
		}
	}
}
