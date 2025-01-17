package br.com.victorhgbb.service;

import br.com.victorhgbb.client.AwesomeClient;
import br.com.victorhgbb.dto.CurrencyDTO;
import br.com.victorhgbb.model.Currency;
import br.com.victorhgbb.model.CurrencyResponse;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class CurrencyService {

    String[] currencys = { "USD-BRL", "EUR-BRL" , "BTC-BRL" };

    private final AwesomeClient awesomeClient;

    public CurrencyService(AwesomeClient awesomeClient){
        this.awesomeClient = awesomeClient;
    }

    public List<CurrencyDTO> getCurrency() {
        CurrencyResponse currency = awesomeClient.getCurrency(String.join(",", currencys));

        CurrencyDTO usdBrl = formatCurrency(currency.getUsdbrl());

        CurrencyDTO eurBrl = formatCurrency(currency.getEurbrl());

        CurrencyDTO btcBrl = formatCurrency(currency.getBtcbrl());

        return List.of(usdBrl, eurBrl, btcBrl);
    }

    private CurrencyDTO formatCurrency(Currency currency){
        String formattedName = currency.getName().replace("/", " para ");

        String formattedValue = String.format(" R$ %.2f BRL ", currency.getValue());

        String formattedDate = new SimpleDateFormat(" dd/MM/yyyy HH:mm").format(new Date());

        return new CurrencyDTO(formattedName, formattedValue, formattedDate);
    }
}
