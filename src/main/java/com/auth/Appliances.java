package com.auth;

public class Appliances {
    private String nome;
    private double potenciaWatts;
    private double horasUsoDiario;

    private static final double TARIFA_MEDIA = 0.80;

    public Appliances(String nome, double potenciaWatts, double horasUsoDiario) {
        this.nome = nome;
        this.potenciaWatts = potenciaWatts;
        this.horasUsoDiario = horasUsoDiario;
    }

    public double calcularConsumoDiario() {
        return (potenciaWatts * horasUsoDiario) / 1000;
    }

    public double calcularCustoDiario() {
        return calcularConsumoDiario() * TARIFA_MEDIA;
    }

    public String getNome() {
        return nome;
    }

    public double getPotenciaWatts() {
        return potenciaWatts;
    }

    public double getHorasUsoDiario() {
        return horasUsoDiario;
    }

    public String getInfoResumida() {
        return String.format("%s (%.0fW, %.1fh/dia) - Custo: R$%.2f/dia",
                nome, potenciaWatts, horasUsoDiario, calcularCustoDiario());
    }
}