package monEcole.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import monEcole.bean.Professeur;



@Repository
public class ListeProfesseurDao implements IListeProfDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	public List<Professeur> rechercherProfesseur() {
		final CriteriaBuilder lCriteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Professeur> lCriteriaQuery = lCriteriaBuilder.createQuery(Professeur.class);
		final Root<Professeur> lRoot = lCriteriaQuery.from(Professeur.class);
		lCriteriaQuery.select(lRoot);
		final TypedQuery<Professeur> lTypedQuery = entityManager.createQuery(lCriteriaQuery);
		return lTypedQuery.getResultList();
	}

	public void creerProfesseur(Professeur pProf) {
		entityManager.persist(pProf);
	}

	public void supprimerProfesseur(Professeur pProf) {
		final Professeur lProf = entityManager.getReference(Professeur.class, pProf.getId());
		entityManager.remove(lProf);
		
	}

	public void modifierProfesseur(Professeur pProf) {
		final CriteriaBuilder lCriteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaUpdate<Professeur> lCriteriaUpdate = lCriteriaBuilder.createCriteriaUpdate(Professeur.class);
		final Root<Professeur> lRoot = lCriteriaUpdate.from(Professeur.class);
		final Path<Professeur> lPath = lRoot.get("id");
		final Expression<Boolean> lExpression = lCriteriaBuilder.equal(lPath, pProf.getId());
		lCriteriaUpdate.where(lExpression);
		lCriteriaUpdate.set("nom", pProf.getNom());
		lCriteriaUpdate.set("prenom", pProf.getPrenom());
		lCriteriaUpdate.set("adresse", pProf.getAdresse());
		lCriteriaUpdate.set("date_naissance", pProf.getDate_naissance());
		lCriteriaUpdate.set("sexe", pProf.getSexe());
		final Query lQuery = entityManager.createQuery(lCriteriaUpdate);
		final int lRowCount = lQuery.executeUpdate();
		
		if (lRowCount != 1) {
			final org.hibernate.Query lHQuery = lQuery.unwrap(org.hibernate.Query.class);
			final String lSql = lHQuery.getQueryString();
			throw new RuntimeException("Nombre d'occurences (" + lRowCount + ") modifiÃ©s diffÃ©rent de 1 pour " + lSql);
		}

	}



}
