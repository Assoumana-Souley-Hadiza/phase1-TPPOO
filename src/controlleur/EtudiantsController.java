package controlleur;

import models.Enseignant;
import models.Etudiant;
import models.Filiere;
import services.*;


public class EtudiantsController {

    public static void showMenu(){
        System.out.println("-------------------------[ Etudiants ]---------------------------");


        System.out.println("1: Pour ajouter un etudiant");
        System.out.println("2: Pour afficher les etudiants");
        System.out.println("3: Pour modifier un etudiant");
        System.out.println("4: Pour supprimer un etudiant");
        System.out.println("0: Pour retourner au menu principal");

        //"Veuillez sélectionner une option : ")
        int option = myscanner.getIntInput("Veuillez sélectionner une option : ");
        switch(option) {
            case 1:
                createEtudiant();
                break;
            case 2:
                showEtudiants();
                break;
            case 3:
                editEtudiant();
                break;
            case 4:
                destroyEtudiant();
                break;
            default:
                Main.showPrincipalMenu();
        }
    }

    public static void showEtudiants() {
        for (Etudiant etudiant : DB.etudiants) {
            System.out.print("Id : " + etudiant.getId());
            System.out.print(" | Nom : " + etudiant.getNom() + " " + etudiant.getPrenom());
            System.out.print(" | Email : " + etudiant.getEmail());
            System.out.print("| Apogée: " + etudiant.getApogee());
            if (etudiant.getFiliere() != null) {
                String filiereIntitule = etudiant.getFiliere().getIntitule();
                System.out.println("filiere= " + etudiant.getFiliere().getIntitule());
            } else {
                System.out.println(" ");
            }
        }
    }
    public static void createEtudiant(){
        String nom = myscanner.getStringInput("Entrez le nom :");
        String prenom = myscanner.getStringInput("Entrez le prenom :");
        String email = myscanner.getStringInput("Entrez l'email' :");
        int apogee = myscanner.getIntInput("Entrez l'apogee' :");
        int id = myscanner.getIntInput("Sélectionnez une filiere par id :");
        EtudiantServices.addEtd(nom,prenom,email,apogee, FiliereServices.getFiliereById(id));
        showEtudiants();
        showMenu();
    }
    public static void editEtudiant(){
        showEtudiants();
        int id = myscanner.getIntInput("Sélecionnez un etudiant par id :");
        String nom = myscanner.getStringInput("Entrez le nom :");
        String prenom = myscanner.getStringInput("Entrez le prenom :");
        String email = myscanner.getStringInput("Entrez l'email' :");
        int apogee = myscanner.getIntInput("Entrez l'apogée' :");
        FilieresController.showFilieres();
        int idfil= myscanner.getIntInput("Sélecionnez une filiere par id :");

        EtudiantServices.updateEtd(id,nom,prenom,email,apogee, FiliereServices.getFiliereById(idfil));

        showEtudiants();
        showMenu();
    }
    public static void destroyEtudiant(){
        showEtudiants();
        int id = myscanner.getIntInput("Sélecionnez un Etudiant par id :");
        Etudiant etudiantdelete=EtudiantServices.getEtdById(id);
        if(etudiantdelete!=null){
            EtudiantServices.deleteEtdById(id);
            System.out.println("etudiant supprimé avec succes");
        }else{
            System.out.println("aucun etudiant trouvé");
        }

        showEtudiants();
        showMenu();
    }
}



