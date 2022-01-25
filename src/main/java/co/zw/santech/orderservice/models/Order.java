package co.zw.santech.orderservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order
        implements java.io.Serializable {
    private static final long serialVersionUID = 7523967970034938905L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    private String customerName;
    private String customerAddress;
    private String customerPhone;
    private String date;
    private Integer productId;
    private Integer quantity;
    private Long available;
    private String status;
    private BigDecimal price;
    private BigDecimal totalPrice;
}
