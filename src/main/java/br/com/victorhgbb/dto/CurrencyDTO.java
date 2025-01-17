package br.com.victorhgbb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDTO {
    private String formattedName;
    private String formattedValue;
    private String formattedDate;

    @Override
    public String toString() {
        return "Cotação:" +
                " " + formattedName + " "
                + formattedValue + " " +
                formattedDate;
    }
}
