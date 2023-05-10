package ru.petsy.jtcw.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int age;

    private String race;

    private String breed;

    private String eye_color;

    private String color;

    private String info;

    private String image_address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "animal")
    public List<AdoptionRequest> adoptionRequests = new ArrayList<>();
}
