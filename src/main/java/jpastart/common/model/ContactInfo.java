package jpastart.common.model;

import javax.persistence.*;

@Embeddable
public class ContactInfo {

    @Column(name = "ct_phone")
    private String phone;
    @Column(name = "ct_email")
    private String email;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "zipcode", column = @Column(name = "ct_zip")),
            @AttributeOverride(name = "address1", column = @Column(name = "ct_addr1")),
            @AttributeOverride(name = "address2", column = @Column(name = "ct_addr2"))
    })
    private Address address; // @Embeddable 에 @Embeddable 중첩

}
