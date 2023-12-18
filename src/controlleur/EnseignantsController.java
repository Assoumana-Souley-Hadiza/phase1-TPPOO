package controlleur;
import controlleur.myscanner;
import models.Enseignant;
import services.DB;
import services.DepartementServices;
import services.EnseignantServices;

public class EnseignantsController {

    public static void showMenu(){
        System.out.println("-------------------------[ Enseignant ]---------------------------");


        System.out.println("1: Pour ajouter un enseignant");
        System.out.println("2: Pour afficher les enseignants");
        System.out.println("3: Pour modifier un enseignant");
        System.out.println("4: Pour supprimer un enseignant");
        System.out.println("0: Pour retourner au menu principal");

        //"Veuillez sélectionner une option : ")
        int option = myscanner.getIntInput("Veuillez sélectionner une option : ");
        switch(option) {
            case 1:
                createEnseignant();
                break;
            case 2:
                showEnseignants();
                break;
            case 3:
                editEnseignant();
                break;
            case 4:
                destroyEnseignant();
                break;
            default:
                Main.showPrincipalMenu();
        }
    }


    public static void showEnseignants(){
        for (Enseignant enseignant : DB.enseignants) {
            System.out.print("Id : " + enseignant.getId());
            System.out.print(" | Nom : " + enseignant.getNom() + " " + enseignant.getPrenom());
            System.out.print(" | Email : " + enseignant.getEmail() );
            System.out.print(" | Departement : " + enseignant.getDept().getIntitule());
            System.out.println("");
        }
    }
    public static void createEnseignant(){
        String nom = myscanner.getStringInput("Entrez le nom :");
        String prenom = myscanner.getStringInput("Entrez le prenom :");
        String email = myscanner.getStringInput("Entrez l'email' :");
        String grade = myscanner.getStringInput("Entrez le grade' :");
        int id = myscanner.getIntInput("Sélecionnez un departement par id :");
        EnseignantServices.addEns(nom,prenom,email,grade,DepartementServices.getDeptById(id));
        showEnseignants();
        showMenu();
    }
    public static void editEnseignant(){
        showEnseignants();
        int id = myscanner.getIntInput("Sélecionnez un enseignant par id :");
        String nom = myscanner.getStringInput("Entrez le nom :");
        String prenom = myscanner.getStringInput("Entrez le prenom :");
        String email = myscanner.getStringInput("Entrez l'email' :");
        String grade = myscanner.getStringInput("Entrez le grade' :");
        DepartementsController.showDepartements();
        int iddept= myscanner.getIntInput("Sélecionnez un departement par id :");

        EnseignantServices.updateEns(id,nom,prenom,email,grade,DepartementServices.getDeptById(iddept));

        showEnseignants();
        showMenu();
    }
    public static void destroyEnseignant(){
        showEnseignants();
        int id = myscanner.getIntInput("Sélecionnez un Enseignant par id :");
        Enseignant enseignantdelete=EnseignantServices.getEnsById(id);
        if(enseignantdelete!=null){
            EnseignantServices.deleteEnsById(id);
            System.out.println("enseignant supprimé avec succes");
        }else{
            System.out.println("aucun enseignant trouvé");
        }

        showEnseignants();
        showMenu();
    }
}
