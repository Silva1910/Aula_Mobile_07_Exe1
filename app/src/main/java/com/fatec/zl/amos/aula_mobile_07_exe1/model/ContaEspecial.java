package com.fatec.zl.amos.aula_mobile_07_exe1.model;


public class ContaEspecial extends ContaBancaria {

    private float limite;

    public ContaEspecial() {
        // TODO Auto-generated constructor stub
    }

    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }

    @Override
    public float sacar(float valor) {
        if (getLimite() + getSaldo() >= valor) {
            setSaldo(getSaldo() - valor);
            System.out.println("CONTA ESPECIAL ******  Seu saldo atual é " + getSaldo() + " e você acabou de sacar o valor de " + valor );
            return getSaldo();
        } else {
            System.out.println("CONTA ESPECIAL ******  O valor do saque é maior que o saldo disponível mais o limite");
            return -1; // Retornar um valor negativo para indicar erro
        }
    }

}
