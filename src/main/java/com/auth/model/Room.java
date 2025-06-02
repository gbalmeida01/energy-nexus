package com.auth.model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String nome;
    private List<Appliances> eletrodomesticos;

    public Room(String nome) {
        this.nome = nome;
        this.eletrodomesticos = new ArrayList<>();
    }

    public void adicionarEletrodomestico(Appliances eletrodomestico) {
        eletrodomesticos.add(eletrodomestico);
    }

    public double getConsumoTotalDiario() {
        return eletrodomesticos.stream()
                .mapToDouble(Appliances::getConsumoDiarioKWh)
                .sum();
    }

    public double getCustoTotalDiario() {
        return eletrodomesticos.stream()
                .mapToDouble(Appliances::getCustoDiario)
                .sum();
    }

    public String getNome() { return nome; }
    public List<Appliances> getEletrodomesticos() { return eletrodomesticos; }
}