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

package fr.utt.girardguittard.levasseur.menhir;

/**
 * Enumération permettant de décrire l'état d'une manche.
 */
public enum EtatManche {
	/**
	 * La manche a été créé.
	 */
	DEBUT_MANCHE,
	
	/**
	 * La manche attend que les joueurs choisissent de prendre ou non une carte alliés.
	 */
	EN_ATTENTE_CHOIX_CARTE_ALLIES,
	
	/**
	 * La partie est prête à démarrer.
	 */
	PRET_A_DEMARRER,
	
	/**
	 * Une saison a débuté.
	 */
	DEBUT_SAISON,
	
	/**
	 * Le tour d'un joueur a débuté.
	 */
	DEBUT_TOUR_JOUEUR,
	
	/**
	 * Le tour d'un joueur vient de se terminer.
	 */
	FIN_TOUR_JOUEUR,
	
	/**
	 * La saison actuelle vient de se terminer.
	 */
	FIN_SAISON,
	
	/**
	 * La manche vient de se finir.
	 */
	FIN_MANCHE
}
