package agv;

public class Component {

    private final String name;
    private boolean enabled;
    private boolean showdebug;

    public Component(String name) {
        this.name = name;

    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (this.enabled) {
            this.onEnable();
        } else {
            this.onDisable();
        }
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public String getName() {
        return this.name;
    }

    public void onEnable() {
    }
    public void setDebugOutput(boolean b){
        this.showdebug = b;
    }
    public void debug(String debug) {
        if (showingDebug())
            System.out.println("[DEBUG] [" + this.getName() + "]: " + debug);
    }

    public boolean showingDebug() {
        return this.showdebug;
    }

    public void onDisable() {
    }
}
