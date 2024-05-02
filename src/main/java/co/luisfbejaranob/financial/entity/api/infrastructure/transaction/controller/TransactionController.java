package co.luisfbejaranob.financial.entity.api.infrastructure.transaction.controller;

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
    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository)
    {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("{id}")
    public ResponseEntity<Transaction> findById(@PathVariable Long id)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(transactionRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<Transaction> create(@Valid @RequestBody Transaction transaction)
    {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(transactionRepository.create(transaction));
    }

    @PutMapping
    public ResponseEntity<Transaction> update(@Valid @RequestBody Transaction transaction)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(transactionRepository.update(transaction));
    }

    @ExceptionHandler(TransactionNotFound.class)
    public ResponseEntity<ErrorDto> handleTransactionNotFound(Exception ex)
    {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDto(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }
}
