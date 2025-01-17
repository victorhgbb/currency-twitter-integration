package br.com.victorhgbb.client;

import br.com.victorhgbb.model.Currency;
import br.com.victorhgbb.model.CurrencyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "AwesomeClient", url = "https://economia.awesomeapi.com.br/last")
public interface AwesomeClient {

    @RequestMapping(method = RequestMethod.GET, value = "/:currency")
    CurrencyResponse getCurrency(@PathVariable("currency") String currency);
}
