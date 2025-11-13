package br.com.infnet;

import br.com.infnet.validador.CpfUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CpfValidatorTest {
    CpfUtils cpfUtils;

    @BeforeEach
    public void setup(){
        cpfUtils = new CpfUtils();
    }

    @Test
    void rejeitaNulo(){
        assertFalse(cpfUtils.isValidCpf(null));
    }

    @Test
    void rejeitaTamanhoErrado(){
        assertFalse(cpfUtils.isValidCpf("123"));
    }

    @Test
    void rejeitaRepetidos(){
        assertFalse(cpfUtils.isValidCpf("000.000.000-00"));
    }

    @Test
    void testaValido(){
        assertTrue(cpfUtils.isValidCpf("124.963.297-84"));
    }
}
