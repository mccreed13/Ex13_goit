package Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@AllArgsConstructor
public class TodoDto {
    private int userId;
    private int id;
    private String title;
    private boolean completed;

}
