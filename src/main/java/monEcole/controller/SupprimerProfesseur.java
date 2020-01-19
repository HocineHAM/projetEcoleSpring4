package monEcole.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import monEcole.bean.Professeur;
import monEcole.service.IServiceListeProfesseur;

@Controller
public class SupprimerProfesseur {

	@Autowired
	private IServiceListeProfesseur service;
	
	@RequestMapping(value = "/afficherSuppressionListeProfesseur", method = RequestMethod.GET)
	public String afficher(final ModelMap pModel) {
		final List<Professeur> lListeProfesseur = service.rechercherProfesseur();
		pModel.addAttribute("listeProfesseurs", lListeProfesseur);
		return "suppression";
	}
	
	
	@RequestMapping(value = "/supprimerSuppressionProfesseur", method = RequestMethod.GET)
	public String supprimer(@RequestParam(value = "idProfesseur") final Integer pIdProfesseur, final ModelMap pModel) {
		service.supprimerProfesseur(pIdProfesseur);
		
		return afficher(pModel);
	}
	

}
