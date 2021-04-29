package games;

import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) {
        Database db = new Database("projetl3", "hadja", "H@dja2020");

        try {
            db.importSqlFile("bdcasino.sql");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}