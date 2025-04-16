package pharmacy.pharmacy.dto;

import lombok.Data;
import java.util.Date;
import java.util.UUID;

@Data
public class SupplyOrderResponseDTO {
    private UUID id;
    private String supplierName;
    private String branchName;
    private Date orderDate;
    private String status;

    public void setId(UUID id) {
    }

    public void setSupplierName(String name) {
            
    }

    public void setOrderDate(Date orderDate) {
    }
}
