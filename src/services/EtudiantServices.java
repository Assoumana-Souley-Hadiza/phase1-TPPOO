package services;

import models.Enseignant;
import models.Etudiant;
import models.Filiere;
import java.util.ArrayList;

public class EtudiantServices {

    public static Etudiant addEtd(String nom, String prenom, String email, int apogee, Filiere filiere){
        Etudiant etudiant=new Etudiant();
       etudiant.setNom(nom);
        etudiant.setPrenom(prenom);
        etudiant.setEmail(email);
        etudiant.setId(apogee);
        etudiant.setFiliere(filiere);
        DB.etudiants.add(etudiant);
        return   etudiant;
    }

    public static Etudiant updateEtd(int id,String nom, String prenom, String email, int apogee, Filiere filiere){

        for (Etudiant etudiant : DB.etudiants) {
            if (etudiant.getId() == id) {
                etudiant.setNom(nom);
                etudiant.setPrenom(prenom);
                etudiant.setEmail(email);
                etudiant.setFiliere(filiere);
                return  etudiant;
            }
        }
        return null;
    }
    public static ArrayList<Etudiant> deleteEtdById(int id){
        Etudiant etudiantdelete=getEtdById(id);
        if(etudiantdelete!= null){
            DB.etudiants.remove(etudiantdelete);
        }
        return  DB.etudiants;
    }

    public static Etudiant getEtdById(int id){
        for (Etudiant etudiant : DB.etudiants) {
            if (etudiant.getId() == id) return  etudiant;
        }
        return  null;
    }
    public static ArrayList<Etudiant> getAllEtd(){

        return  new ArrayList<>(DB.etudiants);
    }
}
