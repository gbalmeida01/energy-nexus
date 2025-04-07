package com.auth.util;

import com.auth.model.Room;
import com.auth.model.Appliances;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    public static final Scanner scanner = new Scanner(System.in);

    public static void exibirMenuPrincipal() {
        System.out.println("\nMenu Principal:");
        System.out.println("1. Adicionar cômodo");
        System.out.println("2. Listar cômodos e consumo");
        System.out.println("3. Adicionar eletrodoméstico");
        System.out.println("4. Relatório completo");
        System.out.println("5. Sair");
        System.out.print("Escolha: ");
    }

    public static int lerOpcao() {
        try {
            return scanner.nextInt();
        } finally {
            scanner.nextLine(); // Limpa o buffer do teclado
        }
    }

    public static void exibirCabecalho() {
        System.out.println();
        System.out.println("==== Nexus - Calculadora de Consumo ====");
        System.out.println("(Usando tarifa média de R$0.80/kWh)\n");
    }

    public static String lerNomeComodo() {
        System.out.print("\nNome do cômodo: ");
        return scanner.nextLine();
    }

    public static void listarComodos(List<Room> comodos) {
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

    public static int selecionarComodo(List<Room> comodos) {
        listarComodos(comodos);
        System.out.print("\nNúmero do cômodo: ");
        return scanner.nextInt() - 1;
    }

    public static Appliances lerEletrodomestico() {
        scanner.nextLine(); // Limpa o buffer antes de começar

        System.out.print("\nNome do aparelho: ");
        String nome = scanner.nextLine();

        System.out.print("Potência em Watts: ");
        double watts = scanner.nextDouble();

        System.out.print("Horas de uso diário: ");
        double horas = scanner.nextDouble();
        scanner.nextLine(); // Limpa o buffer após a leitura

        return new Appliances(nome, watts, horas);
    }
}