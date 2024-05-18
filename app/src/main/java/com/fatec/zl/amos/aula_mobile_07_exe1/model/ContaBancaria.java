package com.fatec.zl.amos.aula_mobile_07_exe1.model;


public class ContaBancaria {

    private float saldo;
    private int num_conta;
    private String cliente;

    public ContaBancaria() {
        super();
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public int getNum_conta() {
        return num_conta;
    }

    public void setNum_conta(int num_conta) {
        this.num_conta = num_conta;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public float sacar(float valor) {
        if (valor <= saldo) {
            saldo -= valor;
        } else {
            System.out.println("Saldo insuficiente");
        }
        return saldo;
    }

    public float depositar(float valor) {
        saldo += valor;
        return saldo;
    }
}
