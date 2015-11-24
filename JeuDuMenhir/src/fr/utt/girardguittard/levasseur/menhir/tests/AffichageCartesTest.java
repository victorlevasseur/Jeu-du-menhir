package fr.utt.girardguittard.levasseur.menhir.tests;

import fr.utt.girardguittard.levasseur.menhir.cartes.CartesFabrique;

public class AffichageCartesTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(CartesFabrique.genererCartesIngredients().get((int)(Math.random()*24)));
	}

}
