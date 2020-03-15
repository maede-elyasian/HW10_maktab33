package dto;

import java.util.Objects;

public class Reading extends Product {
    private String readingName;
    private String author;
    private String publisher;

    public Reading() {
    }


    public Reading(String name, double price, int productNumber,String readingName, String author, String publisher) {
        super(name, price, productNumber);
        this.readingName = readingName;
        this.author = author;
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getReadingName() {
        return readingName;
    }

    public void setReadingName(String readingName) {
        this.readingName = readingName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Reading reading = (Reading) o;
        return Objects.equals(getAuthor(), reading.getAuthor()) &&
                Objects.equals(getPublisher(), reading.getPublisher());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getAuthor(), getPublisher());
    }

    @Override
    public String toString() {
        return super.toString() +
                "Reading{" +
                "author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}
