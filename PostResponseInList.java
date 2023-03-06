import Dto.PostDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class PostResponseInList {
    private static final String POSTS_URL = "https://jsonplaceholder.typicode.com/posts";

    public static List<PostDto> getPostList(String url) {
        try {
            String response = Jsoup.connect(url).ignoreContentType(true).get().body().text();
            List<PostDto> responseDtos = convertPostResponseToList(response);
            return responseDtos.stream()
                    .map(dto -> new PostDto(dto.getUserId(), dto.getId(), dto.getTitle(), dto.getBody()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    private static List<PostDto> convertPostResponseToList(String response) {
        Type type = TypeToken.getParameterized(List.class, PostDto.class).getType();
        Gson gson = new Gson();
        return gson.fromJson(response, type);
    }
}
