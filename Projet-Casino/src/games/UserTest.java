package games;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getPseudo() {
        String pseudo =  "TEST";
        Database database = new Database();
        User user = new User(pseudo, "test", "test", 0, 0, database);
        assertEquals(user.getPseudo(), pseudo);
    }

    @Test
    void getEmail() {
        String email =  "TEST";
        Database database = new Database();
        User user = new User("test", email, "test", 0, 0, database);
        assertEquals(user.getEmail(), email);
    }

    @Test
    void getToken() {
        int token =  10;
        Database database = new Database();
        User user = new User("test", "test", "test", token, 0, database);
        assertEquals(user.getToken(), token);
    }

    @Test
    void getMoney() {
        int money =  10;
        Database database = new Database();
        User user = new User("test", "test", "test", 0, money, database);
        assertEquals(user.getMoney(), money);
    }

    @Test
    void getRank() {
        String rank =  "TEST";
        Database database = new Database();
        User user = new User("test", "test", rank, 0, 0, database);
        assertEquals(user.getRank(), rank);
    }

    @Test
    void addToken() {
    }

    @Test
    void addMoney() {
    }

    @Test
    void removeToken() {
    }

    @Test
    void removeMoney() {
    }

    @Test
    void setPseudo() {
        User user = new User();
        String pseudo = "TEST";
        user.setPseudo(pseudo);
        assertEquals(user.getPseudo(), pseudo);
    }

    @Test
    void setEmail() {
        User user =  new User();
        String email = "TEST";
        user.setEmail(email);
        assertEquals(user.getEmail(), email);
    }
}