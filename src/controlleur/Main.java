package controlleur;

import models.Departement;
import models.Enseignant;
import services.DepartementServices;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import controlleur.*;
import models.*;
import services.*;


public class Main {public static boolean isNull(Object ob) {
    return ob == null ;
}


    public static void showPrincipalMenu(){
        System.out.println("-------------------------[ Bienvenu ]---------------------------");


        System.out.println("1: Pour gérer les départements");
        System.out.println("2: Pour gérer les filières");
        System.out.println("3: Pour gérer les enseignants");
        System.out.println("4: Pour gérer les modules");
        System.out.println("5: Pour gérer les étudiants");
        System.out.println("0: Pour sortir");

        //"Veuillez sélectionner une option : ")
        int option = myscanner.getIntInput("Veuillez sélectionner une option : ");
        switch(option) {
            case 1:
                DepartementsController.showMenu();
                break;
            case 2:
                FilieresController.showMenu();
                break;
            case 3:
                EnseignantsController.showMenu();
                break;
            case 4:
                ModulesController.showMenu();
                break;
            case 5:
                EtudiantsController.showMenu();
                break;
            default:
                // code block
        }



    }
    public static void main(String[] args) {
       // showPrincipalMenu();
       /* Enseignant enseignant = new Enseignant();
        enseignant.setNom("wahab");
        enseignant.setPrenom("Ait");
        enseignant.setEmail("wahab@gmail.com");
        enseignant.setGrade("prof");*/



        String url = "jdbc:mysql://localhost:3306/gestion_educative";
        String user = "root";
        String pwd = "";
        try {
            Connection cx = DriverManager.getConnection(url, user, pwd);
            System.out.println("Good Connection");
           // insertenseignant(enseignant,cx);
            List<Enseignant> enseignants = getALLEns(cx);
            for (Enseignant enseignant : enseignants) {
                System.out.println(enseignant);
            }

    } catch (SQLException e) {
            System.out.println("Bad Connection");
            e.printStackTrace();
        }

        }
    public static void deleteenseignant(int id, Connection cx) throws SQLException {
        String query = "DELETE  from gestion_educative.enseignant where id = ?";
        PreparedStatement ps = cx.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();


    }
    public static void insertenseignant(Enseignant enseignant, Connection cx) throws SQLException {
        String query = "INSERT INTO enseignant(nom, prenom, email, grade, departement_id) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = cx.prepareStatement(query);
        PreparedStatement p = cx.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        int idDepartement = 3;
        ps.setString(1, enseignant.getNom());
        ps.setString(2, enseignant.getPrenom());
        ps.setString(3, enseignant.getEmail());
        ps.setString(4, enseignant.getGrade());
        ps.setInt(5, idDepartement);

        ps.executeUpdate();

        ResultSet generatedKeys = p.getGeneratedKeys();
        if (generatedKeys.next()) {
            int idEnseignant = generatedKeys.getInt("id");
            enseignant.setId(idEnseignant);
            System.out.println("Enseignant inséré avec succès. ID généré : " + idEnseignant);
        } else {
            System.out.println("Échec de la récupération de l'ID généré pour l'enseignant.");
        }
        System.out.println("Enseignant inséré avec succès.");
    }



    public static List<Enseignant> getALLEns(Connection cx) throws SQLException {
        String query = "SELECT * from enseignant";
        List<Enseignant> enseignants = new ArrayList<>();

        Statement st = cx.createStatement();

        ResultSet r = st.executeQuery(query);

        while (r.next()) {
            int idEnseignant = r.getInt("id");
            Departement departement= DepartementServices.getDeptById(r.getInt("departement_id"));
            enseignants.add(
                    new Enseignant(idEnseignant,
                            r.getString("nom"),
                            r.getString("prenom"),
                            r.getString("email"),
                            r.getString("grade"),
                            departement
                    )

            );
        }

        return enseignants;


    }
    public static void createTable(Connection cx) throws SQLException {
        // Création de la table pour Enseignant
        String queryEnseignant = "CREATE TABLE IF NOT EXISTS enseignant (\n" +
                "  id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "  nom VARCHAR(255),\n" +
                "  prenom VARCHAR(255),\n" +
                "  email VARCHAR(255),\n" +
                "  grade VARCHAR(255),\n" +
                "  departement_id INT,\n" +  // Ajout de la colonne pour la relation avec Departement
                "  FOREIGN KEY (departement_id) REFERENCES departement(id)\n" +
                ");";
        Statement st=cx.createStatement();
        st.execute(queryEnseignant);
    }
}

