package entity;

/**
 * Created by SIMO on 21/04/2016.
 */
public class Classe {
    private String niveau;

    public Classe(String niveau) {
        this.niveau = niveau;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    @Override
    public String toString() {
        return "Classe{" +
                "niveau='" + niveau + '\'' +
                '}';
    }
}
