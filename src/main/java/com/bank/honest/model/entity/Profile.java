package com.bank.honest.model.entity;

import com.bank.honest.model.dto.ProfileDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;

/**
 * Created by User on 2/10/2018.
 */

@Entity
@Table(name = "user_profile")
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter
@Getter
@EqualsAndHashCode
@ToString(exclude = "id")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_phone")
    @Email
    private String email;

    @Column(name = "user_firstname")
    private String firstName;

    @Column(name = "user_lastname")
    private String lastName;

    @OneToOne
    @JoinColumn(name = "user_id")
    private CustomUser customUser;

    public ProfileDTO toDTO() {
        return ProfileDTO.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }

    public static Profile fromDTO(ProfileDTO dto) {
        return Profile.builder()
                .email(dto.getEmail())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .build();
    }
}
