package tiy.sneakers;

import javax.persistence.*;

/**
 * Created by Sulton on 5/22/2016.
 */


    @Entity
    @Table(name = "sneakers")
    public class Sneaker {
        @Id
        @GeneratedValue
       public int id;

        @Column(nullable = false)
       public String model;

        @Column(nullable = false)
        public Integer modelNo;

        @Column(nullable = false)
        public String color;


        @Column(nullable = false)
        public Integer releaseYear;

        @Column(nullable = false)
        public Double  price;

        @Column(nullable = false)
        public String  photoLink;

    @ManyToOne
    public User user;

        public Sneaker() {
        }

        public Sneaker(String model, Integer modelNo, String color, Integer releaseYear,Double price,String photoLink, User user) {
            this.model = model;
            this.modelNo = modelNo;
            this.color= color;
            this.releaseYear = releaseYear;
            this.price=price;
            this.photoLink = photoLink;
            this.user = user;
        }
    }



