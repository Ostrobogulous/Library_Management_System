# Library Management System Documentation
![library](https://github.com/Ostrobogulous/Library_Management_System/assets/73111142/4bd72bd4-db50-4233-a82e-3e59eb178f54)
## Overview
This Library Management System is designed to manage various operations of a library. It allows both customers and administrators to interact with the system for different purposes such as book management, subscription services and author talks.

## Users of the System

### Customers
Customers are the library members who can:

- *Subscribe*: Obtain a subscription with a unique UUID. There are three subscription tiers:
  - *Premium*: Offers extensive borrowing privileges.
  - *Normal*: Offers standard borrowing privileges.
  - *Basic*: Offers limited borrowing privileges.
- *Unsubscribe*: Cancel their current subscription.
- *View Subscription Details*: Check details of their current subscription.
- *Borrow Books*: Borrow books using their name or ISBN.
- *View Available Books*: See all books that are currently available for borrowing.
- *View Books by Category*: Browse books sorted by categories.
- *View Borrowed Books*: Check the books they have currently borrowed.
- *View Upcoming Author Talks*: Stay updated with upcoming events and talks by authors.

### Admins
Admins are responsible for the management of the library. They can:

- *Add New Book*: Introduce new books to the library's collection.
- *Add Book Quantity*: Increase the quantity of existing books.
- *Remove Book*: Remove books from the library.
- *View Books*: Check the list of all books in the library.
- *Add/Modify Author Talk*: Organize and manage author talks.

## System Components

1. *Library*: Represents the library and its operations.

2. *LibraryManagementSystem*: Main class to run the system.

3. *User*: General class representing a user of the system.

4. *Admin*: Manages administrative tasks in the library.

5. *Customer*: Represents a customer of the library.

6. *Subscription*: Represents a customer's subscription to the library.

7. *Tier*: Represents different levels of subscription tiers.

8. *SubscriptionManagement*: Manages subscription operations in the library.

9. *Book*: Represents a book in the library.

10. *Category*: Represents different categories of books.

11. *Author*: Represents the creator of a book.

12. *BookItem*: Represents a physical copy of a book in the library.

13. *Booking*: Represents a customer's book borrowing.

14. *BookingReceipt*: Represents a receipt for book borrowing.

15. *BookingHistory*: Maintains the borrowing history of books.

16. *AuthorTalk*: Represents events or talks given by authors.


## Interactions and Relationships

- *Admin and User*: Admin inherits from User.
- *Customer and User*: Customer inherits from User.
- The system allows the creation of AuthorTalk, Book, BookItem, Booking, BookingReceipt and Subscription objects.

## Usage and Operation

- The system is initiated through the *LibraryManagementSystem* class.
- Users interact with the system based on their role (customer or admin).
- **Initialization from CSV File**: The system incorporates an initial data setup where books and admins are instantiated from a CSV file.
- Each user action triggers specific operations within the library, such as book borrowing, returning, or subscription management.

## Diagram
![22](https://github.com/Ostrobogulous/Library_Management_System/assets/73111142/df1d9d57-87ed-42fb-a18c-777ae6515246)
