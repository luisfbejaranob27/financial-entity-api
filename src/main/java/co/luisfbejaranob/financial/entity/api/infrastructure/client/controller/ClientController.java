package co.luisfbejaranob.financial.entity.api.infrastructure.client.controller;

import co.luisfbejaranob.financial.entity.api.application.usecase.ClientUseCase;
import co.luisfbejaranob.financial.entity.api.domain.client.Client;
import co.luisfbejaranob.financial.entity.api.domain.client.ClientErrors.ClientNotFound;
import co.luisfbejaranob.financial.entity.api.domain.client.ClientErrors.ClientInsufficientAge;
import co.luisfbejaranob.financial.entity.api.shared.infrastructure.exception.dto.ErrorDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clients")
public class ClientController
{
    private final ClientUseCase useCase;

    public ClientController(ClientUseCase useCase)
    {
        this.useCase = useCase;
    }

    @GetMapping("{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(useCase.findById(id));
    }

    @GetMapping("identification-number/{number}")
    public ResponseEntity<Client> findByIdentificationNumber(@PathVariable String number)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(useCase.findByIdentificationNumber(number));
    }

    @PostMapping
    public ResponseEntity<Client> create(@Valid @RequestBody Client client)
    {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(useCase.create(client));
    }

    @PutMapping
    public ResponseEntity<Client> update(@Valid @RequestBody Client client)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(useCase.update(client));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        useCase.deleteById(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @ExceptionHandler(ClientNotFound.class)
    public ResponseEntity<ErrorDto> handleClientNotFound(Exception ex)
    {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDto(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler(ClientInsufficientAge.class)
    public ResponseEntity<ErrorDto> handleClientInsufficientAge(Exception ex)
    {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }
}
