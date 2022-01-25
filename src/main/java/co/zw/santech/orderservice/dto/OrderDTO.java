package co.zw.santech.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private String customerName;
    private String customerAddress;
    private String customerPhone;
    private String date;
    private Integer productId;
    private Integer quantity;
    private String status;
    private BigDecimal price;
    private BigDecimal totalPrice;
}
