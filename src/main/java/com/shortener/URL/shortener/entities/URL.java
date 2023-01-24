package com.shortener.URL.shortener.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "URL")
@Data
@NoArgsConstructor
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
}
