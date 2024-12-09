package com.rr.ms.user.infrastructure.repositories.entities;

import com.rr.ms.user.domain.UserDomain;
import com.rr.ms.user.domain.enums.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.util.Optional;

@Entity(name = "tb_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "first_name")
    private String firstName;
    @Column(nullable = false, name = "last_name")
    private String lastName;
//    @CPF(message = "Coloque um cpf válido")
    private String cpf;
    @Column(nullable = false)
//    @Email(regexp = ".+[@].+[\\.].+", message = "Coloque um email válido")
    private String email;
    @Column(nullable = false)
    private BigDecimal balance;
    private String phone;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "user_type")
    private UserType userType;

    public static UserEntity fromDomain(UserDomain domain) {
        UserEntity entity = new UserEntity();

        entity.setId(domain.id());
        entity.setFirstName(domain.firstName());
        entity.setLastName(domain.lastName());
        entity.setCpf(domain.cpf());
        entity.setEmail(domain.email());
        entity.setBalance(domain.balance());
        entity.setPhone(domain.phone().orElse(null));
        entity.setUserType(domain.userType());

        return entity;
    }

    public UserDomain toDomain() {
        return new UserDomain(
                getId(),
                getFirstName(),
                getLastName(),
                getCpf(),
                getEmail(),
                getBalance(),
                Optional.ofNullable(getPhone()),
                getUserType()
        );
    }
}
