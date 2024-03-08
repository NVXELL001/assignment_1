import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import classes.KnowledgeBase;
import classes.Statement;

public class GenericsKbArrayApp {
    public static void main(String[] args) {
        KnowledgeBase knowledgeBase = new KnowledgeBase();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Choose an action from the menu:");
            System.out.println("1. Load a knowledge base from a file");
            System.out.println("2. Add a new statement to the knowledge base");
            System.out.println("3. Search for an item in the knowledge base by term");
            System.out.println("4. Search for an item in the knowledge base by term and sentence");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    loadKnowledgeBaseFromFile(knowledgeBase);
                    break;
                case 2:
                    addStatement(knowledgeBase, scanner);
                    break;
                case 3:
                    searchByTerm(knowledgeBase, scanner);
                    break;
                case 4:
                    searchByTermAndSentence(knowledgeBase, scanner);
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

    private static void loadKnowledgeBaseFromFile(KnowledgeBase knowledgeBase) {
        try (BufferedReader reader = new BufferedReader(new FileReader("bin/data/GenericsKB.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 3) {
                    String term = parts[0];
                    String sentence = parts[1];
                    double confidenceScore = Double.parseDouble(parts[2]);
                    knowledgeBase.addOrUpdateStatement(term, sentence, confidenceScore);
                }
            }
            System.out.println("Knowledge base loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading knowledge base: " + e.getMessage());
        }
    }

    private static void addStatement(KnowledgeBase knowledgeBase, Scanner scanner) {
        System.out.print("Enter the term: ");
        String term = scanner.nextLine();
        System.out.print("Enter the statement: ");
        String sentence = scanner.nextLine();
        System.out.print("Enter the confidence score: ");
        double confidenceScore = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        knowledgeBase.addOrUpdateStatement(term, sentence, confidenceScore);
        System.out.println("Statement for term " + term + " has been updated.");
    }

    private static void searchByTerm(KnowledgeBase knowledgeBase, Scanner scanner) {
        System.out.print("Enter the term to search: ");
        String term = scanner.nextLine();
        Statement statement = knowledgeBase.getStatementByTerm(term);
        if (statement != null) {
            System.out.println("Statement found: " + statement.getSentence() +
                    " (Confidence score: " + statement.getConfidenceScore() + ")");
        } else {
            System.out.println("Statement not found for term " + term);
        }
    }

    private static void searchByTermAndSentence(KnowledgeBase knowledgeBase, Scanner scanner) {
        System.out.print("Enter the term: ");
        String term = scanner.nextLine();
        System.out.print("Enter the statement to search for: ");
        String sentence = scanner.nextLine();
        Statement statement = knowledgeBase.searchStatementByTermAndSentence(term, sentence);
        if (statement != null) {
            System.out.println("The statement was found and has a confidence score of " + statement.getConfidenceScore() + ".");
        } else {
            System.out.println("The statement was not found.");
        }
    }
}
