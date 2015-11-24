package fr.utt.girardguittard.levasseur.menhir.ui;

public class InterfaceManager {
	
	static InterfaceUtilisateur interfaceUtilisateur;
	
	static void setInterfaceUtilisateur(InterfaceUtilisateur interfaceUtil) {
		interfaceUtilisateur = interfaceUtil;
	}
	
	static InterfaceUtilisateur get() {
		if(interfaceUtilisateur == null) {
			//TODO: Exception !
		}
		
		return interfaceUtilisateur;
	}
}
