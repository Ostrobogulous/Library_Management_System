import java.util.function.Consumer;

public enum AdminAction {
    ADD_BOOK("Add New Book", "Add a new book to the library.", LibraryManagementSystem::addBook),
    ADD_BOOK_QUANTITY("Add Book Quantity", "Add copies of an existing book to the library.", LibraryManagementSystem::addBookQuantity),
    REMOVE_BOOK("Remove Book", "Remove a book from the library.", LibraryManagementSystem::removeBook),
    VIEW_BOOKS("View Books", "View all books in the library.", LibraryManagementSystem::viewBooks),
    ADD_AUTHOR_TALK("Add Author Talk", "Program an author talk.", LibraryManagementSystem::addAuthorTalk),
    VIEW_UPCOMING_AUTHOR_TALKS("View Upcoming Author Talks", "View future author talks programmed in the library.", LibraryManagementSystem::viewUpcomingAuthorTalks),
    MODIFY_AUTHOR_TALK("Modify Author Talk", "Modify an author talk.", LibraryManagementSystem::modifyAuthorTalk),
    LOGOUT("Logout", "Logout from admin panel.", LibraryManagementSystem::logout);

    private final String action;
    private final String description;
    private final Consumer<LibraryManagementSystem> associatedFunction;


    AdminAction(String action, String description, Consumer<LibraryManagementSystem> associatedFunction) {
        this.action = action;
        this.description = description;
        this.associatedFunction = associatedFunction;
    }

    public static boolean isValidAction(int option) {
        return option > 0 && option <= CustomerAction.values().length;
    }

    public String getAction() {
        return action;
    }

    public String getDescription() {
        return description;
    }

    public Consumer<LibraryManagementSystem> getAssociatedFunction() {
        return associatedFunction;
    }

}