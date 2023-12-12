public class InteractiveObject {

    private final InteractiveType type;
    private final String name;

    public InteractiveObject(InteractiveType type, String name) {
        this.type = type;
        this.name = name;
    }

    public InteractiveType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
