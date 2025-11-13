package br.com.infnet.validador;

import br.com.infnet.client.EmailBlackListClient;

public class FormValidator {
    private final PasswordPolicy policy;
    private final CpfUtils cpfUtils;
    private final EmailUtil emailUtil;

    public FormValidator(PasswordPolicy policy){
        this.policy = policy;
        cpfUtils = new CpfUtils();
        emailUtil = new EmailUtil();
    }

    public boolean validarCpf(String cpf){
        return cpfUtils.isValidCpf(cpf);
    }

    public boolean validarEmail(String email){
        EmailBlackListClient blackListClient = new EmailBlackListClient();
        if(!emailUtil.isWellFormed(email)) return false;
        if(blackListClient.isBlackListed(email)) return false;
        return true;
    }

    public boolean validarIdade(int idade){
        return idade >= 0 && idade <= 150;
    }

    public boolean validarSenha(String senha){
        if(!policy.atendeRequisitos(senha)) return false;
        return true;
    }
}
