package fr.utt.girardguittard.levasseur.menhir.joueurs;

import java.util.ArrayList;
import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteAllies;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteIngredient;

public class MainJoueur {

	/**
	 * Le joueur auquel appartient la main.
	 */
	private final Joueur joueur;
	
	/**
	 * La manche à laquelle la main est associée.
	 */
	private final Manche manche;
	
	/**
	 * Le nombre de graines du joueur dans la manche.
	 */
	private int nombreGraine;

	/**
	 * Le nombre de menhir du joueur dans la manche.
	 */
	private int nombreMenhir;
	
	/**
	 * Le nombre de graines que les chiens de garde défendent.
	 */
	private int defenseChienDeGarde;
	
	/**
	 * Les cartes ingrédients dans la main du joueur (pour la manche actuelle).
	 */
	private ArrayList<CarteIngredient> cartesIngredient;
	
	/**
	 * La carte alliés (ou null si aucune) du joueur (partie avancée uniquement)
	 */
	private CarteAllies carteAllies;
	
	/**
	 * Constructeur.
	 * @param joueur le joueur qui possède la main
	 * @param manche la manche jouée par le joueur avec cette main
	 */
	public MainJoueur(Joueur joueur, Manche manche) {
		//Initialisation de l'association
		this.joueur = joueur;
		this.manche = manche;
		
		//Initialisation des attributs
		this.nombreGraine = 0;
		this.nombreMenhir = 0;
		this.defenseChienDeGarde = 0;
		this.cartesIngredient = new ArrayList<CarteIngredient>();
		this.carteAllies = null;
	}

	/**
	 * @return le joueur possédant la main.
	 */
	public Joueur getJoueur() {
		return joueur;
	}

	/**
	 * @return la manche jouée par le joueur possédant la main.
	 */
	public Manche getManche() {
		return manche;
	}
	
	/**
	 * Ajoute des graines.
	 * @param graines le nombre de graines à ajouter
	 */
	public void ajouterGraines(int graines) {
		this.nombreGraine += graines;
	}
	
	/**
	 * Vole le nombre de graine (si cela est possible)
	 * @param graines le nombre de graine qui doivent être volées
	 * @return le nombre de graines réellement volée
	 * @note si la main possède moins de graines que ce qui est demandé, uniquement les graines possédées seront volées
	 * (la valeur de retour permet d'obtenir le nombre réellement volé).
	 */
	public int volerGraines(int graines) {
		int grainesVolees = Math.min(this.nombreGraine, graines);
		while(this.defenseChienDeGarde > 0 && grainesVolees > 0) {
			this.defenseChienDeGarde--;
			grainesVolees--;
		}
		this.nombreGraine -= grainesVolees;
		
		return grainesVolees;
	}

	/**
	 * @return le nombre de graines de la main
	 */
	public int getNombreGraine() {
		return nombreGraine;
	}
	
	/**
	 * Fait pousser des graines en menhirs.
	 * @param graines le nombre de graines à faire pousser
	 * @return le nombre de menhir réellement obtenus
	 * @note Si le nombre de graines à faire pousser est plus grand que le stock de graine alors seulement les graines
	 * disponible pousseront.
	 */
	public int fairePousserMenhir(int graines) {
		int menhirsPousses = Math.min(this.nombreGraine, graines);
		this.nombreGraine -= menhirsPousses;
		this.defenseChienDeGarde = Math.max(0, this.defenseChienDeGarde - menhirsPousses); //On réduit la défense du nombre
		this.nombreMenhir += menhirsPousses;
		
		return menhirsPousses;
	}
	
	/**
	 * Détruit des menhirs.
	 * @param menhirs le nombre de menhir à détruire
	 * @return le nombre de menhirs réellement détruits
	 */
	public int detruireMenhir(int menhirs) {
		int menhirsDetruits = Math.min(this.nombreMenhir, menhirs);
		this.nombreMenhir -= menhirsDetruits;
		
		return menhirsDetruits;
	}

	/**
	 * @return le nombre de menhir du joueur dans la manche
	 */
	public int getNombreMenhir() {
		return nombreMenhir;
	}
	
	public int getDefenseChienDeGarde() {
		return defenseChienDeGarde;
	}

	public void setDefenseChienDeGarde(int defenseChienDeGarde) {
		this.defenseChienDeGarde = Math.min(defenseChienDeGarde, this.nombreGraine);
	}

	/**
	 * Ajoute une carte ingrédient à la main du joueur
	 * @param carteIngredient la carte ingrédient à ajouter
	 */
	public void ajouterCarteIngredient(CarteIngredient carteIngredient) {
		if(this.cartesIngredient.size() <= 3) {
			this.cartesIngredient.add(carteIngredient);
		}
	}
	
	/**
	 * Retire une carte de la main du joueur (après l'avoir jouée en général)
	 * @param carte l'index de la carte à retirer
	 */
	public void retirerCarteIngredient(int carte) {
		if(carte < this.cartesIngredient.size()) {
			this.cartesIngredient.remove(carte);
		}
	}
	
	/**
	 * Retire une carte de la main du joueur (après l'avoir jouée en général)
	 * @param carte la carte à retirer
	 * Si la carte n'est pas dans la main, rien n'est fait.
	 */
	public void retirerCarteIngredient(CarteIngredient carte) {
		this.cartesIngredient.remove(carte);
	}
	
	/**
	 * Retourne le nombre de cartes appartenant à la main du joueur.
	 * @return le nombre de cartes
	 */
	public int getNombreCarteIngredient() {
		return this.cartesIngredient.size();
	}
	
	/**
	 * Retourne la carte ingrédient demandée.
	 * @param carte l'index de la carte
	 * @return la carte ingrédient
	 */
	public CarteIngredient getCarteIngredient(int carte) {
		if(carte < this.cartesIngredient.size()) {
			return this.cartesIngredient.get(carte);
		} else {
			return null;
		}
	}

	/**
	 * Récupère la carte alliés du joueur (s'il en a une)
	 * @return la carte alliés du joueur, null sinon
	 */
	public CarteAllies getCarteAllies() {
		return carteAllies;
	}

	/**
	 * Définit la carte alliés du joueur.
	 * @param carteAllies la carte alliés à donner au joueur
	 */
	public void setCarteAllies(CarteAllies carteAllies) {
		this.carteAllies = carteAllies;
	}
	
	/**
	 * Retire la carte alliés du joueur (après utilisation par exemple).
	 */
	public void retirerCarteAllies() {
		this.setCarteAllies(null);
	}
}
