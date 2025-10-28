package api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseEntity {
    @JsonProperty("id")
    int id;

    public BaseEntity() {
    }

    public BaseEntity(int id) {

    }
}
