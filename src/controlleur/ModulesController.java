package controlleur;

import models.Departement;
import models.Filiere;
import services.*;
import models.Module;

public class ModulesController {
    public static void showMenu(){
        System.out.println("-------------------------[ Modules ]---------------------------");


        System.out.println("1: Pour ajouter un module");
        System.out.println("2: Pour afficher les modules");
        System.out.println("3: Pour modifier un module");
        System.out.println("4: Pour supprimer un module");
        System.out.println("0: Pour retourner au menu principal");

        //"Veuillez sélectionner une option : ")
        int option = myscanner.getIntInput("Veuillez sélectionner une option : ");
        switch(option) {
            case 1:
                createModule();
                break;
            case 2:
                showModules();
                break;
            case 3:
                editModule();
                break;
            case 4:
                destroyModule();
                break;
            default:
                Main.showPrincipalMenu();
        }
    }
    public static void showModules(){
        for (Module module : DB.modules) {
            System.out.print("Id : " + module.getId());
            System.out.print(" | Intitulé : " + module.getIntitule());
            if (module.getChef() != null && module.getFiliere()!=null) {
                String modulechef =module.getChef().getNom();
                String departement=module.getFiliere().getIntitule();
                System.out.println("modulechef= " +module.getChef().getNom());
                System.out.println("departement= " +module.getFiliere().getIntitule());
            } else {
                System.out.println(" ");
            }
            System.out.println("");
        }
    }
    public static void createModule(){
        String intitule = myscanner.getStringInput("Entrez l'intitulé :");
        EnseignantsController.showEnseignants();
        int id = myscanner.getIntInput("Sélecionnez un enseignant par id :");
        FilieresController.showFilieres();
        int idfil=myscanner.getIntInput("Sélecionnez une filiere par id :");
       ModuleServices.addModule(intitule,EnseignantServices.getEnsById(id),FiliereServices.getFiliereById(idfil));
        showModules();
        showMenu();


    }
    public static void editModule(){
        showModules();
        int id = myscanner.getIntInput("Sélecionnez un module par id :");
        String intitule = myscanner.getStringInput("Entrez l'intitulé :");
        EnseignantsController.showEnseignants();
        int idEns = myscanner.getIntInput("Sélecionnez un enseignant par id :");
       FilieresController.showFilieres();
       int idfil= myscanner.getIntInput("Sélecionnez une filiere par id :");
        ModuleServices.updateModule(id,intitule,EnseignantServices.getEnsById(idEns), FiliereServices.getFiliereById(idfil));
        showModules();
        showMenu();
    }
    public static void destroyModule(){
        showModules();
        int id = myscanner.getIntInput("Sélecionnez un module par id :");
       Module moduledelete=ModuleServices.getModuleById(id);
        if(moduledelete!=null){
            ModuleServices.deleteModuleById(id);
            System.out.println("Module supprimé avec succes");
        }else{
            System.out.println("aucun module trouvé");
        }
        showModules();
        showMenu();
    }
}
