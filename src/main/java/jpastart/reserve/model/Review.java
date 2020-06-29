package jpastart.reserve.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "hotel_review")
@NoArgsConstructor
@Getter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hotel_id")
    private String hotelId;
    private int rate;
    private String comment;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "rating_date")
    private Date ratingDate;

    public Review(String hotelId, int rate, String comment) {
        this.hotelId = hotelId;
        this.rate = rate;
        this.comment = comment;
    }

}
