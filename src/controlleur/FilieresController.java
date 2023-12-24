package controlleur;
import models.Etudiant;
import services.*;
import models.Filiere;

public class FilieresController {
    public static void showMenu(){
        System.out.println("-------------------------[ Filieres ]---------------------------");


        System.out.println("1: Pour ajouter une filiere");
        System.out.println("2: Pour afficher les filieres");
        System.out.println("3: Pour modifier une filiere");
        System.out.println("4: Pour supprimer une filiere");
        System.out.println("0: Pour retourner au menu principal");

        //"Veuillez sélectionner une option : ")
        int option = myscanner.getIntInput("Veuillez sélectionner une option : ");
        switch(option) {
            case 1:
                createFiliere();
                break;
            case 2:
                showFilieres();
                break;
            case 3:
                editFiliere();
                break;
            case 4:
                destroyFiliere();
                break;
            default:
                Main.showPrincipalMenu();
        }
    }

    public static void showFilieres(){
        for (Filiere filiere : DB.filieres) {
            System.out.print("Id : " + filiere.getId());
            System.out.print(" | l'intitulé : " + filiere.getIntitule());
            if (filiere.getChef() != null && filiere.getDept()!=null) {
                String filierechef =filiere.getChef().getNom();
                String departement=filiere.getDept().getIntitule();
                System.out.println("filierechef= " +filiere.getChef().getNom());
                System.out.println("departement= " +filiere.getDept().getIntitule());
            } else {
                System.out.println(" ");
            }
        }
    }
    public static void createFiliere(){
        String intitule = myscanner.getStringInput("Entrez l'intitulé :");
        EnseignantsController.showEnseignants();
        int idens = myscanner.getIntInput("Sélecionnez un enseignant par id :");
        DepartementsController.showDepartements();
        int iddept = myscanner.getIntInput("Sélecionnez un departement par id :");
        FiliereServices.addFiliere(intitule, EnseignantServices.getEnsById(idens), DepartementServices.getDeptById(iddept));
        showFilieres();
        showMenu();
    }
    public static void editFiliere(){
        showFilieres();
        int id = myscanner.getIntInput("Sélecionnez une filiere par id :");
        String intitule = myscanner.getStringInput("Entrez l'intitulé :");
        EnseignantsController.showEnseignants();
        int idens= myscanner.getIntInput("Sélecionnez un enseignant par id :");
        DepartementsController.showDepartements();
        int iddept= myscanner.getIntInput("Sélecionnez un departement par id :");
        FiliereServices.updateFiliere(id,intitule,EnseignantServices.getEnsById(idens),DepartementServices.getDeptById(iddept));
        showFilieres();
        showMenu();
    }
    public static void destroyFiliere(){
        showFilieres();
        int id = myscanner.getIntInput("Sélecionnez une filiere par id :");
        Filiere filieredelete=FiliereServices.getFiliereById(id);
        if(filieredelete!=null){
            FiliereServices.deleteFiliereById(id);
            System.out.println("filiere supprimé avec succes");
        }else{
            System.out.println("aucune filiere trouvée");
        }
        showFilieres();
        showMenu();
    }
}


