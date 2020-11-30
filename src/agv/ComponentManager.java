package agv;

import agv.Motor.Detectie;
import agv.Motor.Motor;

import java.util.*;

public class ComponentManager {
    public static final Class<? extends Component>[] COMPONENTS = new Class[]{Motor.class, Detectie.class};
    private final ArrayList<Component> components = new ArrayList<>();
    private final List<Class<? extends Component>> enabledComponents = new ArrayList<Class<? extends Component>>();

    public ComponentManager() {
        for (Class<? extends Component> moduleClass : COMPONENTS) {
            try {
                this.components.add(moduleClass.getConstructor(Component.class).newInstance());
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
    public Component getModule(String name) {
        for (Component component : this.components) {
            if (!component.getName().equals(name)) continue;
            return component;
        }
        return null;
    }

    public Set<Component> getEnabledModules() {
        HashSet<Component> enabledModules = new HashSet<>();
        for (Class<? extends Component> moduleClazz : this.enabledComponents) {
            enabledModules.add(this.getModule(moduleClazz.toString()));
        }
        return enabledModules;
    }

    public Set<Component> getModules() {
        HashSet<Component> allModules = new HashSet<Component>();
        allModules.addAll(this.components);
        return allModules;
    }
}
