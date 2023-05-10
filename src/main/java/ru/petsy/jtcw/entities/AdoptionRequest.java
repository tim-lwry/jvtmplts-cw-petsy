package ru.petsy.jtcw.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "adoption_request")
@Getter
@Setter
public class AdoptionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user", nullable = false)
    private Adopter adopter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_animal", nullable = false)
    private Animal animal;

}
