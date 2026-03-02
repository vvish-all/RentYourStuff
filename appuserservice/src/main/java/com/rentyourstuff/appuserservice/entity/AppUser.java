package com.rentyourstuff.appuserservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "appuser",
        indexes = {
                @Index(name = "idx_appuser_username", columnList = "USERNAME")
        }
)
public class AppUser extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;
    @Column(name ="USERNAME", unique = true, nullable = false)
    private String userName;
    @Column(name ="PASSWORD",nullable = false)
    private String password;
    private String email;
    @Column(name ="NAME",nullable = false)
    private String name;
    private String phoneNumber;
}
