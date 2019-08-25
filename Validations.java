
// Incomplete
// To be finished the cross-class constraint / validation class
import javax.validation.*;
import javax.validation.constraints.*;
import java.lang.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

class Book {

    @NotEmpty(message = "Book title must not be empty")
    private String title;

    @Size(min = 1)
    private List<Chapter> chapters;

    @Size(min = 1, max = 3)
    private List<@NotNull String> authors;

    private String isbn;

    @NotNull(message = "firstEditionPublishDate should not be null")
    private LocalDate firstEditionPublishDate;

    @NotNull(message = "lastEditionPublishDate should not be null")
    private LocalDate lastEditionPublishDate;

    private Integer numberOfPages;

    Book(String title, List<Chapter> chapters, List<String> authors, String isbn, LocalDate firstEditionPublishDate,
            LocalDate lastEditionPublishDate, Integer numberOfPages) {
        this.title = title;
        this.chapters = chapters;
        this.authors = authors;
        this.isbn = isbn;
        this.firstEditionPublishDate = firstEditionPublishDate;
        this.lastEditionPublishDate = lastEditionPublishDate;
        this.numberOfPages = numberOfPages;
    }

    String getTitle() {
        return title;
    }

    List<Chapter> getChapters() {
        return chapters;
    }

    List<String> getAuthors() {
        return authors;
    }

    String getIsbn() {
        return isbn;
    }

    LocalDate getFirstEditionPublishDate() {
        return firstEditionPublishDate;
    }

    LocalDate getLastEditionPublishDate() {
        return lastEditionPublishDate;
    }

    Integer getNumberOfPages() {
        return numberOfPages;
    }
}

class Chapter {

    @NotEmpty(message = "Chapter title must not be empty")
    private String title;

    @Size(min = 1)
    private Integer numberOfPages;

    Chapter(String title, Integer numberOfPages) {
        this.title = title;
        this.numberOfPages = numberOfPages;
    }

    public String toString() {
        return "Chapter{" + "title='" + title + '\'' + ", numberOfPages=" + numberOfPages + '}';
    }

    String getTitle() {
        return title;
    }

    Integer getNumberOfPages() {
        return numberOfPages;
    }
}