import java.util.Map;
import java.util.Scanner;

public class MenuView {
    public void displayMenu(Map<Integer, MenuItem> menu){
        System.out.println("==========================");
        System.out.println("Selamat datang di BinarFud");
        System.out.println("==========================");
        System.out.println("Silahkan pilih makanan:");
        for (Map.Entry<Integer, MenuItem> entry : menu.entrySet()){
            System.out.println(entry.getKey() + ". " + entry.getValue().displayDetails());
        }
        System.out.println("99. Pesan dan Bayar");
        System.out.println("0. Keluar Aplikasi");
    }

    public void displayOrder(RestaurantOrder order){
        order.displayOrder();
    }

    public void displayReceipt(Order order, String paymentMethod){
        System.out.println("==========================");
        System.out.println("BinarFud");
        System.out.println("==========================");
        System.out.println("Terimakasih sudah memesan\ndi BinarFud\n");
        System.out.println("Dibawah ini adalah pesanan anda");
        for (OrderItem item : order.getItems()){
            System.out.println(item.getMenuItem().getName() + "\t" + item.getQuantity() + "\tRp. " + item.getTotalPrice() );
        }
        System.out.println("---------------------------+");
        System.out.println("Total\t\t\t" + order.getTotalPrice());
        System.out.println("Pembayaran: " + paymentMethod);
        System.out.println("==========================");
        System.out.println("Simpan struk ini sebagai\nbukti pembayaran");
        System.out.println("==========================");
    }

    public int displayConfirmation(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("==========================");
        System.out.println("1. Konfirmasi dan Bayar");
        System.out.println("2. Kembali ke Menu Utama");
        System.out.println("0. Keluar Aplikasi");
        System.out.print("Pilihan Anda: ");
        return scanner.nextInt();
    }

}
