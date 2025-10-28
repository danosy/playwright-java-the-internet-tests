package api.client;

import api.config.APIConfig;
import api.model.Entities;
import api.model.Todo;
import api.utils.JsonUtils;
import io.restassured.response.Response;

import java.util.List;

public class TodosClient extends BaseAPIClient {
    public List<Todo> getAll() {
        Response response = get(APIConfig.BASE_URL + Entities.Todos.getPath());
        return JsonUtils.convertToList(response, Todo.class);
    }

    public Todo getById(int id) {
        Response response = get(APIConfig.BASE_URL + Entities.Todos.getPath() + "/" + id);
        return response.as(Todo.class);
    }
}
