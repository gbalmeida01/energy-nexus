package com.auth;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String nome;
    private List<Appliances> eletrodomesticos;

    public List<Appliances> getEletrodomesticos() {
        return eletrodomesticos;
    }

    public void setEletrodomesticos(List<Appliances> eletrodomesticos) {
        this.eletrodomesticos = eletrodomesticos;
    }

    public Room(String nome) {
        this.nome = nome;
        this.eletrodomesticos = new ArrayList<>();
    }

    public void adicionarEletrodomestico(Appliances eletrodomestico) {
        eletrodomesticos.add(eletrodomestico);
    }

    public double getConsumoTotalDiario() {
        return eletrodomesticos.stream()
                .mapToDouble(Appliances::calcularConsumoDiario)
                .sum();
    }

    public double getCustoTotalDiario() {
        return eletrodomesticos.stream()
                .mapToDouble(Appliances::calcularCustoDiario)
                .sum();
    }

    public void listarEletrodomesticos() {
        if (eletrodomesticos.isEmpty()) {
            System.out.println("Nenhum eletrodoméstico neste cômodo.");
            return;
        }

        System.out.println("\nEletrodomésticos no " + nome + ":");
        for (int i = 0; i < eletrodomesticos.size(); i++) {
            System.out.println((i + 1) + ". " + eletrodomesticos.get(i).getInfoResumida());
        }
    }

    public String getNome() {
        return nome;
    }
}