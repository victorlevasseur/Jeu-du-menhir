/*
	JeuDuMenhir is a board game adapted into a computer game.
	Copyright (C) 2015-2016  
	Antoine Girard Guittard (antoine.girard_guittard@utt.fr), Victor Levasseur (victorlevasseur52@gmail.com)
	
	This program is free software; you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation; either version 2 of the License, or
	(at your option) any later version.
	
	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.
	
	You should have received a copy of the GNU General Public License along
	with this program; if not, write to the Free Software Foundation, Inc.,
	51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package fr.utt.girardguittard.levasseur.menhir.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;

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
