import java.util.function.Consumer;


public enum CustomerAction {
    SUBSCRIBE("Subscribe", "Subscribe to a membership.", LibraryManagementSystem::subscribe),
    UNSUBSCRIBE("Unsubscribe", "Unsubscribe from the membership.", LibraryManagementSystem::unsubscribe),
    VIEW_SUBSCRIPTION_DETAILS("View Subscription Details", "Get details about subscription.", LibraryManagementSystem::viewSubscriptionDetails),
    BORROW_BOOK("Borrow Book", "Borrow a book from the library using subscription.", LibraryManagementSystem::borrowBook),
    RETURN_BOOK("Return Book", "Return a borrowed book to the library using subscription.", LibraryManagementSystem::returnBook),
    VIEW_ALL_AVAILABLE_BOOKS("View All Available Books", "View all available books in the library.", LibraryManagementSystem::viewAvailableBooks),
    VIEW_AVAILABLE_BOOKS_BY_CATEGORY("View Available Books by Category", "View available books in the library by category.", LibraryManagementSystem::viewAvailableBooksByCategory),
    VIEW_BORROWED_BOOKS("View Borrowed Books", "View all borrowed books using subscription.", LibraryManagementSystem::viewBorrowedBooks),
    VIEW_UPCOMING_AUTHOR_TALKS("View Upcoming Author Talks", "View future author talks programmed in the library.", LibraryManagementSystem::viewUpcomingAuthorTalks),
    GO_BACK("Go Back", "Go back to the main menu.", LibraryManagementSystem::goBack);

    private final String action;
    private final String description;
    private final Consumer<LibraryManagementSystem> associatedFunction;

    CustomerAction(String action, String description, Consumer<LibraryManagementSystem> associatedFunction) {
        this.action = action;
        this.description = description;
        this.associatedFunction = associatedFunction;
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

    public static boolean isValidAction(int option) {
        return option > 0 && option <= CustomerAction.values().length;
    }
}
