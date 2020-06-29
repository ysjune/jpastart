package jpastart.common.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SightDetail {

    @Column(name = "hours_op")
    private String hoursOfOperation;
    private String holidays;
    private String facilities;
}
