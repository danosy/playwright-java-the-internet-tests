package api.model.enums;

public enum Endpoints {
    AUTH_LOGIN("/auth", Resource.Login);

    final String path;
    final Resource resource;

    Endpoints(String path, Resource resource) {
        this.path = path;
        this.resource = resource;
    }

    public String getFullPath() {
        return path + resource.getResource();
    }
}
