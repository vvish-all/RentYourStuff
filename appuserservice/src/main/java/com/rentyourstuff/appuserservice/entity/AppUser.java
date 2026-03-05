package com.rentyourstuff.appuserservice.entity;

import com.rentyourstuff.appuserservice.enums.UserRoles;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
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
                @Index(name = "idx_appuser_username", columnList = "username")
        }
)
public class AppUser extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;
    @Column(name ="username", unique = true, nullable = false)
    private String userName;
    @Column(name ="password",nullable = false)
    private String password;
    private String email;
    @Column(name ="name",nullable = false)
    private String name;
    private String phoneNumber;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles")
    private List<UserRoles> roles = List.of(UserRoles.USER);//default user
}
