package co.luisfbejaranob.financial.entity.api.infrastructure.product.controller;

import co.luisfbejaranob.financial.entity.api.application.usecase.ProductUseCase;
import co.luisfbejaranob.financial.entity.api.domain.product.Product;
import co.luisfbejaranob.financial.entity.api.domain.product.ProductErrors.ProductNotFound;
import co.luisfbejaranob.financial.entity.api.infrastructure.product.persistence.postgresql.ProductEntity;
import co.luisfbejaranob.financial.entity.api.shared.infrastructure.exception.dto.ErrorDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static co.luisfbejaranob.financial.entity.api.shared.mappers.ProductMappers.entityFromRaw;
import static co.luisfbejaranob.financial.entity.api.shared.mappers.ProductMappers.entityFromRawPayload;

@RestController
@RequestMapping("products")
public class ProductController
{
    private final ProductUseCase useCase;

    public ProductController(ProductUseCase useCase)
    {
        this.useCase = useCase;
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(useCase.findById(id));
    }

    @GetMapping("account-number/{number}")
    public ResponseEntity<Product> findByAccountNumber(@PathVariable String number)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(useCase.findByAccountNumber(number));
    }

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody ProductEntity product)
    {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(useCase.create(entityFromRawPayload(product)));
    }

    @PutMapping
    public ResponseEntity<Product> update(@Valid @RequestBody ProductEntity product)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(useCase.update(entityFromRaw(product)));
    }

    @PutMapping("{id}/cancelled")
    public ResponseEntity<Product> cancelled(@PathVariable Long id)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(useCase.cancelled(id));
    }

    @PutMapping("{id}/inactive")
    public ResponseEntity<Product> inactive(@PathVariable Long id)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(useCase.inactive(id));
    }

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<ErrorDto> handleProductNotFound(Exception ex)
    {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDto(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }
}
