package br.com.infnet;

import br.com.infnet.client.BlackList;
import br.com.infnet.client.EmailBlackListClient;
import br.com.infnet.validador.EmailUtil;
import br.com.infnet.validador.FormValidator;
import br.com.infnet.validador.PasswordPolicy;
import com.github.javafaker.Faker;
import net.jqwik.api.*;
import net.jqwik.api.lifecycle.BeforeProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class FormValidatorTest {
    private FormValidator validator;
    BlackList blackList;

    @BeforeEach@BeforeProperty
    void setUp(){
        PasswordPolicy policy = new PasswordPolicy(8, true,true,true,true);
        blackList = mock(BlackList.class);
        when(blackList.isBlackListed(anyString())).thenReturn(false);
        validator = new FormValidator(policy, blackList);
    }

    @Test
    @DisplayName("Deve aceitar CPF válido")
    void validarCpf(){
        assertTrue(validator.validarCpf("390.533.447-05"));
        assertFalse(validator.validarCpf("123.456.789-00"));
        assertFalse(validator.validarCpf("000.000.000-00"));
        assertFalse(validator.validarCpf(null));
    }

    @Property(tries = 50)
    void emailsGeradosSaoValidos(@ForAll("emailsGerados") String email){
        assertTrue(validator.validarEmail(email),
                () -> "E-mail inválido gerado:" + email);
    }

    @Provide
    Arbitrary<String> emailsGerados(){
        Arbitrary<String> usuario = Arbitraries.strings().withCharRange('a', 'z').ofMinLength(1).ofMaxLength(10);
        Arbitrary<String> dominio = Arbitraries.strings().withCharRange('a', 'z').ofMinLength(3).ofMaxLength(8);
        Arbitrary<String> tld     = Arbitraries.of("org", "com", "gov", "edu", "net");
        return Combinators.combine(usuario, dominio, tld).as((u,d,t) -> u + "@" + d + "." + t);
    }

    @Test
    @DisplayName("Deve aceitar e-mail bem formado e rejeitar mal formado")
    void validarEmail(){


        assertTrue(validator.validarEmail("astronauta@nasa.gov"));
        assertTrue(validator.validarEmail("ana.silva+teste@empresa.com.br"));

        assertFalse(validator.validarEmail("astronauta.nasa.gov"));

        assertFalse(validator.validarEmail("@nasa.gov"));
        assertFalse(validator.validarEmail("astro@nasa"));
        assertFalse(validator.validarEmail(null));
    }

    @Test
    @DisplayName("Deve rejeitar um e-mail no black list")
    void validarEmailNoBlackList(){
        when(blackList.isBlackListed("astronauta2@nasa.gov")).thenReturn(true);
        assertTrue(validator.validarEmail("astronauta@nasa.gov"));
        assertTrue(validator.validarEmail("ana.silva+teste@empresa.com.br"));

        assertFalse(validator.validarEmail("astronauta.nasa.gov"));
        assertFalse(validator.validarEmail("astronauta2@nasa.gov"));

        assertFalse(validator.validarEmail("@nasa.gov"));
        assertFalse(validator.validarEmail("astro@nasa"));
        assertFalse(validator.validarEmail(null));

        verify(blackList).isBlackListed("astronauta2@nasa.gov");
    }
}
