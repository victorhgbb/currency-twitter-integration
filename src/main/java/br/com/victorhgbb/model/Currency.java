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
public class Currency {
    @JsonProperty("name")
    private String name;
    @JsonProperty("bid")
    private double value;
    @JsonProperty("create_date")
    private String timestamp;


    @Override
    public String toString() {
        return "Currency{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
