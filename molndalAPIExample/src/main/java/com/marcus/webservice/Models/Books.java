package com.marcus.webservice.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JoinColumn(name = "author_id")  // Detta kopplar boken till författaren
    @JsonBackReference  // Förhindrar oändlig loop vid serialisering
    private Author author;
}
