package interfaces;

import classes.Statement;

public interface IKnowledgeBase {
    public void updateStatement(String term, String sentence, double confidenceScore);
    public Statement getStatementByTerm(String term);
    public Statement searchStatementByTermAndSentence(String term, String sentence);

}
