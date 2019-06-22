package ld.springboot_rest_demo2;

public class Book {
    private Long bookdId;
    private String title;
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getBookdId() {
        return bookdId;
    }

    public void setBookdId(Long bookdId) {
        this.bookdId = bookdId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
