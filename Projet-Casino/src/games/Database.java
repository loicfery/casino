package games;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class Database {
    private String bd;
    private Connection connection;
    private ResultSet result;

    public Database(String bd, String userName, String passWord) {
        this.bd = bd;
        this.connection = null;
        this.result = null;
        String url = "jdbc:mysql://localhost:3306/" + bd + "?user=" + userName
                + "&password=" + passWord + "&useSSL=false&useUnicode=true" +
                "&serverTimezone=UTC";
        System.out.println(url);
        authenticate(url);
    }

    public Database() {
    }

    private static void shutdown(String message) {
        System.err.println(message);
        System.exit(99);
    }

    public void authenticate(String url) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url);
            System.out.println("Connected !");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Run sql script file.
     *
     * @param filename filename
     * @throws FileNotFoundException
     */
    public void importSqlFile(String filename) throws FileNotFoundException {
        if (filename == null || filename.isEmpty())
            throw new FileNotFoundException();
        File file = new File(filename);
        Scanner reader = new Scanner(file);
        String str = "";
        while (reader.hasNext()) {
            str += reader.nextLine();
        }
        String[] queries = str.split("[;]");
        for (String query : queries) {
            try {
                Statement statement = connection.createStatement();
                statement.execute(query.replace("[\\t\\n\\r]", " "));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                break;
            }
        }
    }

    public void insert(String nameOfTable, String value) {
        String request = "INSERT INTO " + nameOfTable + " VALUES (" + value + ")";
        try {
            Statement statement = connection.createStatement();
            statement.execute(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(String nameOfTable, String nameOfColumn, String newValue, String condition) {
        String request = "UPADATE " + nameOfTable + " SET " + nameOfColumn + "=" + newValue + " WHERE " + condition;
        try {
            Statement statement = connection.createStatement();
            statement.execute(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String nameOfTable, String condition) {
        String request = "DELETE FROM " + nameOfTable + " WHERE " + condition;
        try {
            Statement statement = connection.createStatement();
            statement.execute(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet select(String nameOfTable, String condition) {
        String request;
        if(condition.length() == 0){
            request = "SELECT * FROM " + nameOfTable;
        }
        else {
            request = "SELECT * FROM " + nameOfTable + "WHERE" + condition;
        }
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(request);
        } catch (SQLException e) {
            shutdown("Anomalie lors de l'execution de la requête");
        }
        return null;
    }
}