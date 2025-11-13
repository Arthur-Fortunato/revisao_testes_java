package br.com.infnet;

import br.com.infnet.validador.FormValidator;
import br.com.infnet.validador.PasswordPolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IdadeValidatorLimits {
    PasswordPolicy passwordPolicy;
    FormValidator form;
    @BeforeEach
    void setUp(){
        passwordPolicy = new PasswordPolicy(0, true, true, true, true);
        form = new FormValidator(passwordPolicy);
    }

    @Test
    void limiteInferior(){
        assertTrue(form.validarIdade(1));
        assertTrue(form.validarIdade(0));
        assertFalse(form.validarIdade(-1));
    }

    @Test
    void limiteSuperior(){
        assertTrue(form.validarIdade(150));
        assertFalse(form.validarIdade(151));
        assertTrue(form.validarIdade(149));
    }

}
