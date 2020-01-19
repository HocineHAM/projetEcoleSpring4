package monEcole.enumeration;

public enum Sexe {

HOMME("HOMME"), FEMME("FEMME");

private String code;

private Sexe(String code) {
	this.code = code;
	
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}


// Constructor of Enum internal use only
// Modifier of constructor is private
// If you do not declare private, java alway understand is private.    
//private Sexe(String code, String text) {
//    this.code = code;
//    this.text = text;
//}

}
