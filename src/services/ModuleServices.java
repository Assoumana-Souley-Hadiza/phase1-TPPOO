package services;

import models.Enseignant;
import models.Filiere;
import models.Module;
import java.util.ArrayList;

public class ModuleServices {

    public static Module addModule(String intitule, Enseignant chef,  Filiere filiere) {
        Module module=new Module();
        module.setIntitule(intitule);
        module.setChef(chef);
        module.setFiliere(filiere);
        DB.modules.add(module);
        return  module;
    }

    public static Module updateModule(int id , String intitule, Enseignant chef,  Filiere filiere){
        for(Module module:DB.modules){
            if(module.getId()==id){
                module.setIntitule(intitule);
                module.setChef(chef);
                module.setFiliere(filiere);
                return module;
            }
        }
        return  null;
    }
    public static ArrayList<Module> deleteModuleById(int id){
        Module moduledelete=getModuleById(id);
        if(moduledelete!=null){
            DB.modules.remove(moduledelete);
        }
        return  DB.modules;
    }

    public static Module getModuleById(int id){
        for(Module module:DB.modules){
            if(module.getId()==id) return module;
        }
        return  null;
    }

    public static ArrayList<Module> getAllModule(){
        return new ArrayList<>( DB.modules);
    }
}
