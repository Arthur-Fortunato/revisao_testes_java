package br.com.infnet;

import br.com.infnet.validador.PasswordPolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordPolicyParticions {
    PasswordPolicy passwordPolicy;

    @BeforeEach
    void setUp(){
        passwordPolicy = new PasswordPolicy(8, true, true, true, true);
    }

    @Test
    void testaForteEValida(){
        assertTrue(passwordPolicy.atendeRequisitos("Abcdef1!"));
    }

    @Test
    void semMaiusculaInvalida(){
        assertFalse(passwordPolicy.atendeRequisitos("abcdef1!"));
    }

    @Test
    void tamanhoMinimoFalha(){
        assertFalse(passwordPolicy.atendeRequisitos("Abx!"));
    }
}
