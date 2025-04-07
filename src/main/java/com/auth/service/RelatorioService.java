package com.auth.service;

import com.auth.model.Room;
import com.auth.model.Appliances;
import java.util.List;

public class RelatorioService {
    public void gerarRelatorioCompleto(List<Room> comodos) {
        if (comodos.isEmpty()) {
            System.out.println("\nNenhum cômodo cadastrado.");
            return;
        }

        System.out.println("\n=== RELATÓRIO DE CONSUMO ===");
        double totalDiario = 0;
        double totalMensal = 0;

        for (Room comodo : comodos) {
            System.out.printf("\n● %s%n", comodo.getNome());
            System.out.println();
            listarEletrodomesticosDoComodo(comodo);

            double custoDiario = comodo.getCustoTotalDiario();
            double custoMensal = custoDiario * 30;

            System.out.printf("\nTotal diário: R$%.2f (%.2f kWh)%n", custoDiario, comodo.getConsumoTotalDiario());
            System.out.printf("Projeção mensal: R$%.2f%n", custoMensal);

            totalDiario += custoDiario;
            totalMensal += custoMensal;
        }

        exibirTotalGeral(totalDiario, totalMensal);
    }

    private void listarEletrodomesticosDoComodo(Room comodo) {
        for (Appliances aparelho : comodo.getEletrodomesticos()) {
            System.out.printf("- %s: %.1f W × %.1f h = %.2f kWh/dia (R$%.2f)%n",
                    aparelho.getNome(),
                    aparelho.getWatts(),
                    aparelho.getHorasUsoDiario(),
                    aparelho.getConsumoDiarioKWh(),
                    aparelho.getCustoDiario());
        }
    }

    private void exibirTotalGeral(double totalDiario, double totalMensal) {
        System.out.printf("\n>> TOTAL GERAL <<\nDiário: R$%.2f | Mensal: R$%.2f%n", totalDiario, totalMensal);
    }
}