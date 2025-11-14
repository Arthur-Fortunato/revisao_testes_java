package br.com.infnet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// TDD -> Test-Driven Development
public class CalculadoraTest {
    @Test
    public void deveSomarDoisInteiros() {
        int a = 1;
        int b = 1;
        Calculadora c = new Calculadora();
        int soma = c.soma(a, b);
        assertEquals(2, soma);
        soma = c.soma(b, a);
        assertEquals(2, soma);
    }

    @Test
    public void deveDividirDoisInteiros() {
        int a = 4;
        int b = 2;
        Calculadora c = new Calculadora();
        c.divide(a, b);
        int resultado = c.divide(a, b);
        assertEquals(2, resultado);
        assertThrows(IllegalArgumentException.class, () -> {c.divide(4, 0)});
    }
}
