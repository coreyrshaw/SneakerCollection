package tiy.sneakers;

import javax.persistence.*;

@Entity
@Table(name = "sneakers")
public class Sneaker {
    @Id
    @GeneratedValue
    int id;

    @ManyToOne
    User user;

    @Column(nullable = false)
    String model;

    @Column(nullable = false)
    int modelNo;

    @Column(nullable = false)
    String color;

    @Column(nullable = false)
    int releaseYear;

    @Column(nullable = false)
    double price;

    @Column(nullable = false)
    boolean copped;

    @Column(nullable = false)
    String photoLink;

    public Sneaker() {
    }

    public Sneaker(String model, int modelNo, String color, int releaseYear, double price, String photoLink, boolean copped,User user) {
        this.model = model;
        this.modelNo = modelNo;
        this.color = color;
        this.releaseYear = releaseYear;
        this.price = price;
        this.photoLink = photoLink;
        this.copped = copped;
        this.user = user;
    }

}