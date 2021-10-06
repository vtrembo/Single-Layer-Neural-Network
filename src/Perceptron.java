
import java.util.List;

public class Perceptron {
    double[] weights;
    double rate;
    String LangName;

    Perceptron(String LangName) {
        weights = new double[26];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = Math.random();
        }
        this.rate = 0.5;
        this.LangName = LangName;
    }

    double computeOutput(Language language) {
        double res = 0;
        Double[] xs = language.getPercentage().values().toArray(new Double[26]);
        for (int j = 0; j < xs.length; j++) {
            res += xs[j] * weights[j];
        }
        return res;
    }

    void deltaTraining(List<Language> languages) {
        while (true) {
            for (Language language : languages) {
                Double[] xs = language.getPercentage().values().toArray(new Double[26]);
                double o = computeOutput(language);
                double t = language.getName().equals(LangName) ? 1 : 0;
                if (t - o >= 0 && t - o <= 0.01) {
                    System.out.println(LangName + " trained!");
                    return;
                }
                for (int j = 0; j < weights.length; j++) {
                    weights[j] += rate * (t - o) * xs[j];
                }
            }
        }
    }
}