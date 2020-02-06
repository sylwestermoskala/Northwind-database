package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "shippers")
public class Shipper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ShipperID", unique = true, nullable = false, length = 11)
    private Integer id;

    @Column(name = "CompanyName", length = 40)
    private String companyName;

    @Column(name = "Phone", length = 24)
    private String phone;
}
