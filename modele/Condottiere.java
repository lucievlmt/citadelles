package modele;

import controleur.Interaction;

public class Condottiere extends Personnage{

	public Condottiere() {
		super("Condottiere", 8, Caracteristiques.CONDOTTIERE);
		// TODO Auto-generated constructor stub
	}
	
	public void utiliserPouvoir() {
		System.out.println("Voulez vous utiliser votre pouvoir de destruction ?");
		boolean temp = Interaction.lireOuiOuNon();
		if(temp) {
			for(int i =0 ; i< super.getPlateau().getNombreJoueurs() ; i++) {
				System.out.println((i+1) + " - " + super.getPlateau().getJoueur(i).getNom()+ ": ");
				for(int j = 0; j<super.getPlateau().getJoueur(i).nbQuartiersDansCite(); j++) {
					System.out.println("-> "+(j+1) + "-" + super.getPlateau().getJoueur(i).getCite()[j].getNom()
							+ "(co�t " +(super.getPlateau().getJoueur(i).getCite()[j].getCout()-1) + ")," );
					
				}
			}
			System.out.println("Pour information vous avez : " + super.getJoueur().nbPieces() + " pieces dans votre tr�sor");
			System.out.println("Quel joueurs choisissez-vous ? (0 pour ne rien faire)");
			int temp2  = Interaction.lireUnEntier(0, (super.getPlateau().getNombreJoueurs()+1));
			if(temp2==0 || super.getPlateau().getJoueur(temp2-1).getPersonnage().getNom().equals("Eveque")) {
				System.out.println("Vous n'utilisez pas votre pouvoir");
			}else {
				temp2--;
				boolean boucle;
				do {
					boucle = false;
					System.out.println("Quel quartier voulez vous d�truire ?");
					int temp3 = Interaction.lireUnEntier(0, super.getPlateau().getJoueur(temp2).getCite().length);
					if(temp3==0) {
						System.out.println("Pouvoir annul�");
						break;
					}
					temp3--;
					if(super.getJoueur().nbPieces()<(super.getPlateau().getJoueur(temp2).getCite()[temp3].getCout()-1)) {
						System.out.println("Votre tr�sor n'est pas suffisant");
						boucle = true;
					}else {
						super.getJoueur().retirerPieces((super.getPlateau().getJoueur(temp2).getCite()[temp3].getCout()-1));
						
						System.out.println("=> On retire " + super.getPlateau().getJoueur(temp2).getCite()[temp3].getNom() 
								+ " de " + super.getPlateau().getJoueur(temp2).getNom());
						
						super.getPlateau().getJoueur(temp2).retirerQuartierDansCite
						(super.getPlateau().getJoueur(temp2).getCite()[temp3].getNom());
						
						
						System.out.println("Il vous reste "+ super.getJoueur().nbPieces()  + " pi�ces dans votre tr�sor");
					}
				}while(boucle);
				
			}
			
			
		}
	}
	
	public String percevoirRessourcesSpecifiques() {
		for(int i =0; i<super.getJoueur().nbQuartiersDansCite();i++) {
					
					if(super.getJoueur().getCite()[i].getType().equals("MILITAIRE")) {
						super.getJoueur().ajouterPieces(1);
					}
				}
		
		return "";
		
	}

}
