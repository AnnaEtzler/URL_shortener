package com.shortener.URL.shortener.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Builder
public class URL {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "shortUrl")
    private String shortUrl;
    @Column(name = "longUrl")
    private String longUrl;
    @Column(name = "creation_date")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime creation_date;
    @Column(name = "expiration_date")
    private LocalDateTime expiration_date;
}