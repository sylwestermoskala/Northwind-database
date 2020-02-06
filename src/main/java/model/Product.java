package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID", unique = true, nullable = false, length = 11)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SupplierID")
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoryID")
    private Category category;

    @Column(name = "ProductName", length = 40)
    private String productName;

    @Column(name = "QuantityPerUnit", length = 20)
    private String quantity;

    @Column(name = "UnitPrice", precision = 1)
    private Float unitPrice;

    @Column(name = "UnitsInStock", length = 6, columnDefinition = "SMALLINT")
    private Short unitsInStock;

    @Column(name = "UnitsOnOrder", length = 6, columnDefinition = "SMALLINT")
    private Short unitsOnOrder;

    @Column(name = "ReorderLevel", length = 6, columnDefinition = "SMALLINT")
    private Short reorderLevel;

    @Column(name = "Discontinued", length = 1, columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean discontinued;
}
