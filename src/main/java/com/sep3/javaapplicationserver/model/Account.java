package com.sep3.javaapplicationserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @JsonIgnore
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "timestamp(0)")
    private LocalDateTime dateCreated;

    @JsonIgnore
    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "timestamp(0)")
    private LocalDateTime dateUpdated;

    public Account(String username, String password, LocalDateTime dateCreated, LocalDateTime dateUpdated) {
        this.username = username;
        this.password = password;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    public Account() {
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
