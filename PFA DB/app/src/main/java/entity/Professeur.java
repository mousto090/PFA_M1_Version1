package entity;

/**
 * Created by SIMO on 10/04/2016.
 */
public class Professeur {

    private int IDProf;

    private String nom;

    private String prenom;

    private String email;

    private String tel;

    private String login;

    private String pass;

    public Professeur(String login, String pass){
        this.login = login;
        this.pass = pass;
    }

    public void setIDProf(int value) {
        this.IDProf = value;
    }

    public int getIDProf() {
        return IDProf;
    }

    public void setNom(String value) {
        this.nom = value;
    }

    public String getNom() {
        return nom;
    }

    public void setPrenom(String value) {
        this.prenom = value;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public String getEmail() {
        return email;
    }

    public void setTel(String value) {
        this.tel = value;
    }

    public String getTel() {
        return tel;
    }

    public void setLogin(String value) {
        this.login = value;
    }

    public String getLogin() {
        return login;
    }

    public void setPass(String value) {
        this.pass = value;
    }

    public String getPass() {
        return pass;
    }

    @Override
    public String toString() {
        return "Professeur : " + login + " " + pass;
    }
}
