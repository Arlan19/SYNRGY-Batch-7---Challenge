import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItem {
    private MenuItem menuItem;
    private int quantity;
    public double getTotalPrice(){
        return menuItem.getPrice() * quantity;
    }
}
