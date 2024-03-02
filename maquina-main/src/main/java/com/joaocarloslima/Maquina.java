package com.joaocarloslima;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maquina {
    private double saldo;
    private double totalArrecadado;
    private List<Refrigerante> refrigerantes = new ArrayList<>();

    public Maquina() {
        Random random = new Random();
        // Aqui adicionamos 10 refrigerantes à lista
        for (int i = 0; i < 10; i++) {
            double precoAleatorio = 1 + (4 * random.nextDouble());
            precoAleatorio = Math.round(precoAleatorio * 100.0) / 100.0;
            refrigerantes.add(new Refrigerante(Sabor.values()[i/2].name(), precoAleatorio, Sabor.values()[i/2]));
        }
    }

    public void adicionarRefrigerante(Refrigerante refrigerante) {
        refrigerantes.add(refrigerante);
    }

    public void inserirDinheiro(double valor) {
        this.saldo += valor;
    }

    public void sacarDinheiro() {
        this.saldo = 0;
    }

    public boolean comprarRefrigerante(Refrigerante refrigerante) throws SaldoInsuficienteException {
        if (saldo < refrigerante.getPreco()) {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar a compra.");
        }
        if (refrigerantes.contains(refrigerante)) {
            saldo -= refrigerante.getPreco();
            totalArrecadado += refrigerante.getPreco();
            refrigerantes.remove(refrigerante);
            return true;
        }
        return false;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getTotalArrecadado() {
        return totalArrecadado;
    }

    public List<Refrigerante> getRefrigerante() {
        return new ArrayList<>(refrigerantes);
    }
}

// Exceção personalizada para saldo insuficiente
class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(String message) {
        super(message);
    }
}
