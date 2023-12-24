package services;

import models.Departement;
import models.Enseignant;
import java.util.ArrayList;

public class EnseignantServices {

    public static Enseignant addEns(String nom, String prenom, String email, String grade, Departement dept){
        Enseignant enseignant=new Enseignant();
        enseignant.setNom(nom);
        enseignant.setPrenom(prenom);
        enseignant.setEmail(email);
        enseignant.setGrade(grade);
        enseignant.setDept(dept);
        enseignant.setId(DB.getEnsId());
        DB.enseignants.add(enseignant);
        return enseignant;
    }
    public static Enseignant updateEns(int id, String nom, String prenom, String email, String grade, Departement dept){
        for (Enseignant enseignant : DB.enseignants) {
            if (enseignant.getId() == id) {
                enseignant.setNom(nom);
                enseignant.setPrenom(prenom);
                enseignant.setGrade(grade);
                enseignant.setEmail(email);
                enseignant.setDept(dept);
                return enseignant;
            }
        }
        return null;
    }



    public static ArrayList<Enseignant> deleteEnsById(int id){
        Enseignant enseignantdelete=getEnsById(id);
        if(enseignantdelete!= null)
        {
            DB.enseignants.remove(enseignantdelete);
        }
        return  DB.enseignants;
    }

    public static Enseignant getEnsById(int id){
        for (Enseignant enseignant : DB.enseignants) {
            if (enseignant.getId() == id) return  enseignant;
        }
        return null;
    }

    public static ArrayList<Enseignant> getAllEns(){
        return new ArrayList<>(DB.enseignants) ;
    }
}
