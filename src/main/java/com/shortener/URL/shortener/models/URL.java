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
@Table(name = "urls")
public class URL {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "shortUrl")
    private String shortUrl;
    @Column(name = "longUrl")
    @NotBlank
    private String longUrl;

    public String generateShortUrl(String url) {
        // generating murmur3 based hash key as short URL
        String key = Hashing.murmur3_32_fixed().hashString(url, Charset.defaultCharset()).toString();
        return key;
    }

}
