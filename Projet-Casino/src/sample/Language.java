package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Language {

    private File file;
    private List<String> listOfLine;
    private List<String> listOfObject;
    private String language;

    public Language(String language){
        this.language = language;
        getAllText(language);
    }

    private void getAllText(String fileName){
        listOfLine = new ArrayList<>();
        listOfObject = new ArrayList<>();

        try {
            file = new File(fileName);
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            String line = buffer.readLine();

            while(line != null){
                listOfObject.add(line);
                line = buffer.readLine();
                listOfLine.add(line);
                buffer.readLine();
                line = buffer.readLine();
            }
        }
        catch (Exception exception){
            System.err.println("Erreur file \"language_french\"");
        }
    }

    public String getLine(String object){
        if(listOfObject.contains(object)){
            return listOfLine.get(listOfObject.indexOf(object));
        }
        return "";
    }

    public String getLanguage(){ return language; }
}
