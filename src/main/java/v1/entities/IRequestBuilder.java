package v1.entities;

public interface IRequestBuilder<T extends IRequestBuilder<?>> {
    T setKey(String key);

    String getUrl();
}
