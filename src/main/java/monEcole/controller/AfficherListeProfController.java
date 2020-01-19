package monEcole.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import monEcole.bean.Professeur;
import monEcole.service.IServiceListeProfesseur;


@Controller
@RequestMapping(value = "/afficherListeProfesseurs")
public class AfficherListeProfController {
	
	@Autowired
	private IServiceListeProfesseur service;
	
	@RequestMapping(method = RequestMethod.GET)
	public String afficher (Model model) {
		
		final List<Professeur> lListeProfesseurs = service.rechercherProfesseur();
		model.addAttribute("listeProfesseurs", lListeProfesseurs);
		return "listeProfesseurs";
		
		
	}

}
