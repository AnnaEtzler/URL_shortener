package com.shortener.URL.shortener.models;

import com.google.common.hash.Hashing;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.nio.charset.Charset;
import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor

@Builder

@Table(name = "urls")
public class URL {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "shortUrl")
    private String shortUrl;
    @Column(name = "longUrl", columnDefinition = "varchar(3000)")
    @NotBlank
    private String longUrl;
    @Column(name = "time")
    private Long millis = System.currentTimeMillis();





}

