package entity;

import javax.persistence.*;

@Entity
@Table(name = "readings")
@PrimaryKeyJoinColumn(name = "product_id")
public class Reading extends Product {
    @Column(name = "reading_name")
    private String readingName;

    @Column(name = "author")
    private String author;

    @Column(name = "publisher")
    private String publisher;

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
    public String toString() {
        return "Reading{" +
                "id='" + super.getId() + '\'' +
                ", readingName='" + readingName + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", price='" + super.getPrice() + '\'' +
                " ,product number='" + super.getProductNumber() +
                '}';
    }
}
