package colab.Backend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private String author;
    private int commentCount;
    private String content;
    private String createdAt;
    private int id;
    private String title;
    private String[] userPreview;

}
