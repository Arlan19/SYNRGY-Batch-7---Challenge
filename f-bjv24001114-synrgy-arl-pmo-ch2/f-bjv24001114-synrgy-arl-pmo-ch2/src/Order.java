import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
abstract class Order {
    protected List<OrderItem> items = new ArrayList<>();

    public void addItem(OrderItem item){
        items.add(item);
    }

    public double getTotalPrice(){
        double total = 0;
        for (OrderItem item : items){
            total += item.getTotalPrice();
        }
        return total;
    }

    public abstract void displayOrder();
}
