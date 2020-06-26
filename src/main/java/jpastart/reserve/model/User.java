package jpastart.reserve.model;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Entity
@Table(name = "user")
public class User {

    @Id
    private String email;
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createdDate;

    protected User() {

    }

    @Builder
    public User(final String email, final String name, final Date createdDate) {
        this.email = email;
        this.name = name;
        this.createdDate = createdDate;
    }

    public void changeName(String newName) {
        this.name = newName;
    }

}
