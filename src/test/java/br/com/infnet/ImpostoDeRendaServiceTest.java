package br.com.infnet;

import br.com.infnet.imposto.ImpostoRendaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImpostoDeRendaServiceTest {
    ImpostoRendaService impostoRendaService;

    @BeforeEach
    public void setup() {
        impostoRendaService = new ImpostoRendaService();
    }

    @ParameterizedTest(name = "[{index}] Renda = {0} -> imposto esperado = {1}")
    @CsvSource({
            "0.00, 0.00",
            "1999.99, 0.00",
            "2000.00, 0.00",
            "2500.00, 37.50",
            "3000.00, 75.00",
            "4000.00, 225.00",
            "5000.00, 375.00",
            "7000.00, 825.00",
            "10000.00, 1500.00",
            "15000.00, 2875.00",
            "25000.00, 5625.00"
    })
    void deveCalcularCorretamente(BigDecimal renda, BigDecimal esperado){
        BigDecimal imposto = impostoRendaService.calcular(renda);
        assertEquals(esperado, imposto, () -> "Falha para renda " + renda + ": esperado " + esperado + ", obtido " + imposto);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/impostos.csv", numLinesToSkip = 1)
    @DisplayName("Deve calcular corretamente (usando @CsvFileSource)")
    void deveCalcularCorretamenteComCSV(BigDecimal renda, BigDecimal esperado){
        assertEquals(esperado, impostoRendaService.calcular(renda));
    }

    @ParameterizedTest(name = "Renda {0}")
    @ValueSource(doubles =  {0.0, 500.0, 1500.0, 1999.99})
    @DisplayName("Deve calcular corretamente")
    public void testeSimples(double renda) {
        BigDecimal resultado = impostoRendaService.calcular(BigDecimal.valueOf(renda));
        assertEquals(new BigDecimal("0.00"), resultado);
    }
}
