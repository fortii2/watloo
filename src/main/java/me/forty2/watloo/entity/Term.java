package me.forty2.watloo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Term {
    @Id
    private String termCode;

    private String name;

    private LocalDateTime termBeginDate;

    private LocalDateTime termEndDate;
}
