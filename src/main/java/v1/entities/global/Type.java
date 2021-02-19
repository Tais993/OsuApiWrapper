package v1.entities.global;

public enum Type {
    USERNAME("string"),
    USER_ID("id");

    private final String title;

    Type(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
