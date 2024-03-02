package com.joaocarloslima;

import java.util.Objects;

public class Refrigerante {
    private String nome;
    private double preco;
    private Sabor sabor;

    public Refrigerante(String nome, double preco, Sabor sabor) {
        this.nome = nome;
        this.preco = preco;
        this.sabor = sabor;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public Sabor getSabor() {
        return sabor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Refrigerante)) return false;
        Refrigerante that = (Refrigerante) o;
        return Double.compare(that.preco, preco) == 0 &&
               Objects.equals(nome, that.nome) &&
               sabor == that.sabor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, preco, sabor);
    }
}
