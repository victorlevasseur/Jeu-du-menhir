package fr.utt.girardguittard.levasseur.menhir.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;
import junit.framework.Assert;

public class MainJoueurTest {
	
	@Test
	public void testConstructeur() {
		MainJoueur mj = new MainJoueur(null, null);
		assertEquals(mj.getNombreGraine(), 0);
	}

	@Test
	public void testAjouterGraines() {
		MainJoueur mj = new MainJoueur(null, null);
		mj.ajouterGraines(3);
		
		assertEquals(mj.getNombreGraine(), 3);
	}

	@Test
	public void testVolerGraines() {
		MainJoueur mj = new MainJoueur(null, null);
		mj.ajouterGraines(3);
		
		assertEquals(mj.volerGraines(1), 1);
		assertEquals(mj.getNombreGraine(), 2);
		
		assertEquals(mj.volerGraines(4), 2);
		assertEquals(mj.getNombreGraine(), 0);
	}

	@Test
	public void testFairePousserMenhir() {
		MainJoueur mj = new MainJoueur(null, null);
		mj.ajouterGraines(3);
		
		assertEquals(mj.fairePousserMenhir(2), 2);
		assertEquals(mj.getNombreMenhir(), 2);
		
		assertEquals(mj.fairePousserMenhir(4), 1);
		assertEquals(mj.getNombreMenhir(), 3);
	}

	@Test
	public void testDetruireMenhir() {
		MainJoueur mj = new MainJoueur(null, null);
		mj.ajouterGraines(3);
		mj.fairePousserMenhir(3);
		
		assertEquals(mj.detruireMenhir(1), 1);
		assertEquals(mj.getNombreMenhir(), 2);
		
		assertEquals(mj.detruireMenhir(4), 2);
		assertEquals(mj.getNombreMenhir(), 0);
	}
	
	@Test
	public void testChienDeGarde() {
		MainJoueur mj = new MainJoueur(null, null);
		mj.ajouterGraines(3);
		
		mj.setDefenseChienDeGarde(3);
		mj.fairePousserMenhir(2);
		assertEquals(mj.getDefenseChienDeGarde(), 1);
		
		mj.setDefenseChienDeGarde(3);
		assertEquals(mj.getDefenseChienDeGarde(), 1);
		
		mj.ajouterGraines(3);
		mj.setDefenseChienDeGarde(2);
		assertEquals(mj.volerGraines(1), 0);
		assertEquals(mj.getDefenseChienDeGarde(), 1);
		assertEquals(mj.volerGraines(3), 2);
		assertEquals(mj.getDefenseChienDeGarde(), 0);
	}

}
