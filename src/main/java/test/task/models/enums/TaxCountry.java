package test.task.models.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum TaxCountry {
    GERMANY("DE", 0.19, "DE\\d{9}"),
    ITALY("IT", 0.22, "IT\\d{11}"),
    GREECE("GR", 0.24, "GR\\d{9}"),
    FRANCE("FR", 0.20, "FR[A-Z]{2}\\d{9}");

    String value;
    Double sum;
    String pattern;
}
