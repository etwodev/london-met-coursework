import java.util.Scanner;
import service.CommandService;

public class BankApp {
  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    while (true) {
      System.out.println("=== Main Menu ===");
      System.out.println("1. Manage Accounts");
      System.out.println("2. View Transactions");
      System.out.println("3. Exit");
      System.out.print("Choose an option: ");

      int choice;
      try {
        choice = Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a number.");
        continue;
      }

      switch (choice) {
        case 1:
          manageAccountsMenu();
          break;
        case 2:
          viewTransactionsMenu();
          break;
        case 3:
          System.out.println("Exiting application.");
          return;
        default:
          System.out.println("Invalid option. Please try again.");
      }
    }
  }

  private static void manageAccountsMenu() {
    while (true) {
      System.out.println("=== Manage Accounts ===");
      System.out.println("1. Create Account");
      System.out.println("2. Deposit");
      System.out.println("3. Withdraw");
      System.out.println("4. Send Money");
      System.out.println("5. List Accounts");
      System.out.println("6. Close Account");
      System.out.println("7. Get account details");
      System.out.println("8. Back to Main Menu");
      System.out.print("Choose an option: ");

      int choice;
      try {
        choice = Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a number.");
        continue;
      }

      switch (choice) {
        case 1:
          CommandService.create();
          break;
        case 2:
          CommandService.deposit();
          break;
        case 3:
          CommandService.withdraw();
          break;
        case 4:
          CommandService.send();
          break;
        case 5:
          CommandService.listAccounts();
          break;
        case 6:
          CommandService.close();
          break;
        case 7:
          CommandService.account();
          break;
        case 8:
          return;
        default:
          System.out.println("Invalid option. Please try again.");
      }
    }
  }

  private static void viewTransactionsMenu() {
    while (true) {
      System.out.println("=== View Transactions ===");
      System.out.println("1. List All Transactions");
      System.out.println("2. Search Transaction by Amount");
      System.out.println("3. Back to Main Menu");
      System.out.print("Choose an option: ");

      int choice;
      try {
        choice = Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a number.");
        continue;
      }

      switch (choice) {
        case 1:
          CommandService.listTransactions();
          break;
        case 2:
          CommandService.searchTransactions();
          break;
        case 3:
          return;
        default:
          System.out.println("Invalid option. Please try again.");
      }
    }
  }
}
