import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class RestaurantController {
    private MenuView menuView;
    private Map<Integer, MenuItem> menu;
    private Scanner scanner;

    public RestaurantController(){
        menuView = new MenuView();
        menu = new HashMap<>();
        scanner = new Scanner(System.in);
        initializeMenu();
    }

    private void initializeMenu() {
        menu.put(1, new MenuItem("Nasi Goreng", 15000));
        menu.put(2, new MenuItem("Mie Goreng", 13000));
        menu.put(3, new MenuItem("Nasi + Ayam", 18000));
        menu.put(4, new MenuItem("Es Teh Manis", 3000));
        menu.put(5, new MenuItem("Es Jeruk", 5000));
    }

    public void run(){
        RestaurantOrder order = new RestaurantOrder();
        boolean running = true;
        while (running){
            try {
                menuView.displayMenu(menu);
                System.out.print("Pilihan Anda: ");
                int choice = scanner.nextInt();
                if (choice == 0){
                    System.out.println("Keluar dari aplikasi.");
                    running = false;
                }  else if (menu.containsKey(choice)) {
                    processItemOrder(order, menu.get(choice));
                } else if (choice == 99) {
                    processOrder(order);
                    running = false;
                }else {
                    System.out.println("Pilihan tidak valid.");
                }
            }catch (InputMismatchException e){
                System.out.println("Masukan harus berupa Angka");
                scanner.nextLine();
            }
        }
    }

    private void processItemOrder(RestaurantOrder order, MenuItem menuItem) {
        System.out.println("\n==========================");
        System.out.println("Berapa Pesanan Anda");
        System.out.println("==========================");
        System.out.print("Qty => ");
        int qty = scanner.nextInt();
        if (qty > 0){
            order.addItem(new OrderItem(menuItem, qty));
        } else {
            System.out.println("Jumlah pesanan harus lebih dari 0.");
        }
    }

    private void processOrder(RestaurantOrder order) {
        menuView.displayOrder(order);
        int choice = menuView.displayConfirmation();
        if (choice == 1){
            completeOrder(order);
        } else if (choice == 2) {
            order.items.clear();
            run();
        }else {
            System.out.println("Keluar dari aplikasi.");
        }
    }

    private void completeOrder(RestaurantOrder order) {
        scanner.nextLine(); // consume newline
        System.out.print("Metode pembayaran (Cash/Card): ");
        String paymentMethod = scanner.nextLine();
        menuView.displayReceipt(order, paymentMethod);

        try (PrintWriter writer = new PrintWriter(new FileWriter("struk_pembayaran.txt"))) {
            writer.println("==========================");
            writer.println("BinarFud");
            writer.println("==========================\n");
            writer.println("Terimakasih sudah memesan\ndi BinarFud\n");
            writer.println("Dibawah ini adalah pesanan anda\n");
            for (OrderItem item : order.getItems()) {
                writer.println(item.getMenuItem().getName() + "\t" + item.getQuantity() + "\tRp. " + item.getTotalPrice());
            }
            writer.println("---------------------------+");
            writer.println("Total\t\t\t" + order.getTotalPrice());
            writer.println("\nPembayaran: " + paymentMethod +"\n");
            writer.println("==========================");
            writer.println("Simpan struk ini sebagai\nbukti pembayaran");
            writer.println("==========================");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan struk pembayaran: " + e.getMessage());
        }
    }
}
