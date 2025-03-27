public class Book {
    private String id;
    private String title;
    private String author;
    private String genre;
    private String availabilityStatus;

    // Constructor
    public Book(String id, String title, String author, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availabilityStatus = "Available"; // Default status
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAvailabilityStatus(String status) {
        if (status.equals("Available") || status.equals("Checked Out")) {
            this.availabilityStatus = status;
        }
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Title: %s | Author: %s | Genre: %s | Status: %s",
                id, title, author, genre, availabilityStatus);
    }
}