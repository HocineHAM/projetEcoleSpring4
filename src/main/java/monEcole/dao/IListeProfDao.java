package monEcole.dao;

import java.util.List;

import monEcole.bean.Professeur;


public interface IListeProfDao {
	List<Professeur> rechercherProfesseur();

	void creerProfesseur(final Professeur pProf);
	void supprimerProfesseur(final Professeur pProf);
	void modifierProfesseur(final Professeur pProf);
}
