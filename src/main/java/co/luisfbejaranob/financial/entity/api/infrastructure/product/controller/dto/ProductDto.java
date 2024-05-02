package co.luisfbejaranob.financial.entity.api.infrastructure.product.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class ProductDto implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Long id;

    private String accountType;

    private String accountNumber;

    private Double balance;

    private Boolean gmfExempt;
}
