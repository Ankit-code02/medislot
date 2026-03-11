package com.medislot.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String specialization;

    @Column(nullable = false)
    private String qualification;

    @Column(nullable = false)
    private int experienceYears;

    @Column(nullable = false)
    private String availableDays;

    @Column(nullable = false)
    private String availableTimeStart;

    @Column(nullable = false)
    private String availableTimeEnd;

    @Column(nullable = false)
    private double consultationFee;
}