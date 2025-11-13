package br.com.infnet.validador;

import java.util.regex.Pattern;

public class EmailUtil {
    private final Pattern SIMPLE =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public boolean isWellFormed(String email){
        if(email == null) return false;
        return SIMPLE.matcher(email).matches();
    }
}
