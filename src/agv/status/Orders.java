package agv.status;

import java.util.ArrayList;
import java.util.Arrays;

public class Orders {
    private ArrayList<String> orders = new ArrayList<>();

    public boolean hasOrder() {
        return !this.orders.isEmpty();
    }

    public void setOrder(String[] theOrder) {
        this.orders.clear();
        this.orders.addAll(Arrays.asList(theOrder));
    }

    public void add(String order) {
        this.orders.add(order);
    }

    public void remove(String order) {
        this.orders.remove(order);
    }

    public String getNextPart() {
        if (this.orders.isEmpty()) {
            StatusManager.setStatus(Status.StatusType.TAAK_VOLTOOID, false);
        }
        String color = this.orders.get(0);
        this.orders.remove(0);
        return color;

    }

    public boolean isEmpty() {
        return this.orders.isEmpty();
    }

    public ArrayList<String> getOrders() {
        return this.orders;
    }
}
