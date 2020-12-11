package agv.status;

import agv.Component;
import agv.test.PiJavaUltrasonic;
import agv.utils.Database;
import agv.utils.Multithread;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.ResultSet;

public class OrderManager extends Component {
    public OrderManager() {
        super("OrderManager");
    }

    @Override
    public void onEnable() {
        Multithread.execute(this::start);
    }
    int delay = 1000;
    public void start() {
        if (!this.isEnabled())
            return;
        while (this.isEnabled()) {
            try {
                String order = getOrdersFromDatabase();
                if (order.equals("")) {
                    debug("Geen order in de database!!!!");
                    Thread.sleep(delay);
                    continue;
                }
                clearOrderDatabase();
                debug("Order found: " + order);
                Orders orders = new Orders();
                if(orders.isEmpty())
                    orders.setOrder(order.split(","));

                Thread.sleep(delay);
            } catch (Exception e) {
                e.printStackTrace();
            }
        StatusManager.setStatus(Status.StatusType.GEEN_DASHBOARD_CONNECTIE, true);
        }
    }

    private void clearOrderDatabase() {
        Database db = new Database();
        db.connect();
        db.query("DELETE FROM `orders`");
        db.disconnect();
    }

    private String getOrdersFromDatabase() {
        try {
            Database db = new Database();
            db.connect();
            ResultSet result = db.get("select * from `orders`");
            String order = "";
            while (result.next()) {
                order = result.getString("orders");
                break;
            }
            db.disconnect();
            return order;

        } catch (Exception ignored) {
        }
        return "";
    }
}
