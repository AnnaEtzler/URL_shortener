package com.shortener.URL.shortener.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class URL {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "shortUrl")
    @NotBlank
    private String shortUrl;
    @Column(name = "longUrl")
    private String longUrl;

}
