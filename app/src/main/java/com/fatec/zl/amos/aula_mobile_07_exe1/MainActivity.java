package com.fatec.zl.amos.aula_mobile_07_exe1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fatec.zl.amos.aula_mobile_07_exe1.model.ContaEspecial;
import com.fatec.zl.amos.aula_mobile_07_exe1.model.ContaPoupanca;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private EditText etPoupancaSaque;
    private EditText etPoupancaDeposito;
    private EditText etEspecialSaque;
    private EditText etEspecialDeposito;
    private TextView tvRes;
    private Button btnCalc;
    private RadioButton rdEspecial;
    private RadioButton rdPoupanca;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ContaPoupanca CP = new ContaPoupanca();
        CP.setCliente("Silva");
        CP.setNum_conta(2);
        CP.setSaldo(0f);
        CP.setRendimentoDiario(1.15f);
        ContaEspecial CE = new ContaEspecial();
        CE.setCliente("Amos");
        CE.setNum_conta(1);
        CE.setSaldo(0f);
        CE.setLimite(500f);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etPoupancaSaque = findViewById(R.id.etPoupancaSaque);
        etPoupancaSaque.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etPoupancaDeposito = findViewById(R.id.etPoupancaDeposito);
        etPoupancaDeposito.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etEspecialSaque = findViewById(R.id.etEspecialSaque);
        etEspecialSaque.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etEspecialDeposito = findViewById(R.id.etEspecialDeposito);
        etEspecialDeposito.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvRes = findViewById(R.id.tvRes);
        tvRes.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


        btnCalc = findViewById(R.id.btnCalc);

        radioGroup = findViewById(R.id.radioGroup);
        rdEspecial = findViewById(R.id.rdEspecial);
        rdPoupanca = findViewById(R.id.rdPoupanca);


        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rdPoupanca) {
                etPoupancaSaque.setEnabled(true);
                etPoupancaDeposito.setEnabled(true);
                etEspecialSaque.setEnabled(false);
                etEspecialDeposito.setEnabled(false);
            } else if (checkedId == R.id.rdEspecial) {
                etPoupancaSaque.setEnabled(false);
                etPoupancaDeposito.setEnabled(false);
                etEspecialSaque.setEnabled(true);
                etEspecialDeposito.setEnabled(true);
            }
        });

        btnCalc.setOnClickListener(v -> {

            String resultado = "";
            if (rdPoupanca.isChecked()) {
                String saqueText = etPoupancaSaque.getText().toString();
                String depositoText = etPoupancaDeposito.getText().toString();

                if (!saqueText.isEmpty() && !depositoText.isEmpty()) {
                    float saque = Float.parseFloat(saqueText);
                    float deposito = Float.parseFloat(depositoText);
                    if ( CP.getSaldo() >= saque) {
                        CP.sacar(saque);
                        CP.depositar(deposito);
                        CP.calcNovoSaldo();

                        CP.setSaldo(CP.getSaldo() - saque);
                        resultado = "Nome: " + CP.getCliente() + " " +
                                "ID CONTA: " + CP.getNum_conta() + "\n" +
                                "Saldo + Investimento: " + CP.getSaldo();
                    } else {
                        resultado = "O saque excede o saldo disponível ";
                    }




                } else {
                    resultado = "Por favor, preencha um campo e o outro como zero.";
                }
            } else if (rdEspecial.isChecked()) {
                String saqueText = etEspecialSaque.getText().toString();
                String depositoText = etEspecialDeposito.getText().toString();

                if (!saqueText.isEmpty() && !depositoText.isEmpty()) {
                    float saque = Float.parseFloat(saqueText);
                    float deposito = Float.parseFloat(depositoText);

                    CE.depositar(deposito);

                    if (CE.getLimite() + CE.getSaldo() >= saque) {
                        CE.setSaldo(CE.getSaldo() - saque);
                        resultado = "Nome: " + CE.getCliente() + " " +
                                "ID CONTA: " + CE.getNum_conta() + "\n" +
                                "Saldo: " + CE.getSaldo();
                    } else {
                        resultado = "O saque excede o saldo disponível mais o limite.";
                    }
                } else {
                    resultado = "Por favor, um campo e outro como zero";
                }
            }



            tvRes.setText(resultado);
        });
    }
}
