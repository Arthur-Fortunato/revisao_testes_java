package br.com.infnet;

import br.com.infnet.validador.EmailUtil;
import com.github.javafaker.Faker;
import net.jqwik.api.*;
import net.jqwik.api.lifecycle.BeforeProperty;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailPropertiesTest {

    EmailUtil emailUtil;
    @BeforeProperty
    void setUp(){
        emailUtil = new EmailUtil();
    }

    @Property
    void arrobaNecessario(@ForAll("naoEmails") String email){
        emailUtil.isWellFormed(email);
    }

    @Provide
    Arbitrary<String> naoEmails(){
        return Arbitraries.strings().ofMinLength(1).ofMaxLength(12)
                .map(s -> ".com");
    }

    @Provide
    Arbitrary<String> emailsBasicos(){
        var user = Arbitraries.strings().withChars("abcdefghijklm.-_").ofMinLength(1).ofMaxLength(12);
        var host = Arbitraries.strings().withChars("abcdefghijklm").ofMinLength(1).ofMaxLength(8);
        var tld = Arbitraries.of("org", "com", "gov", "edu", "net");
        return Combinators.combine(user, host, tld).as((u,h,t) -> u + "@" + h + "." + t);
    }

    @Property
    boolean emailsSimplesSaoAceitos(@ForAll("emailsBasicos") String email){
        return emailUtil.isWellFormed(email);
    }

    @Provide
    Arbitrary<String> emailFaker(){
        Faker faker = new Faker();
        return Arbitraries.integers().between(1, 1_000_000).map(i -> faker.internet().emailAddress());
    }

    @Property(tries = 3000)
    void emailsDoFakerSaoValidos(@ForAll("emailFaker") String email){
        System.out.println(email);
        assertTrue(emailUtil.isWellFormed(email), () -> "Email gerado pelo faker deveria ser valido " + email);
    }
}
