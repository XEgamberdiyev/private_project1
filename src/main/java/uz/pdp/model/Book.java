package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "description", columnDefinition = " text")
    private String description;
    @Column(name = "is_active", columnDefinition = " boolean default true")
    private boolean isActive;
    @Column(name = "price", columnDefinition = " double precision default 1000")
    private double price;
    @Column(name = "image_path", columnDefinition = " text")
    private String imagePath;
    @Column(name = "image_name")
    private String imageName;
    @Column(name = "created_at", columnDefinition = " timestamp default now()")
    private Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
    @Column(name = "updated_at", columnDefinition = " timestamp default now()")
    private Timestamp updatedAt = Timestamp.valueOf(LocalDateTime.now());

//    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<Module> modules = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();
}
