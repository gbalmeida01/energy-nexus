package com.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppNexus {
    private static List<Room> comodos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("==== Nexus - Calculadora de Consumo ====");
        System.out.println("(Usando tarifa média de R$0.80/kWh)\n");

        while (true) {
            System.out.println("\nMenu Principal:");
            System.out.println("1. Adicionar cômodo");
            System.out.println("2. Listar cômodos e consumo");
            System.out.println("3. Adicionar eletrodoméstico");
            System.out.println("4. Relatório completo");
            System.out.println("5. Sair");
            System.out.print("Escolha: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> adicionarComodo();
                case 2 -> listarComodos();
                case 3 -> adicionarEletrodomestico();
                case 4 -> exibirRelatorioCompleto();
                case 5 -> System.exit(0);
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void adicionarComodo() {
        System.out.print("\nNome do cômodo: ");
        String nome = scanner.nextLine();
        comodos.add(new Room(nome));
        System.out.println("Cômodo adicionado!");
    }

    private static void listarComodos() {
        if (comodos.isEmpty()) {
            System.out.println("\nNenhum cômodo cadastrado.");
            return;
        }

        System.out.println("\nCômodos cadastrados:");
        for (int i = 0; i < comodos.size(); i++) {
            Room c = comodos.get(i);
            System.out.printf("%d. %s - %d eletrodoméstico(s) - Custo diário: R$%.2f%n",
                    i + 1, c.getNome(), c.getEletrodomesticos().size(), c.getCustoTotalDiario());
        }
    }

    private static void adicionarEletrodomestico() {
        if (comodos.isEmpty()) {
            System.out.println("\nPrimeiro adicione um cômodo!");
            return;
        }

        listarComodos();
        System.out.print("\nNúmero do cômodo: ");
        int numComodo = scanner.nextInt() - 1;
        scanner.nextLine();

        if (numComodo < 0 || numComodo >= comodos.size()) {
            System.out.println("Cômodo inválido!");
            return;
        }

        System.out.print("\nNome do aparelho: ");
        String nome = scanner.nextLine();

        System.out.print("Potência em Watts: ");
        double watts = scanner.nextDouble();

        System.out.print("Horas de uso diário: ");
        double horas = scanner.nextDouble();
        scanner.nextLine();

        comodos.get(numComodo).adicionarEletrodomestico(new Appliances(nome, watts, horas));
        System.out.println("Aparelho adicionado!");
    }

    private static void exibirRelatorioCompleto() {
        if (comodos.isEmpty()) {
            System.out.println("\nNenhum cômodo cadastrado.");
            return;
        }

        System.out.println("\n=== RELATÓRIO DE CONSUMO ===");
        double totalDiario = 0;
        double totalMensal = 0;

        for (Room comodo : comodos) {
            System.out.printf("\n● %s%n", comodo.getNome());
            comodo.listarEletrodomesticos();

            double custoDiario = comodo.getCustoTotalDiario();
            double custoMensal = custoDiario * 30;

            System.out.printf("\nTotal diário: R$%.2f (%.2f kWh)%n", custoDiario, comodo.getConsumoTotalDiario());
            System.out.printf("Projeção mensal: R$%.2f%n", custoMensal);

            totalDiario += custoDiario;
            totalMensal += custoMensal;
        }

        System.out.printf("\n>> TOTAL GERAL <<\nDiário: R$%.2f | Mensal: R$%.2f%n", totalDiario, totalMensal);
    }
}