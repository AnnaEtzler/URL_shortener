package com.shortener.URL.shortener.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "URL")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class URL {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "shortUrl")
    private String shortUrl;
    @Column(name = "longUrl")
    private String longUrl;

    @NotBlank(message = "Date is mandatory")
    private Date dateCreated;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "user")
    private User user;
}
