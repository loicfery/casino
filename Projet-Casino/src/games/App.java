package games;

import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) {
        Database db = new Database("casino", "root", "Bananaslayer2!");

        try {
            db.importSqlFile("bdcasino.sql");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}