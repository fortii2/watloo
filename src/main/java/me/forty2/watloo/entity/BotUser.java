package me.forty2.watloo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.forty2.watloo.enums.UserState;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BotUser {
    @Id
    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String languageCode;

    private String boundTerm;

    @Enumerated(EnumType.STRING)
    private UserState userState;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createAt;
}
