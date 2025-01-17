package br.com.victorhgbb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyResponse {

    @JsonProperty("USDBRL")
    private Currency usdbrl;

    @JsonProperty("EURBRL")
    private Currency eurbrl;

    @JsonProperty("BTCBRL")
    private Currency btcbrl;

}
