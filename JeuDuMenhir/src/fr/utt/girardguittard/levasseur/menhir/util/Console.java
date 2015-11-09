package fr.utt.girardguittard.levasseur.menhir.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Console permet de gérer la sortie et l'entrée de la console de manière plus simple.
 * En effet, lorsqu'une application Java est testée dans Eclipse, la lecture de données depuis la console
 * n'est pas possible avec System.console(). Cette classe encapsule donc un BufferedReader permettant la
 * récupération de données depuis la console.
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
	
}
