import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CheckingAccount {
    Integer numberAccount;
    Integer agency;
    String userName;
    LocalDate date;
    private Double balance = 0.0;
    boolean active = true;
    private final List<String> transactionHistory = new ArrayList<>();

    // Verificar saldo
    Double checkBalance() {
        return balance;
    }

    // Verificar extrato entre duas datas (simplificado com strings)
    List<String> checkStatement(LocalDate startDate, LocalDate endDate) {
        // Aqui poderia filtrar por data real — exemplo simplificado
        return transactionHistory;
    }

    // Cancelar com justificativa
    void cancel(String justification) {
        active = false;
        transactionHistory.add("Conta cancelada: " + justification);
        System.out.println("Conta cancelada com justificativa: " + justification);
    }

    // Transferir para outra conta
    void transfer(CheckingAccount destinationAccount, Double transferredValue) {
        if (balance >= transferredValue) {
            balance -= transferredValue;
            destinationAccount.balance += transferredValue;
            transactionHistory.add("Transferência de R$" + transferredValue + " para " + destinationAccount.userName);
            System.out.println("Transferência realizada com sucesso.");
        } else {
            System.out.println("Saldo insuficiente para transferência.");
        }
    }

    // Sacar valor solicitado
    void draw(Double requestValue) {
        if (balance >= requestValue) {
            balance -= requestValue;
            transactionHistory.add("Saque de R$" + requestValue);
            System.out.println("Saque realizado.");
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    // Depositar valor
    void deposit(Double amount) {
        balance += amount;
        transactionHistory.add("Depósito de R$" + amount);
        System.out.println("Depósito realizado.");
    }

    // Método principal
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CheckingAccount conta = new CheckingAccount();

        System.out.print("Nome do usuário: ");
        conta.userName = scanner.nextLine();

        System.out.print("Número da conta: ");
        conta.numberAccount = scanner.nextInt();

        System.out.print("Agência: ");
        conta.agency = scanner.nextInt();

        conta.date = LocalDate.now();

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1 - Ver saldo");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("4 - Cancelar conta");
            System.out.println("5 - Ver extrato");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Saldo atual: R$" + conta.checkBalance());
                    break;
                case 2:
                    System.out.print("Valor para depositar: ");
                    double dep = scanner.nextDouble();
                    conta.deposit(dep);
                    break;
                case 3:
                    System.out.print("Valor para sacar: ");
                    double saque = scanner.nextDouble();
                    conta.draw(saque);
                    break;
                case 4:
                    scanner.nextLine(); // Limpa o buffer
                    System.out.print("Justificativa para cancelamento: ");
                    String just = scanner.nextLine();
                    conta.cancel(just);
                    break;
                case 5:
                    System.out.println("Extrato:");
                    for (String transacao : conta.checkStatement(null, null)) {
                        System.out.println("→ " + transacao);
                    }
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}

