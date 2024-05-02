package co.luisfbejaranob.financial.entity.api.infrastructure.transaction.controller;

import co.luisfbejaranob.financial.entity.api.application.usecase.TransactionUseCase;
import co.luisfbejaranob.financial.entity.api.domain.transaction.Transaction;
import co.luisfbejaranob.financial.entity.api.domain.transaction.TransactionErrors.TransactionNotFound;
import co.luisfbejaranob.financial.entity.api.domain.transaction.TransactionRepository;
import co.luisfbejaranob.financial.entity.api.shared.infrastructure.exception.dto.ErrorDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transactions")
public class TransactionController
{
    private final TransactionUseCase useCase;

    public TransactionController(TransactionUseCase useCase)
    {
        this.useCase = useCase;
    }

    @GetMapping("{id}")
    public ResponseEntity<Transaction> findById(@PathVariable Long id)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(useCase.findById(id));
    }

    @PostMapping
    public ResponseEntity<Transaction> create(@Valid @RequestBody Transaction transaction)
    {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(useCase.create(transaction));
    }

    @ExceptionHandler(TransactionNotFound.class)
    public ResponseEntity<ErrorDto> handleTransactionNotFound(Exception ex)
    {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDto(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }
}
