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

package fr.utt.girardguittard.levasseur.menhir.joueurs;

/**
 * Classe d'exception signalant que le joueur a voulu jouer une carte invalide (null ou pas présente dans la main du joueur).
 */
public class CarteInvalideException extends Exception {

	private static final long serialVersionUID = 6828993139162706250L;

	public CarteInvalideException() {
	}

	public CarteInvalideException(String message) {
		super(message);
	}

	public CarteInvalideException(Throwable cause) {
		super(cause);
	}

	public CarteInvalideException(String message, Throwable cause) {
		super(message, cause);
	}

	public CarteInvalideException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
