package monEcole.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import monEcole.bean.Professeur;
import monEcole.enumeration.Sexe;
import monEcole.service.IServiceListeProfesseur;



@Controller
public class CreationProfesseur {
	@Autowired
	private IServiceListeProfesseur service;
	
	@RequestMapping(value = "/afficherProfesseur", method = RequestMethod.GET)
	public String afficher(ModelMap pModel) {
		final List<Professeur> lListeProfesseur = service.rechercherProfesseur();
		pModel.addAttribute("listeProfesseurs",lListeProfesseur);
		return "listeProfesseurs";
	}

	
	@RequestMapping(value = "/afficherCreationProfesseur", method = RequestMethod.GET)
	public String afficherCreer(final ModelMap pModel) {
		final List<Professeur> lListeProfesseurs = service.rechercherProfesseur();
		pModel.addAttribute("listeProfesseurs",lListeProfesseurs);
		if (pModel.get("creationProf") == null) {
			pModel.addAttribute("creationProf", new CreationForm());
		}
		return "creationProf";
	}


	@RequestMapping(value = "/creerCreationProfesseur", method = RequestMethod.POST)
	public String creer(@Valid @ModelAttribute(value = "creationProf") final CreationForm pCreation,
			final BindingResult pBindingResult, final ModelMap pModel) {
		if (!pBindingResult.hasErrors()) {
			
			
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat sd = new SimpleDateFormat(pattern);
			sd.setLenient(false);
			Date date =null;
			try {
				date = sd.parse(pCreation.getDate_naissance());
			}catch(Exception e) {
				e.getStackTrace();
			}
			
			Sexe sexe = Sexe.FEMME;
			if( !pCreation.getSexe().equalsIgnoreCase(Sexe.FEMME.toString())) sexe = Sexe.HOMME;
			
			service.creerProfesseur(pCreation.getNom(), pCreation.getPrenom(), date, pCreation.getAdresse(), sexe);
		}
		return afficherCreer(pModel);
	}
	
	@RequestMapping(value = "/afficherModificationProfesseur", method = RequestMethod.GET)
	public String afficherModification(final ModelMap pModel) {
		if (pModel.get("modification") == null) {
			final List<Professeur> lListeProf = service.rechercherProfesseur();
			final ModificationProfesseur lModificationForm = new ModificationProfesseur();
			final List<CreationForm> lListe = new LinkedList<CreationForm>();
			for (final Professeur prof : lListeProf) {
				final CreationForm lModificationProf = new CreationForm();
				lModificationProf.setId(prof.getId());
				lModificationProf.setNom(prof.getNom());
				lModificationProf.setPrenom(prof.getPrenom());
				lModificationProf.setAdresse(prof.getAdresse());
				lModificationProf.setDate_naissance(prof.getDate_naissance().toString());
				lModificationProf.setSexe(prof.getSexe().toString());
				lListe.add(lModificationProf);
			}
			lModificationForm.setListeProfesseurs(lListe);
			pModel.addAttribute("modification", lModificationForm);
		}
		return "modification";
	}

	@RequestMapping(value = "/modifierProfesseur", method = RequestMethod.POST)
	public String modifier(@Valid @ModelAttribute(value = "modification") final ModificationProfesseur pModification,
			final BindingResult pBindingResult, final ModelMap pModel) {
		if (!pBindingResult.hasErrors()) {
			final List<Professeur> lListeCourses = new LinkedList<Professeur>();
			for (final CreationForm lProfForm : pModification.getListeProfesseurs()) {
				final Professeur lProf = new Professeur();
				lProf.setId(lProfForm.getId());
				lProf.setAdresse(lProfForm.getAdresse());
				lProf.setNom(lProfForm.getNom());
				lProf.setPrenom(lProfForm.getPrenom());
				String pattern = "yyyy-MM-dd";
				SimpleDateFormat sd = new SimpleDateFormat(pattern);
				sd.setLenient(false);
				try {
					lProf.setDate_naissance(sd.parse(lProfForm.getDate_naissance()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Sexe sexe = Sexe.FEMME;
				if( !lProfForm.getSexe().equalsIgnoreCase(Sexe.FEMME.toString())) sexe = Sexe.HOMME;
				
				lProf.setSexe(sexe);
				lListeCourses.add(lProf);
			}
			service.modifierProfesseur(lListeCourses);
		}
		return afficherModification(pModel);
	}

	
}
