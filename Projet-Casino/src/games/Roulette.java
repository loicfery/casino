package games;

import java.util.Random;

public class Roulette {

    public int RandomNumber(){
        Random random = new Random();
        int number;
        number = random.nextInt(37);
        return number;
    }

    /** une méthode qui à partir d'une liste de case et de la valeur d'une mise
     * ajoute cette mise dans une liste de mise en attribut **/


    /** une méthode qui prend en paramètre une liste de case d'une mise, et la case choisit aléatoireemnt
     * et retourne le gain de cette mise (0 si aucun gain)  **/

    /** une méthode qui calcule tous les gains et retourne le gain final **/

    /** une méthode qui reset la liste des mises pour recommencer une nouvelle partie **/

}
