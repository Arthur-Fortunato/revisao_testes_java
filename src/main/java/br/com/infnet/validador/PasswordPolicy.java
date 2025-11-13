package br.com.infnet.validador;

public class PasswordPolicy {
    private final int minLength;
    private final boolean requireUpper;
    private final boolean requireLower;
    private final boolean requireDigit;
    private final boolean requireSymbol;

    public PasswordPolicy(int minLength, boolean requireUpper, boolean requireLower, boolean requireDigit, boolean requireSymbol) {
        this.minLength = minLength;
        this.requireUpper = requireUpper;
        this.requireLower = requireLower;
        this.requireDigit = requireDigit;
        this.requireSymbol = requireSymbol;
    }

    public boolean atendeRequisitos(String s){
        if (s == null|| s.length() < minLength) return false;
        boolean up = false, low = false, dig = false, sym = false;
        for (char c : s.toCharArray()) {
            if(Character.isUpperCase(c))    up = true;
            if(Character.isLowerCase(c))    low = true;
            if(Character.isDigit(c))        dig = true;
            else                            sym = true;
        }
        if (requireUpper && !up) return false;
        if (requireLower && !low) return false;
        if (requireDigit && !dig) return false;
        if (requireSymbol && !sym) return false;
        return true;
    }
}
