import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean available;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Borrower {
    private String name;
    private List<Book> borrowedBooks;

    public Borrower(String name) {
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.setAvailable(false);
        System.out.println("Book '" + book.getTitle() + "' borrowed successfully by " + name);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.setAvailable(true);
        System.out.println("Book '" + book.getTitle() + "' returned successfully by " + name);
    }
}

public class LibraryManagementSystem {
    private List<Book> books;
    private List<Borrower> borrowers;

    public LibraryManagementSystem() {
        books = new ArrayList<>();
        borrowers = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addBorrower(Borrower borrower) {
        borrowers.add(borrower);
    }

    public void displayAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor());
            }
        }
    }

    public void displayBorrowedBooks(Borrower borrower) {
        System.out.println("Borrowed Books by " + borrower.getName() + ":");
        List<Book> borrowedBooks = borrower.getBorrowedBooks();
        if (borrowedBooks.isEmpty()) {
            System.out.println("No books borrowed.");
        } else {
            for (Book book : borrowedBooks) {
                System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor());
            }
        }
    }

    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();

        // Adding books
        library.addBook(new Book("Book 1", "Author 1"));
        library.addBook(new Book("Book 2", "Author 2"));
        library.addBook(new Book("Book 3", "Author 3"));

        // Adding borrowers
        library.addBorrower(new Borrower("Borrower 1"));
        library.addBorrower(new Borrower("Borrower 2"));

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Library Management System Menu:");
            System.out.println("1. Display Available Books");
            System.out.println("2. Display Borrowed Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    library.displayAvailableBooks();
                    break;
                case 2:
                    System.out.print("Enter borrower name: ");
                    String borrowerName = scanner.nextLine();
                    Borrower borrower = library.getBorrowerByName(borrowerName);
                    if (borrower != null) {
                        library.displayBorrowedBooks(borrower);
                    } else {
                        System.out.println("Borrower not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter borrower name: ");
                    borrowerName = scanner.nextLine();
                    borrower = library.getBorrowerByName(borrowerName);
                    if (borrower != null) {
                        System.out.print("Enter book title: ");
                        String bookTitle = scanner.nextLine();
                        Book book = library.getBookByTitle(bookTitle);
                        if (book != null && book.isAvailable()) {
                            borrower.borrowBook(book);
                        } else {
                            System.out.println("Book not available or not found.");
                        }
                    } else {
                        System.out.println("Borrower not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter borrower name: ");
                    borrowerName = scanner.nextLine();
                    borrower = library.getBorrowerByName(borrowerName);
                    if (borrower != null) {
                        System.out.print("Enter book title: ");
                        String bookTitle = scanner.nextLine();
                        Book book = library.getBookByTitle(bookTitle);
                        if (book != null && !book.isAvailable()) {
                            borrower.returnBook(book);
                        } else {
                            System.out.println("Book not borrowed or not found.");
                        }
                    } else {
                        System.out.println("Borrower not found.");
                    }
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
    }
    private Borrower getBorrowerByName(String name) {
        for (Borrower borrower : borrowers) {
            if (borrower.getName().equals(name)) {
                return borrower;
            }
        }
        return null;
    }

    private Book getBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }
}