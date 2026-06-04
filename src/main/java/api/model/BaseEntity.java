package api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseEntity {
    @JsonProperty("id")
    private int id;

    public BaseEntity() {
    }

    public BaseEntity(int id) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
