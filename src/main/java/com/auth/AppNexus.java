package com.auth;

import com.auth.model.Appliances;
import com.auth.model.Room;
import com.auth.model.User;
import com.auth.service.AuthService;
import com.auth.service.RelatorioService;
import com.auth.util.ConsoleUI;
import java.util.ArrayList;
import java.util.List;

public class AppNexus {
    private static final List<Room> comodos = new ArrayList<>();
    private static final RelatorioService relatorioService = new RelatorioService();
    private static final AuthService authService = new AuthService();
    private static User userLogin;

    public static void main(String[] args) {
        authUser();
        ConsoleUI.exibirCabecalho();

        while (true) {
            ConsoleUI.exibirMenuPrincipal();
            int opcao = ConsoleUI.lerOpcao();

            switch (opcao) {
                case 1 -> adicionarComodo();
                case 2 -> ConsoleUI.listarComodos(comodos);
                case 3 -> adicionarEletrodomestico();
                case 4 -> relatorioService.gerarRelatorioCompleto(comodos);
                case 5 -> {
                    System.out.println("Saindo do sistema...");
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void authUser() {
        while(userLogin == null) {
            ConsoleUI.exibirLogin();
            String username = ConsoleUI.lerUsername();
            String password = ConsoleUI.lerSenha();

            userLogin = authService.login(username, password);
            if (userLogin == null) {
                System.out.println("Usuário ou senha inválidos! Tente novamente.\n");
            } else {
                System.out.println("\nBem-vindo, " + userLogin.getUsername() + "!\n");
            }
        }
    }

    private static void adicionarComodo() {
        String nome = ConsoleUI.lerNomeComodo();
        comodos.add(new Room(nome));
        System.out.println("Cômodo adicionado com sucesso!");
    }

    private static void adicionarEletrodomestico() {
        if (comodos.isEmpty()) {
            System.out.println("\nPrimeiro adicione um cômodo!");
            return;
        }

        try {
            int numComodo = ConsoleUI.selecionarComodo(comodos);
            if (numComodo < 0 || numComodo >= comodos.size()) {
                System.out.println("Cômodo inválido!");
                return;
            }

            Appliances eletrodomestico = ConsoleUI.lerEletrodomestico();
            comodos.get(numComodo).adicionarEletrodomestico(eletrodomestico);
            System.out.println("Aparelho adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }
}