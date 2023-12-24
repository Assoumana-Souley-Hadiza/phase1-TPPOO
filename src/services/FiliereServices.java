package services;

import models.Departement;
import models.Enseignant;
import models.Filiere;
import java.util.ArrayList;

public class FiliereServices {

    public static Filiere addFiliere(String intitule, Enseignant chef, Departement dept) {
       Filiere filiere = new Filiere();
        filiere.setIntitule(intitule);
        filiere.setDept(dept);
        filiere.setChef(chef);
        filiere.setId(DB.getFilIdId());
        DB.filieres.add(filiere);
        return   filiere;
    }

    public static Filiere updateFiliere(int id , String intitule, Enseignant chef, Departement dept){
        for (Filiere filiere : DB.filieres) {
            if (filiere.getId() == id) {
                filiere.setIntitule(intitule);
                filiere.setChef(chef);
                filiere.setDept(dept);
                return filiere;
            }
        }
        return  null;
    }
    public static ArrayList<Filiere> deleteFiliereById(int id){
        Filiere filieredelete=getFiliereById(id);
        if(filieredelete!=null){
            DB.filieres.remove(getFiliereById(id));
        }
        return  DB.filieres;
    }

    public static Filiere getFiliereById(int id){
        for (Filiere filiere : DB.filieres) {
            if (filiere.getId() == id) return  filiere;
        }
        return  null;
    }

    public static ArrayList<Filiere> getAllFiliere(){

        return new ArrayList<>(DB.filieres) ;
    }
}
