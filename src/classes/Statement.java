package classes;

public class Statement {
    private String term;
    private String sentence;
    private double confidenceScore;

    public Statement(String term, String sentence, double confidenceScore) {
        this.term = term;
        this.sentence = sentence;
        this.confidenceScore = confidenceScore;
    }

    public String getTerm() {
        return term;
    }

    public String getSentence() {
        return sentence;
    }

    public double getConfidenceScore() {
        return confidenceScore;
    }

    public void update(String sentence, double confidenceScore) {
        this.sentence = sentence;
        this.confidenceScore = confidenceScore;
    }
}
