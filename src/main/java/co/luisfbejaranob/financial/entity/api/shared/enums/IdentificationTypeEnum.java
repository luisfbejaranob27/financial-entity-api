package co.luisfbejaranob.financial.entity.api.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum IdentificationTypeEnum
{
    CC("Cédula Ciudadania"),
    CE("Cédula Extrangeria"),
    PA("Pasaporte");

    private final String name;
}
