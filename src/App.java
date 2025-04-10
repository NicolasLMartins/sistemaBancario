
import java.util.Scanner;

public class App {

    public static void showMenu(boolean acc, String msg) {
        System.out.print("\033\143");
        System.out.println("========== MENU ==========");
        if (msg != null) {
            System.out.println("\n" + msg);
        }
        if (!acc) {
            System.out.println("\n1. Abrir conta");
        }
        if (acc) {
            System.out.println("\n2. Depositar");
            System.out.println("3. Sacar");
            System.out.println("4. Aplicar juros");
            System.out.println("5. Simular empréstimo");
            System.out.println("6. Extrato");
            System.out.println("7. Integrantes");
        }
        System.out.println("8. Sair");
    }

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        boolean contaExists = false;
        String nome = null;
        String msg = null;

        double saldoInicial = 0.0d;
        double saldoAtual = 0.0d;
        double totalDeposito = 0.0d;
        int qtyDeposito = 0;
        int qtySaques = 0;

        boolean valorValidacao = false;

        int option = 0;

        while (option != 8) {
            showMenu(contaExists, msg);
            option = input.nextInt();
            input.nextLine();
            msg = null;

            if (contaExists) {
                switch (option) {
                    case 2:

                        System.out.print("\033\143");
                        System.out.println("========== DEPÓSITO ==========\n");
                        System.out.printf("Saldo atual: R$ %.2f", saldoAtual);
                        double valorDeposito = 0.0d;

                        while (valorDeposito <= 0) {
                            System.out.print("\nDigite um valor a ser depositado: ");
                            valorDeposito = input.nextDouble();
                        }

                        saldoAtual += valorDeposito;
                        totalDeposito += valorDeposito;
                        qtyDeposito++;

                        msg = String.format("Saldo atual: R$ %.2f", saldoAtual);

                        break;
                    case 3:
                        System.out.print("\033\143");
                        System.out.println("===== Realizar saque =====");
                        System.out.println("Digite o valor a ser sacado!");

                        float valorSaque = 0f;
                        int notas;

                        while (valorValidacao == false) {
                            if (input.hasNextFloat()) {
                                valorSaque = input.nextFloat();
                                input.nextLine();

                                if (valorSaque % 2 != 0) {
                                    System.out.println("ERRO! Notas disponiveis: 100, 50, 20, 10, 5, 2.");

                                } else if (valorSaque <= 0) {
                                    System.out.println("Digite um valor que seja maior que zero!");
                                } else if (valorSaque > saldoAtual) {
                                    System.out.println("Impossivel sacar!! Saldo insuficiente");
                                } else {
                                    valorValidacao = true;
                                    saldoAtual -= valorSaque;
                                    qtySaques++;

                                    System.out.print("\033\143");
                                    System.out.format("Saque de R$%.2f realizado com sucesso!\n", valorSaque);
                                    System.out.format("Saldo atual: R$%.2f\n\n", saldoAtual);
                                }

                            } else {
                                System.out.println("ERRO! Digite um valor numerico!");
                            }
                        }
                        System.out.println("Pressione ENTER para voltar ao menu...");
                        input.nextLine();

                        break;
                    case 4:

                        break;
                    case 5:
                        double capital = 0.0d;
                        double taxa = 0.0d;
                        int parcelas = 0;
                        double totalJuros = 0.0d;
                        double totalParcelas = 0.0d;
                        msg = "";

                        while (taxa <= 0.0d || capital <= 0.0d || parcelas <= 0) {
                            System.out.print("\033\143");
                            System.out.println("========== SIMULAR EMPRÉSTIMO ==========\n");
                            if (capital <= 0.0d) {
                                System.out.print("Digite o valor do empréstimo: ");
                                capital = input.nextDouble();
                            }

                            if (taxa <= 0.0d) {
                                System.out.print("Digite a taxa de juros mensal: ");
                                taxa = input.nextDouble();
                            }

                            if (parcelas <= 0) {
                                System.out.print("Digite a quantidade de parcelas: ");
                                parcelas = input.nextInt();
                            }
                        }

                        double juros = 0.0d;
                        double montante = 0.0d;

                        for (int i = 0; i < parcelas; i++) {
                            juros = capital * taxa * (i + 1);
                            montante = capital + juros;
                            msg += String.format("Parcela %d: R$ %.2f\n", (i + 1), montante);
                            totalJuros += juros;
                            totalParcelas += montante;
                        }

                        msg += String.format("Total de juros: R$ %.2f\n", totalJuros);
                        msg += String.format("Total de parcelas: R$ %.2f", totalParcelas);

                        break;
                    case 6:

                        break;
                    case 7:

                        break;
                    case 8:
                        msg = "Fechando programa";

                        break;
                    default:
                        msg = "ERRO: Opção indisponível";

                        break;
                }
            } else {
                switch (option) {
                    case 1:
                        System.out.print("\033\143");
                        System.out.println("========== ABRIR CONTA ==========\n");
                        System.out.print("Digite o nome do titular da nova conta: ");
                        nome = input.nextLine();
                        System.out.print("Saldo inicial da nova conta: ");
                        saldoInicial = input.nextFloat();
                        saldoAtual = saldoInicial;
                        input.nextLine();

                        System.out.println("Conta cadastrada com sucesso!");
                        System.out.format("Saldo inicial de R$%.2f", saldoInicial);
                        input.nextLine();

                        System.out.print("\033\143");
                        System.out.println("Pressione ENTER para voltar ao menu...");
                        input.nextLine();

                        if (saldoInicial != 0.0d && saldoInicial > 0 && saldoAtual == saldoInicial) {
                            contaExists = true;
                        }

                        break;

                    case 8:
                        msg = "Fechando programa";

                        break;
                    default:
                        msg = "ERRO: Opção indisponível";

                        break;
                }
            }
        }

        showMenu(contaExists, msg);

        input.close();
    }
}
