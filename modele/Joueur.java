/**
 * 
 */
package modele;

import java.util.ArrayList;
import java.util.Random;

public class Joueur {
	private String nom = "";
	private int tresor = 0;
	private Quartier [] cite = new Quartier[8];
	private int nbQuartier = 0;
	private ArrayList<Quartier> main = new ArrayList<Quartier>();
	private boolean possedeCouronne = false;
	
	
	public Joueur(String nom) {
		this.nom = nom;
		this.tresor = 0;
		this.nbQuartier = 0;
		this.main = new ArrayList<Quartier>();
		this.possedeCouronne = false;
		
	}


	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}


	/**
	 * @return the tresor
	 */
	public int nbPieces() {
		return tresor;
	}
	
	public int nbQuartiersDansCite() {
		int nbQuartiersDansCite =0;
		for(int i = 0; i<cite.length ; i++) {
			if(cite[i]!=null && !cite[i].getNom().equals("") ) {
				nbQuartiersDansCite++;
			}else if(cite[i]==null) {
				continue;
			}
		}
		return nbQuartiersDansCite;
	}


	/**
	 * @return the cite
	 */
	public Quartier[] getCite() {
		return cite;
	}


	/**
	 * @return the nbQuartier
	 */
	public int nbQuartiersDansMain() {
		return nbQuartier;
	}


	/**
	 * @return the main
	 */
	public ArrayList<Quartier> getMain() {
		return main;
	}


	/**
	 * @return the possedeCouronne
	 */
	public boolean getPossedeCouronne() {
		return possedeCouronne;
	}


	public void setPossedeCouronne(boolean possedeCouronne) {
		this.possedeCouronne = possedeCouronne;
	}
	
	public void ajouterPieces(int nbpieces) {
		if(nbpieces>0) {
			this.tresor += nbpieces;
		}
	}
	
	public void retirerPieces(int nbpieces) {
		if(nbpieces>=0) {
			if(nbpieces<=this.tresor) {
				this.tresor-=nbpieces;
			}
		}
	}
	
	public void ajouterQuartierDansCite(Quartier quart) {
		int compteur =0;
		boolean ajouter = false;
		
		do {
			if(cite[compteur]==null) {
				this.cite[compteur]= quart;
				ajouter = true;
			}
			compteur++;
		}while(compteur<cite.length && ajouter!=true);
	}
	
	public boolean quartierPresentDansCite(String nomQuartier) {
		boolean estPresent =false;
		for (int j= 0; j< this.nbQuartiersDansCite();j++) {
			if(cite[j].getNom()==nomQuartier) {
				estPresent = true;
				
				
			}
		}
				
		return estPresent;
	}
	
	public Quartier retirerQuartierDansCite(String nomQuartier) {
		Quartier quart = null;
		int compteur =0;
		do {
			if(cite[compteur].getNom() == nomQuartier) {
				
				/* Autre methode (a voir)
				quart = cite[compteur];
				cite[compteur] = new Quartier();
				
				return quart;
				*/
				
				quart = cite[compteur];
				for(int j=0; j< (this.nbQuartiersDansCite() - (compteur+1)) ;j++) {
					cite[compteur] = cite[compteur+1];
					cite[compteur+1] = null;
				}
											
				return quart;
			}
			compteur++;
		}while(compteur<cite.length);
		
		
		return quart;
	}
	
	public void ajouterQuartierDansMain(Quartier quart) {
		this.main.add(quart);
	}
	
	public Quartier retirerQuartierDansMain() {
		Random generator = new Random();
		int numAleatoire = generator.nextInt(this.nbQuartier);
		Quartier quart = this.main.get(numAleatoire);
		this.main.remove(numAleatoire);
		return quart;
	}
	
	public void reinitialiser() {
		this.tresor = 0;
		this.main.clear();
		this.cite = new Quartier[8];	
	}
	

}