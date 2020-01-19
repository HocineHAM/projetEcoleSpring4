package monEcole.service;

import java.util.Date;
import java.util.List;

import monEcole.bean.Professeur;
import monEcole.enumeration.Sexe;



public interface IServiceListeProfesseur {
	List<Professeur> rechercherProfesseur();
	
	void supprimerProfesseur(final Integer pProf);
	void modifierProfesseur(final List<Professeur> lListeCourses);
	void creerProfesseur(String nom, String prenom, Date date, String adresse, Sexe sexe);
}
