import java.io.*;
import java.util.Scanner;

public class NotesApp {
    private static final String FILE_PATH = "data/notes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n📒 Notes App");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Clear Notes");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addNote(scanner);
                case 2 -> viewNotes();
                case 3 -> clearNotes();
                case 4 -> {
                    running = false;
                    System.out.println("👋 Exiting Notes App...");
                }
                default -> System.out.println("❌ Invalid option!");
            }
        }

        scanner.close();
    }

    private static void addNote(Scanner scanner) {
        System.out.print("Enter your note: ");
        String note = scanner.nextLine();

        try (FileWriter fw = new FileWriter(FILE_PATH, true)) {
            fw.write(note + System.lineSeparator());
            System.out.println("✅ Note added.");
        } catch (IOException e) {
            System.out.println("⚠️ Error writing note: " + e.getMessage());
        }
    }

    private static void viewNotes() {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            System.out.println("📭 No notes found.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            System.out.println("\n📃 Your Notes:");
            while ((line = br.readLine()) != null) {
                System.out.println("- " + line);
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error reading notes: " + e.getMessage());
        }
    }

    private static void clearNotes() {
        try (FileWriter fw = new FileWriter(FILE_PATH)) {
            fw.write("");
            System.out.println("🧹 Notes cleared.");
        } catch (IOException e) {
            System.out.println("⚠️ Error clearing notes: " + e.getMessage());
        }
    }
}
