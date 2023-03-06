import Dto.CommentDto;
import Dto.UserDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class CommentResponseInList {
    private static final String COMMENTS_URL = "https://jsonplaceholder.typicode.com/comments";

    public static List<CommentDto> getCommentList(String url) {
        try {
            String response = Jsoup.connect(url).ignoreContentType(true).get().body().text();
            List<CommentDto> responseDtos = convertCommentResponseToList(response);
            return responseDtos.stream()
                    .map(dto -> new CommentDto(dto.getPostId(), dto.getName(), dto.getEmail(), dto.getBody()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static List<CommentDto> convertCommentResponseToList(String response) {
        Type type = TypeToken.getParameterized(List.class, CommentDto.class).getType();
        Gson gson = new Gson();
        return gson.fromJson(response, type);
    }
}
