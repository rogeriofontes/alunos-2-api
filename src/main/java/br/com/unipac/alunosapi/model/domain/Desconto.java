package br.com.unipac.alunosapi.model.domain;

import java.math.BigDecimal;

public enum Desconto {
    FIES{
        @Override
        public BigDecimal calculaDesconto(BigDecimal valor) {
            return valor.multiply(BigDecimal.valueOf(0.80));
        }
    },
    FINANCIAMENTO_BANCO{
        @Override
        public BigDecimal calculaDesconto(BigDecimal valor) {
            return valor.multiply(BigDecimal.valueOf(0.60));
        }
    },
    FINANCIAMENTO_INTERNO{
        @Override
        public BigDecimal calculaDesconto(BigDecimal valor) {
            return valor.multiply(BigDecimal.valueOf(0.30));
        }
    },
    BOLSA_50{
        @Override
        public BigDecimal calculaDesconto(BigDecimal valor) {
            return valor.multiply(BigDecimal.valueOf(0.50));
        }
    },
    BOLSA_100{
        @Override
        public BigDecimal calculaDesconto(BigDecimal valor) {
            return valor.multiply(BigDecimal.valueOf(1.00));
        }
    };

    public abstract BigDecimal calculaDesconto(BigDecimal valor);

    public static Desconto getTipoDesconto(String tipo) {
        for (Desconto desconto: Desconto.values()) {
            if (tipo.equals(desconto.name())) {
                return desconto;
            }
        }

        return null;
    }
}
