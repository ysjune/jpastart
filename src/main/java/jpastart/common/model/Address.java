package jpastart.common.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@Embeddable
@NoArgsConstructor
public class Address {
    private String zipcode;
    private String address1;
    private String address2;

    public Address(final String zipcode, final String address1, final String address2) {
        this.zipcode = zipcode;
        this.address1 = address1;
        this.address2 = address2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipcode, address1, address2);
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Address address = (Address) obj;
        return Objects.equals(zipcode, address.zipcode) &&
                Objects.equals(address1, address.address1) &&
                Objects.equals(address2, address.address2);
    }
}
