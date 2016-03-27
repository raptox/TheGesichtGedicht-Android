package at.alextornoreanu.thegesichtgedicht.model;

/**
 * Created by alex on 28.03.16.
 */
public class Poem {
    private String title;
    private String text;
    private String author;

    public Poem() {
        title = "No Title";
        text = "No Text";
        author = "No Author";
    }

    public Poem(String title, String text, String author) {
        this.title = title;
        this.text = text;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return title + "\n" + text + "\n" + author;
    }
}
