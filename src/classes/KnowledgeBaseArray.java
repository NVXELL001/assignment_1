package classes;

import java.util.HashMap;
import java.util.Map;

import interfaces.IKnowledgeBase;

/**
 * @author NVXELL001
 * @version 1.0
 */

public class KnowledgeBaseArray implements IKnowledgeBase {
    private Statement[] statements;

    public KnowledgeBaseArray() {
        this(new Statement[0]);
    }

    public KnowledgeBaseArray(Statement[] statements) {
        SetStatements(statements);
    }

    /**
     * This method is used to set the statement for the KnowledgeBase instance.
     * 
     * @param statements the statements for the KnowledgeBase.
     */
    public void SetStatements(Statement[] statements) {
        this.statements = statements;
    }

    /**
     * This method is used to get a statement based off the term supplied.
     * 
     * @param term the term to search.
     */
    public Statement getStatementByTerm(String term) {
        int statementIndex = getIndexByTerm(term);
        if (statementIndex < 0) {
            return null;
        }
        return statements[statementIndex];
    }

    /**
     * This method is used to get a statement based off the term and sentence
     * supplied.
     * 
     * @param term     the term of the statement.
     * @param sentence the sentence of the statement.
     */
    public Statement searchStatementByTermAndSentence(String term, String sentence) {
        Statement statement = getStatementByTerm(term);
        if (statement != null && statement.getSentence().equals(sentence)) {
            return statement;
        }
        return null;
    }

    /**
     * This method is used to update a statement in the KnowledgeBase.
     * the statement will only be updated if the cofidence score is greater than the
     * current confidence score.
     * 
     * @param term            the term to identify the statement.
     * @param sentence        the new sentence for the statement.
     * @param confidenceScore the confidence score of the new sentence.
     */
    public void updateStatement(String term, String sentence, double confidenceScore) {
        int statementIndex = getIndexByTerm(term);
        if (statementIndex < 0) {
            return;
        }
        double currentConfidenceScore = statements[statementIndex].getConfidenceScore();
        if (confidenceScore < currentConfidenceScore) {
            System.out.println("Confidence score is less than current statement in knowledge base");
            return;
        }
        statements[statementIndex].update(sentence, confidenceScore);
        System.out.println("Statement updated");
    }

    /**
     * Tries to get the index of the statement based off term.
     * 
     * @param term the term to search for.
     * @return if the statement is found, this will return the index, otherwise will
     *         return -1.
     */
    private int getIndexByTerm(String term) {
        term = term.toLowerCase();
        for (int i = 0; i < statements.length; i++) {
            String statementTerm = statements[i].getTerm().toLowerCase();
            String[] statementWords = statementTerm.split("\s");

            if (statementTerm.equals(term)) {
                return i;
            }
            for (String word : statementWords) {
                if (word.equals(term)) {
                    return i;
                }
            }
        }
        System.out.println("Statement could not be found for term: " + term);
        return -1;
    }
}
