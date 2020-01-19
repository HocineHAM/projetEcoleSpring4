package monEcole.controller;

import java.util.List;

import javax.validation.Valid;

public class ModificationProfesseur {
		@Valid
		private List<CreationForm> listeProfesseurs;

		public List<CreationForm> getListeProfesseurs() {
			return listeProfesseurs;
		}

		public void setListeProfesseurs(List<CreationForm> listeProfesseurs) {
			this.listeProfesseurs = listeProfesseurs;
		}

}
