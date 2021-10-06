import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<Language> languages = new ArrayList<>();
        BufferedReader reader;
        File LangFolder = new File("data/Languages");
        File[] allLanguages = LangFolder.listFiles();
        for(int i = 0; i < allLanguages.length; i++) {
                Language language = new Language(allLanguages[i].getName());
                ArrayList<String> data = new ArrayList<>();
                File LangDataFolder = new File("data/Languages/" + allLanguages[i].getName());
                File[] LangData = LangDataFolder.listFiles();
                //for (int j = 0; j < LangData.length; j++) {
                for (int j = 0; j < 1; j++) {
                    reader = new BufferedReader(new FileReader("data/Languages/" + allLanguages[i].getName() + "/" + LangData[j].getName()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        data.add(line.replaceAll("[^a-zA-z]",""));
                    }
                }
                language.setData(data);
                languages.add(language);
        }
        ArrayList<Perceptron> perceptrons = new ArrayList<>();
        long start = System.currentTimeMillis();
        languages.forEach(language -> {
            Perceptron perceptron = new Perceptron(language.getName());
            perceptron.deltaTraining(languages);
            perceptrons.add(perceptron);
        });
            long elapsedTime = System.currentTimeMillis() - start;
        System.out.println("Trained in: " + elapsedTime/1000F);

        GUI.perceptrons = perceptrons;
        GUI.launch(args);
    }
}
