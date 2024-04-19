package com.binar.batch7;

public class RestaurantOrder extends Order {
    @Override
    public void displayOrder() {
        System.out.println("\n==========================");
        System.out.println("Konfirmasi & Pembayaran");
        System.out.println("==========================");
        for (OrderItem item : items) {
            System.out.println(item.getMenuItem().getName().orElse("Unknown") + "\t" + item.getQuantity() + "\t" + item.getTotalPrice());
        }
        System.out.println("---------------------------+");
        System.out.println("Total\t\t\tRp. " + getTotalPrice());
    }
}
