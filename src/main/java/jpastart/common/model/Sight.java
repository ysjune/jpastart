package jpastart.common.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@SecondaryTable(
        name = "sight_detail",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "sight_id", referencedColumnName = "id")
)
@Getter
@Setter
@NoArgsConstructor
public class Sight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Embedded
    @AttributeOverride(name = "zipcode", column = @Column(name = "kor_zipcode"))
    private Address korAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "zipcode", column = @Column(name = "eng_zipcode")),
            @AttributeOverride(name = "address1", column = @Column(name = "eng_addr1")),
            @AttributeOverride(name = "address2", column = @Column(name = "eng_addr2"))
    })
    private Address engAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "hoursOfOperation", column = @Column(name = "hours_op", table = "sight_detail")),
            @AttributeOverride(name = "holidays", column = @Column(table = "sight_detail")),
            @AttributeOverride(name = "facilities", column = @Column(table = "sight_detail")),
    })
    private SightDetail sightDetail;

    public Sight(String name, Address korAddress, Address engAddress) {
        this.name = name;
        this.korAddress = korAddress;
        this.engAddress = engAddress;
    }
}
