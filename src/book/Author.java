package book;

import java.util.HashMap;
import java.util.Map;

public class Author {
    private static final Map<String, Author> authorMap = new HashMap<>();

    private String name;

    private Author(String name) {
        this.name = name;
    }

    public static Author getAuthor(String name) {
        return authorMap.computeIfAbsent(name, Author::new);
    }

    public String getName() {
        return name;
    }

}