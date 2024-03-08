package classes;

import java.util.HashMap;
import java.util.Map;

import interfaces.IKnowledgeBase;

public class KnowledgeBaseArray implements IKnowledgeBase {
private Map<String, Statement> statements;

    public KnowledgeBaseArray() {
        statements = new HashMap<>();
    }

    public void addOrUpdateStatement(String term, String sentence, double confidenceScore) {
        Statement existingStatement = statements.get(term);
        if (existingStatement == null || confidenceScore > existingStatement.getConfidenceScore()) {
            statements.put(term, new Statement(term, sentence, confidenceScore));
        }
    }

    public Statement getStatementByTerm(String term) {
        return statements.get(term);
    }

    public Statement searchStatementByTermAndSentence(String term, String sentence) {
        Statement statement = statements.get(term);
        if (statement != null && statement.getSentence().equals(sentence)) {
            return statement;
        }
        return null;
    }
}
