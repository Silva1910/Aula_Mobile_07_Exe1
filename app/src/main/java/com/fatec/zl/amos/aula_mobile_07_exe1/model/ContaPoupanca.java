package com.fatec.zl.amos.aula_mobile_07_exe1.model;


public class ContaPoupanca extends ContaBancaria {

    private float rendimentoDiario;
    private float novoSaldo;



    public ContaPoupanca() {
        // TODO Auto-generated constructor stub
    }



    public float getRendimentoDiario() {
        return rendimentoDiario;
    }


    public void setRendimentoDiario(float rendimentoDiario) {
        this.rendimentoDiario = rendimentoDiario;
    }




    public float calcNovoSaldo() {

        setSaldo( getSaldo()  * getRendimentoDiario()) ;

        System.out.println(" CONTA POUPANCA ******" + " o novo saldo e " + getSaldo());
        return novoSaldo;
    }


}

