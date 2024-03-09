import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.stream.Stream;

import classes.KnowledgeBaseArray;
import classes.Menu;
import classes.Statement;

public class GenericsKbArrayApp {
    private static KnowledgeBaseArray KnowledgeBase;

    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        Menu menu = new Menu(scanner, false);
        while (running) {
            int choice = menu.getChoice();
            switch (choice) {
                case 1:
                    loadKnowledgeBaseFromFile();
                    break;
                case 2:
                    updateStatement(scanner);
                    break;
                case 3:
                    searchByTerm(scanner);
                    break;
                case 4:
                    searchByTermAndSentence(scanner);
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void loadKnowledgeBaseFromFile() {
        String path = "bin/data/GenericsKB.txt";
        int arraySize = 50000;
        Statement[] statements = new Statement[arraySize];
        int statementIndex = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 3) {
                    String term = parts[0];
                    String sentence = parts[1];
                    double confidenceScore = Double.parseDouble(parts[2]);
                    statements[statementIndex] = new Statement(term, sentence, confidenceScore);
                    statementIndex++;
                }
            }
            KnowledgeBase = new KnowledgeBaseArray(statements);
            System.out.println("Knowledge base loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading knowledge base: " + e.getMessage());
        }
    }

    private static void addStatement(KnowledgeBaseArray knowledgeBase, Scanner scanner) {
        System.out.print("Enter the term: ");
        String term = scanner.nextLine();
        System.out.print("Enter the statement: ");
        String sentence = scanner.nextLine();
        System.out.print("Enter the confidence score: ");
        double confidenceScore = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        knowledgeBase.updateStatement(term, sentence, confidenceScore);
        System.out.println("Statement for term " + term + " has been updated.");
    }

    private static void updateStatement(Scanner scanner) {
        if (KnowledgeBase == null) {
            System.out.println("Please load Knowlegde Base first");
            return;
        }
        System.out.print("Enter the term: ");
        String term = scanner.nextLine();
        System.out.print("Enter the statement: ");
        String sentence = scanner.nextLine();
        boolean validConfidence = false;
        double confidenceScore = 0.0;
        while (!validConfidence) {
            try {
                System.out.print("Enter the confidence score: ");
                confidenceScore = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                validConfidence = true;
            } catch (Exception e) {
                System.out.println("Please enter valid number");
                scanner.nextLine(); // Consume newline}
            }
        }

        
        KnowledgeBase.updateStatement(term, sentence, confidenceScore);
        System.out.println("Statement for term " + term + " has been updated.");
    }

    private static void searchByTerm(Scanner scanner) {
        if (KnowledgeBase == null) {
            System.out.println("Please load Knowlegde Base first");
            return;
        }
        System.out.print("Enter the term to search: ");
        String term = scanner.nextLine();
        Statement statement = KnowledgeBase.getStatementByTerm(term);
        if (statement != null) {
            System.out.println("Statement found: " + statement.getSentence() +
                    " (Confidence score: " + statement.getConfidenceScore() + ")");
        } else {
            System.out.println("Statement not found for term " + term);
        }
    }

    private static void searchByTermAndSentence(Scanner scanner) {
        if (KnowledgeBase == null) {
            System.out.println("Please load Knowlegde Base first");
            return;
        }
        System.out.print("Enter the term: ");
        String term = scanner.nextLine();
        System.out.print("Enter the statement to search for: ");
        String sentence = scanner.nextLine();
        Statement statement = KnowledgeBase.searchStatementByTermAndSentence(term, sentence);
        if (statement != null) {
            System.out.println(
                    "The statement was found and has a confidence score of " + statement.getConfidenceScore() + ".");
        } else {
            System.out.println("The statement was not found.");
        }
    }
}
