package com.marcus.webservice.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String ISBN;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("books")  // Ignorera "books"-fältet i författaren för att undvika cirkulär referens
    private Author author;
}
