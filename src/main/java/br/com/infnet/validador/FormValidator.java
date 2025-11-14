package br.com.infnet.validador;

import br.com.infnet.client.BlackList;
import br.com.infnet.client.EmailBlackListClient;

public class FormValidator {
    private final PasswordPolicy policy;
    private final CpfUtils cpfUtils;
    private final EmailUtil emailUtil;
    BlackList blackListClient;


    public FormValidator(PasswordPolicy policy, BlackList blackList) {
        this.policy = policy;
        cpfUtils = new CpfUtils();
        emailUtil = new EmailUtil();
        blackListClient = blackList;
    }

    public boolean validarCpf(String cpf){
        return cpfUtils.isValidCpf(cpf);
    }

    public boolean validarEmail(String email){
        if(!emailUtil.isWellFormed(email)) return false;
        return !blackListClient.isBlackListed(email);
    }

    public boolean validarIdade(int idade){
        return idade >= 0 && idade <= 150;
    }

    public boolean validarSenha(String senha){
        if(!policy.atendeRequisitos(senha)) return false;
        return true;
    }
}
