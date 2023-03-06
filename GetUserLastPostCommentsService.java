import Dto.CommentDto;
import Dto.PostDto;
import Dto.UserDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.telegram.telegrambots.meta.api.objects.User;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class GetUserLastPostCommentsService {
    private static final String USERS_URL = "https://jsonplaceholder.typicode.com/users";

    @SneakyThrows
    public static UserDto getUserDto(String username) {
        try {
            String response = Jsoup.connect(USERS_URL+"?username="+username).ignoreContentType(true).get().body().text();
            List<UserDto> responseDtos = convertUserResponseToList(response);
            return responseDtos.stream()
                    .map(dto -> new UserDto(dto.getId(), dto.getName(), dto.getUsername(), dto.getEmail(), dto.getAddress(), dto.getPhone(), dto.getWebsite(), dto.getCompany()))
                    .collect(Collectors.toList()).get(0);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<UserDto> convertUserResponseToList(String response) {
        Type type = TypeToken.getParameterized(List.class, UserDto.class).getType();
        Gson gson = new Gson();
        return gson.fromJson(response, type);
    }

    @SneakyThrows
    public static PostDto getUserLastPost(UserDto userDto) {
        List<PostDto> postDtos = PostResponseInList.getPostList("https://jsonplaceholder.typicode.com/users/" + userDto.getId() + "/posts");
        return postDtos.get(postDtos.size() - 1);
    }

    public static void getUserLastPostComments(String username) {
        UserDto userDto = getUserDto(username);
        PostDto postDto = getUserLastPost(userDto);
        List<CommentDto> commentDtos = CommentResponseInList.getCommentList("https://jsonplaceholder.typicode.com/posts/" + postDto.getId() + "/comments");
        List<String> commentsBodies = commentDtos.stream()
                .map(CommentDto::getBody)
                .collect(Collectors.toList());
        System.out.println(commentsBodies);
        try {
            String fileName = "user-"+userDto.getId()+"-post-"+postDto.getId()+"-comments.json";
            boolean b = new File(fileName).createNewFile();
            OutputStream f = new FileOutputStream(fileName, true);
            OutputStreamWriter writer = new OutputStreamWriter(f);
            BufferedWriter out = new BufferedWriter(writer);
            for (String commentsBody : commentsBodies) {
                out.write(commentsBody);
                out.flush();
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
