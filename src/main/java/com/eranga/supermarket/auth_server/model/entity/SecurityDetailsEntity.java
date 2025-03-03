package com.eranga.supermarket.auth_server.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class SecurityDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "security_details_id_seq")
    @SequenceGenerator(name = "security_details_id_seq", sequenceName = "security_details_id_seq", allocationSize = 1)
    private Integer id;
    @Column(name = "key", nullable = false, unique = false)
    private String key;
}
