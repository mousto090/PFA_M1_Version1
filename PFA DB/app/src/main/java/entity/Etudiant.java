package entity;

/**
 * Created by SIMO on 16/04/2016.
 */
public class Etudiant {

    private int id;
    private String  nom,
                    prenom,
                    image;
    private boolean estAbsent;


    public Etudiant(String nom, String prenom, String image) {
        this.nom = nom;
        this.prenom = prenom;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isEstAbsent() {
        return estAbsent;
    }

    public void setEstAbsent(boolean estAbsent) {
        this.estAbsent = estAbsent;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "id ='" + id + '\'' +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", image='" + image + '\'' +
                ", estAbsent " + estAbsent +
                '}';
    }
}
