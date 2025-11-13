package br.com.infnet.validador;

public class CpfUtils {
    public boolean isValidCpf(String raw){
        if (raw == null) return false;
        String cpf = raw.replaceAll("\\D", "");
        if (cpf.length() != 11) return false;
        if (cpf.chars().distinct().count() == 1) return false;

        int dv1 = calcDv(cpf, 10);
        int dv2 = calcDv(cpf, 11);
        return cpf.charAt(9) - '0' == dv1 && cpf.charAt(10) - '0' == dv2;
    }

    public static int calcDv(String cpf, int pesoInicial){
        int soma = 0;
        for (int i = 0; i < pesoInicial - 1; i++) {
            soma += (cpf.charAt(i) - '0') * (pesoInicial - i);
        }
        int mod = soma % 11;
        int dv = (mod < 2) ? 0 : 11 - mod;
        return dv;
    }
}
