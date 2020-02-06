package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "order_details")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "odID", unique = true, nullable = false, length = 10)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderID")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductID")
    private Product product;

    @Column(name = "UnitPrice", precision = 1)
    private Float unitPrice;

    @Column(name = "Quantity", length = 6, columnDefinition = "SMALLINT")
    private Short quantity;

    @Column(name = "Discount", precision = 1)
    private Float discount;
}
