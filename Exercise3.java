import Dto.TodoDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;


public class Exercise3 {
    private static final String USERS_URL = "https://jsonplaceholder.typicode.com/users";
    public static void allTodos(int id){
        try {
            String response = Jsoup.connect(USERS_URL+"/"+id+"/todos").ignoreContentType(true).get().body().text();
            List<TodoDto> responseDtos = convertTodoResponseToList(response);
            responseDtos.stream()
                    .map(dto -> new TodoDto(dto.getUserId(), dto.getId(), dto.getTitle(), dto.isCompleted()))
                    .filter(TodoDto::isCompleted)
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static List<TodoDto> convertTodoResponseToList(String response) {
        Type type = TypeToken.getParameterized(List.class, TodoDto.class).getType();
        Gson gson = new Gson();
        return gson.fromJson(response, type);
    }
}
