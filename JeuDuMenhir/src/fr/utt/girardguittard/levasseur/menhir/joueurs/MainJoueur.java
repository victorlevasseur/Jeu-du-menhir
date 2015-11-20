package fr.utt.girardguittard.levasseur.menhir.joueurs;

import fr.utt.girardguittard.levasseur.menhir.Manche;

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
}
