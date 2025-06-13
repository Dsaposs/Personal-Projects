package com.ttrpg.core.alien.repositories.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer characterId;

    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private String characterName;

    @Column(nullable = false)
    private Integer strength;

    @Column(nullable = false)
    private Integer wits;

    @Column(nullable = false)
    private Integer empathy;

    @Column(nullable = false)
    private Integer agility;
}