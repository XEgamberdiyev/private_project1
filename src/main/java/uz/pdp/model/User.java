package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "Iltimos to'liq ismingizni kiriting.")
    @Size(min = 5, max = 100)
    private String fullName;

    @NotEmpty(message = "User name kiritishingiz shart.")
    private String email;

    @NotEmpty(message = "Iltimos parolingizni kiriting.")
    @Size(min = 8)
    private String password;

    @Column(name = "balance", columnDefinition = " double precision default 0")
    private double balance = 0;

    @Column(name = "role_id", columnDefinition = "int default 1")
    private Integer roleId = 1;

    @Column(name = "created_at", columnDefinition = " timestamp default now()")
    private Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
    @Column(name = "updated_at", columnDefinition = " timestamp default now()")
    private Timestamp updatedAt = Timestamp.valueOf(LocalDateTime.now());

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Book> books = new ArrayList<>();
}
