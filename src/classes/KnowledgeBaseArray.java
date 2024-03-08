package classes;

import java.util.HashMap;
import java.util.Map;

import interfaces.IKnowledgeBase;

public class KnowledgeBaseArray implements IKnowledgeBase {
    private Statement[] statements;

    public KnowledgeBaseArray() {
        this(new Statement[0]);
    }

    public KnowledgeBaseArray(Statement[] statements) {
        SetStatements(statements);
    }

    public void SetStatements(Statement[] statements) {
        this.statements = statements;
    }

    public Statement getStatementByTerm(String term) {
        int statementIndex = getIndexByTerm(term);
        if (statementIndex < 0) {
            return null;
        }
        return statements[statementIndex];
    }

    public Statement searchStatementByTermAndSentence(String term, String sentence) {
        Statement statement = getStatementByTerm(term);
        if (statement != null && statement.getSentence().equals(sentence)) {
            return statement;
        }
        return null;
    }

    public void updateStatement(String term, String sentence, double confidenceScore) {
        int statementIndex = getIndexByTerm(term);
        if (statementIndex < 0) {
            return;
        }
        statements[statementIndex].update(sentence, confidenceScore);
        System.out.println("Statement updated");
    }

    private int getIndexByTerm(String term) {
        for (int i = 0; i < statements.length; i++) {
            String statementTerm=statements[i].getTerm();
            if (statementTerm.equals(term)) {
                return i;
            }
        }
            System.out.println("Statement could not be found for term: " + term);
        return -1;
    }
}
