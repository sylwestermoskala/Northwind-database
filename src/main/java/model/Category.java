package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryID", unique = true, nullable = false, length = 11)
    private Integer id;

    @Column(name = "CategoryName", length = 15)
    private String categoryName;

    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "Picture", length = 40)
    private String picture;
}
