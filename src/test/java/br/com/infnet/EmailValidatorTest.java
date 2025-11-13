package br.com.infnet;

import br.com.infnet.validador.EmailUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailValidatorTest {
    EmailUtil emailUtil;
    @BeforeEach
    void setUp(){
        emailUtil = new EmailUtil();
    }

    @Test
    void testaValido(){
        assertTrue(emailUtil.isWellFormed("teste@agencia.gov.br"));
    }

    @Test
    void testaInvalido(){
        assertFalse(emailUtil.isWellFormed("teste.com.br"));
    }
}
