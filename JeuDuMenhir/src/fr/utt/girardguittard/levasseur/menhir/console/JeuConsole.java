package fr.utt.girardguittard.levasseur.menhir.console;

import java.util.Observable;
import java.util.Observer;

import fr.utt.girardguittard.levasseur.menhir.ActionIllegaleException;
import fr.utt.girardguittard.levasseur.menhir.EtatManche;
import fr.utt.girardguittard.levasseur.menhir.EtatPartie;
import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.Partie;
import fr.utt.girardguittard.levasseur.menhir.joueurs.JoueurPhysique;
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
	
	public JeuConsole(Partie partie) {
		this.partie = partie;
		this.partie.addObserver(this);
		
		this.mancheEnCours = null;
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
				}
			}
		} catch (ActionIllegaleException e) { 
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
}
