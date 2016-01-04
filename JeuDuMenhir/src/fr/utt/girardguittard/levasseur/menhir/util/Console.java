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

package fr.utt.girardguittard.levasseur.menhir.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Cette classe Console permet de gérer la sortie et l'entrée de la console de manière plus simple.
 * En effet, lorsqu'une application Java est testée dans Eclipse, la lecture de données depuis la console
 * n'est pas possible avec System.console(). Cette classe encapsule donc un BufferedReader permettant la
 * récupération de données depuis la console. Une méthode pour afficher du contenu est aussi fournie pour
 * garder une cohérence dans l'utilisation de la console.
 * 
 * C'est une classe Singleton.
 */
public class Console {
	
	private BufferedReader bufReader;
	
	static private Console instance = null;
	
	/**
	 * Retourne l'instance unique de Console et construit Console si nécessaire.
	 * @return l'instance unique de Console
	 */
	public static Console getInstance() {
		if( instance == null ) {
			instance = new Console();
		}
		
		return instance;
	}
	
	/**
	 * Constructeur privé de Console.
	 */
	private Console() {
		this.bufReader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	/**
	 * Affiche une sortie dans la console
	 * @param str la chaîne de caractère à afficher
	 */
	public void println(String str) {
		System.out.println(str);
	}
	
	/**
	 * Lit l'entrée de la console.
	 * @return la chaîne de caractère lue depuis la console, ou une chaîne vide en cas de problème de lecture
	 */
	public String readln() {
		try {
			return bufReader.readLine();
		} catch (IOException e) {
			return "";
		}
	}
	
	/**
	 * Affiche le message "&lt;Entrée&gt; pour continuer" et attends que l'utilisateur presse Entrée pour continuer.
	 */
	public void attendreEntree() {
		this.println("<Entrée> pour continuer");
		this.readln();
	}
}
