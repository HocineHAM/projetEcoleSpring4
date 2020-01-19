package monEcole.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import monEcole.bean.Professeur;
import monEcole.dao.IListeProfDao;
import monEcole.enumeration.Sexe;


@Service
public class ServiceListeProfesseur implements IServiceListeProfesseur {

	@Autowired
	private IListeProfDao dao;

	@Transactional(readOnly = true)
	public List<Professeur> rechercherProfesseur() {
		// TODO Auto-generated method stub
		return dao.rechercherProfesseur();
	}

	@Transactional
	public void creerProfesseur(String nom, String prenom, Date date, String adresse, Sexe sexe) {
		final Professeur prof = new Professeur();

		prof.setNom(nom);
		prof.setPrenom(prenom);
		prof.setDate_naissance(date);
		prof.setAdresse(adresse);
		prof.setSexe(sexe);

		dao.creerProfesseur(prof);
	}

	@Transactional
	public void supprimerProfesseur(final Integer pIdProfesseur) {
		final Professeur lprof = new Professeur();
		lprof.setId(pIdProfesseur);
		dao.supprimerProfesseur(lprof);
	}
	
	

	@Transactional
	public void modifierProfesseur(List<Professeur> plisteProf) {
		// TODO Auto-generated method stub
		for(final Professeur pProf : plisteProf) {
			dao.modifierProfesseur(pProf);
		}
		
	}

	}

	


