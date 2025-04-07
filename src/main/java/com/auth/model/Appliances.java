package com.auth.model;

public class Appliances {
    private String nome;
    private double watts;
    private double horasUsoDiario;
    private static final double TARIFA = 0.80;

    public Appliances(String nome, double watts, double horasUsoDiario) {
        this.nome = nome;
        this.watts = watts;
        this.horasUsoDiario = horasUsoDiario;
    }

    public double getConsumoDiarioKWh() {
        return (watts * horasUsoDiario) / 1000;
    }

    public double getCustoDiario() {
        return getConsumoDiarioKWh() * TARIFA;
    }

    // Getters
    public String getNome() { return nome; }
    public double getWatts() { return watts; }
    public double getHorasUsoDiario() { return horasUsoDiario; }
}