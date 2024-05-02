package co.luisfbejaranob.financial.entity.api.shared.infrastructure.exception.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

public class ErrorDto
{
    private final Integer status;

    private final String message;

    private List<String> errors;

    private final Date date;

    public ErrorDto(Integer status, String message)
    {
        this.status = status;
        this.message = message;
        this.date = new Date();
    }

    public ErrorDto(Integer status, List<String> errors)
    {
        this.status = status;
        this.message = null;
        this.errors = errors;
        this.date = new Date();
    }

    public Integer getStatus()
    {
        return status;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getMessage()
    {
        return message;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<String> getErrors()
    {
        return errors;
    }

    public Date getDate()
    {
        return date;
    }

}
