package br.com.victorhgbb.model;

import java.math.BigDecimal;

public record TransacaoCnab(
        Integer tipo,
        String data,
        BigDecimal valor,
        String cpf,
        String cartao,
        String hora,
        String donoDaLoja,
        String nomeDaLoja) {
}
