import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Language {
    private String name;
    private ArrayList<String> data;

    Language(String langName) {name = langName;}

    Language() {
        this.data = new ArrayList<>();
    }

    void setData(ArrayList<String> data) {
        this.data = data;
    }

    Language addData(String data) {
        this.data.add(data);
        return this;
    }
    public String getName() {
        return name;
    }

    HashMap<Character, Double> getPercentage() {
        HashMap<Character, Double> result = new HashMap<>();
        StringBuilder data = new StringBuilder();
        this.data.forEach(data::append);
        ArrayList<Character> symbols = new ArrayList<>();
        for (char symbol : data.toString().toCharArray())
            symbols.add(symbol);
        for (char i = 97; i < 123; i++) {    // ASCII
            result.put(i, Collections.frequency(symbols, i) / (double) symbols.size());
        }
        return result;
    }
}
