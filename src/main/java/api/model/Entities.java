package api.model;

public enum Entities {
    Todos("/todos"),
    Posts("/posts");

    final String path;

    Entities(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
