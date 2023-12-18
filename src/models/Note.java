package models;

public class Note {

    private double note;
    private Etudiant etudiant;
    private Filiere filiere;
    private Module module;

    public Note() {
    }

    public Note(double note, Etudiant etudiant, Filiere filiere,Module module) {
        this.note = note;
        this.etudiant = etudiant;
        this.filiere = filiere;
        this.module=module;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    @Override
    public String toString() {
        return "Note{" +
                "note=" + note +
                ", etudiant=" + etudiant +
                ", filiere=" + filiere +
                ", module=" + module +
                '}';
    }
}

