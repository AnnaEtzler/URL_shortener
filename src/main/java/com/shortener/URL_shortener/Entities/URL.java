package com.shortener.URL_shortener.Entities;

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

    @Column(name = "short_url")
    private String short_url;
    @Column(name = "long_url")
    private String long_url;

    @NotBlank(message = "Date is mandatory")
    private Date date_created;
}
