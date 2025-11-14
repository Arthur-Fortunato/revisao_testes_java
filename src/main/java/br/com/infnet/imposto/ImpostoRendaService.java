package br.com.infnet.imposto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ImpostoRendaService {
    private static final BigDecimal L1 = new BigDecimal("2000.00");
    private static final BigDecimal L2 = new BigDecimal("3000.00");
    private static final BigDecimal L3 = new BigDecimal("5000.00");
    private static final BigDecimal L4 = new BigDecimal("10000.00");

    private static final BigDecimal A1 = BigDecimal.ZERO;
    private static final BigDecimal A2 = new BigDecimal("0.075");
    private static final BigDecimal A3 = new BigDecimal("0.15");
    private static final BigDecimal A4 = new BigDecimal("0.225");
    private static final BigDecimal A5 = new BigDecimal("0.275");

    public BigDecimal calcular(BigDecimal renda){
        if (renda == null || renda.compareTo(BigDecimal.ZERO) <=0){
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }

        BigDecimal imposto = BigDecimal.ZERO;

        if(renda.compareTo(L4) > 0){
            BigDecimal excedente = renda.subtract(L4);
            imposto = imposto.add(excedente.multiply(A5));
            renda = L4;
        }

        if(renda.compareTo(L3) > 0){
            BigDecimal excedente = renda.subtract(L3);
            imposto = imposto.add(excedente.multiply(A4));
            renda = L3;
        }

        if(renda.compareTo(L2) > 0){
            BigDecimal excedente = renda.subtract(L2);
            imposto = imposto.add(excedente.multiply(A3));
            renda = L2;
        }

        if(renda.compareTo(L1) > 0){
            BigDecimal excedente = renda.subtract(L1);
            imposto = imposto.add(excedente.multiply(A2));
        }

        return imposto.setScale(2, RoundingMode.HALF_UP);
    }
}
