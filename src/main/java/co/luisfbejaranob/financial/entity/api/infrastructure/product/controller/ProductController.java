package co.luisfbejaranob.financial.entity.api.infrastructure.product.controller;

import co.luisfbejaranob.financial.entity.api.domain.product.Product;
import co.luisfbejaranob.financial.entity.api.domain.product.ProductErrors.ProductNotFound;
import co.luisfbejaranob.financial.entity.api.domain.product.ProductRepository;
import co.luisfbejaranob.financial.entity.api.shared.infrastructure.exception.dto.ErrorDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
public class ProductController
{
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productRepository.findById(id));
    }

    @GetMapping("account-number/{number}")
    public ResponseEntity<Product> findByAccountNumber(@PathVariable String number)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productRepository.findByAccountNumber(number));
    }

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product product)
    {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productRepository.create(product));
    }

    @PutMapping
    public ResponseEntity<Product> update(@Valid @RequestBody Product product)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productRepository.update(product));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<ErrorDto> handleProductNotFound(Exception ex)
    {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDto(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }
}
