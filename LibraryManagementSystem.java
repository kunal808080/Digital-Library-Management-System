import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagementSystem {
    private ArrayList<Book> books;
    private Scanner scanner;

    public LibraryManagementSystem() {
        books = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewAllBooks();
                    break;
                case 3:
                    searchBook();
                    break;
                case 4:
                    updateBook();
                    break;
                case 5:
                    deleteBook();
                    break;
                case 6:
                    System.out.println("Exiting system...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\nDigital Library Management System");
        System.out.println("1. Add a Book");
        System.out.println("2. View All Books");
        System.out.println("3. Search Book by ID or Title");
        System.out.println("4. Update Book Details");
        System.out.println("5. Delete a Book Record");
        System.out.println("6. Exit System");
        System.out.print("Enter your choice: ");
    }

    private void addBook() {
        System.out.println("\nAdd a New Book");

        System.out.print("Enter Book ID: ");
        String id = scanner.nextLine();

        // Check if ID already exists
        if (findBookById(id) != null) {
            System.out.println("Error: Book ID already exists.");
            return;
        }

        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        if (title.isEmpty()) {
            System.out.println("Error: Title cannot be empty.");
            return;
        }

        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        if (author.isEmpty()) {
            System.out.println("Error: Author cannot be empty.");
            return;
        }

        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine();

        Book newBook = new Book(id, title, author, genre);
        books.add(newBook);
        System.out.println("Book added successfully!");
    }

    private void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }

        System.out.println("\nList of All Books:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private void searchBook() {
        System.out.println("\nSearch Book");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Title");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 1) {
            System.out.print("Enter Book ID: ");
            String id = scanner.nextLine();
            Book book = findBookById(id);
            if (book != null) {
                System.out.println("Book found:");
                System.out.println(book);
            } else {
                System.out.println("Book not found with ID: " + id);
            }
        } else if (choice == 2) {
            System.out.print("Enter Book Title: ");
            String title = scanner.nextLine();
            ArrayList<Book> foundBooks = findBooksByTitle(title);
            if (!foundBooks.isEmpty()) {
                System.out.println("Found " + foundBooks.size() + " book(s):");
                for (Book book : foundBooks) {
                    System.out.println(book);
                }
            } else {
                System.out.println("No books found with title: " + title);
            }
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private void updateBook() {
        System.out.println("\nUpdate Book Details");
        System.out.print("Enter Book ID to update: ");
        String id = scanner.nextLine();

        Book book = findBookById(id);
        if (book == null) {
            System.out.println("Book not found with ID: " + id);
            return;
        }

        System.out.println("Current details:");
        System.out.println(book);

        System.out.println("\nWhat would you like to update?");
        System.out.println("1. Title");
        System.out.println("2. Author");
        System.out.println("3. Genre");
        System.out.println("4. Availability Status");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter new Title: ");
                String newTitle = scanner.nextLine();
                if (!newTitle.isEmpty()) {
                    book.setTitle(newTitle);
                    System.out.println("Title updated successfully.");
                } else {
                    System.out.println("Error: Title cannot be empty.");
                }
                break;
            case 2:
                System.out.print("Enter new Author: ");
                String newAuthor = scanner.nextLine();
                if (!newAuthor.isEmpty()) {
                    book.setAuthor(newAuthor);
                    System.out.println("Author updated successfully.");
                } else {
                    System.out.println("Error: Author cannot be empty.");
                }
                break;
            case 3:
                System.out.print("Enter new Genre: ");
                String newGenre = scanner.nextLine();
                book.setGenre(newGenre);
                System.out.println("Genre updated successfully.");
                break;
            case 4:
                System.out.print("Enter new Status (Available/Checked Out): ");
                String newStatus = scanner.nextLine();
                if (newStatus.equals("Available") || newStatus.equals("Checked Out")) {
                    book.setAvailabilityStatus(newStatus);
                    System.out.println("Status updated successfully.");
                } else {
                    System.out.println("Error: Status must be 'Available' or 'Checked Out'.");
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void deleteBook() {
        System.out.println("\nDelete a Book");
        System.out.print("Enter Book ID to delete: ");
        String id = scanner.nextLine();

        Book book = findBookById(id);
        if (book == null) {
            System.out.println("Book not found with ID: " + id);
            return;
        }

        books.remove(book);
        System.out.println("Book deleted successfully.");
    }

    // Helper methods
    private Book findBookById(String id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    private ArrayList<Book> findBooksByTitle(String title) {
        ArrayList<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }
}