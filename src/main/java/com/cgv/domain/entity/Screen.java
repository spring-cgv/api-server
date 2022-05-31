package com.cgv.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "screens")
public class Screen {
    @Id
    @Column(name = "screen_id")
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
}
