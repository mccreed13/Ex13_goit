import Dto.UserDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class UserResponseInList {
    private static final String USERS_URL = "https://jsonplaceholder.typicode.com/users";

    public static List<UserDto> getUserList() {
        try {
            String response = Jsoup.connect(USERS_URL).ignoreContentType(true).get().body().text();
            List<UserDto> responseDtos = convertUserResponseToList(response);
            return responseDtos.stream()
                    .map(dto -> new UserDto(dto.getId(), dto.getName(), dto.getUsername(), dto.getEmail(), dto.getAddress(),dto.getPhone(),dto.getWebsite(),dto.getCompany()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static List<UserDto> convertUserResponseToList(String response) {
        Type type = TypeToken.getParameterized(List.class, UserDto.class).getType();
        Gson gson = new Gson();
        return gson.fromJson(response, type);
    }
}
