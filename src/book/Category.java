package book;

public enum Category {

    BIOGRAPHY("Biography"),
    CRIME("Crime"),
    DRAMA("Drama"),
    ACTION("Action"),
    ADVENTURE("Adventure"),
    FICTION("Fiction"),
    HISTORY("History"),
    HORROR("Horror"),
    MYSTERY("Mystery"),
    FANTASY("Fantasy"),
    UNKNOWN("Unknown");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Category findCategory(String categoryName) {
        for (Category category : Category.values()) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                return category;
            }
        }
        return Category.UNKNOWN;
    }

}