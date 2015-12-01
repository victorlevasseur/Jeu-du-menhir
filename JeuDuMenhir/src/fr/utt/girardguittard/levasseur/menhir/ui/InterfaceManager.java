package fr.utt.girardguittard.levasseur.menhir.ui;

/**
 * Classe qui gère une instance unique d'une InterfaceUtilisateur qui sera utilisée
 * pendant l'exécution du programme.
 */
public class InterfaceManager {
	
	static private InterfaceUtilisateur interfaceUtilisateur;
	
	static public void setInterfaceUtilisateur(InterfaceUtilisateur interfaceUtil) {
		interfaceUtilisateur = interfaceUtil;
	}
	
	static public InterfaceUtilisateur get() {
		if(interfaceUtilisateur == null) {
			//TODO: Exception !
		}
		
		return interfaceUtilisateur;
	}
}
