package v1.entities.global;

public enum TypeV1 {
    USERNAME("string"),
    USER_ID("id");

    private final String title;

    TypeV1(String title) {
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
