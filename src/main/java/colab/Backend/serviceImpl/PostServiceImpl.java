package colab.Backend.serviceImpl;

import colab.Backend.dto.CommentDto;
import colab.Backend.dto.PostDto;
import colab.Backend.repository.CommentRepository;
import colab.Backend.repository.PostRepository;
import colab.Backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;




    public List<PostDto> getAllPosts() {

        try{
            List<Object[]> postList = postRepository.findAllPostsWithUsernames();
            List<Object[]> commentDetails = commentRepository.countCommentsAndUserPreview();

                Map<Integer, PostDto> postMap = postList.stream()
                        .map(this::convertToPostDto)
                        .collect(Collectors.toMap(PostDto::getId, postDto -> postDto));

            // Update postMap with comment count and user preview
            commentDetails.forEach(commentObject -> {
                int postId = (int) commentObject[0];
                int commentCount = commentObject[1] != null ? ((Number) commentObject[1]).intValue() : 0;
                String userPreview = (String) commentObject[2];
                PostDto postDto = postMap.get(postId);
                if (postDto != null) {
                    postDto.setCommentCount(commentCount);
                    postDto.setUserPreview(Arrays.stream(userPreview.split(","))
                            .limit(3) // Limit to the first three elements
                            .toArray(String[]::new));

                }
            });

            return new ArrayList<>(postMap.values());
        }catch (Exception e){
            log.error("Error occurred while fetching all posts", e);
        }
       return new ArrayList<>();
    }


    public List<CommentDto> getCommentsForPost(int postId, int pageNumber, int pageSize) {

        List<CommentDto> commentDtoList = new ArrayList<>();

        try{

            List<Object[]> commentDetails = commentRepository.findCommentsByPostIdWithUsernames(postId, pageNumber, pageSize);

            // Mapping commentDetails to CommentDto objects
             commentDtoList = commentDetails.stream()
                    .map(this::convertToCommentDto)
                    .collect(Collectors.toList());

            return commentDtoList;

        } catch (Exception e){
            log.error("Error occurred while fetching comments for post", e);
        }
      return commentDtoList;
    }



    private PostDto convertToPostDto(Object[] postObject) {
            PostDto postDto = new PostDto();
            postDto.setId((int) postObject[0]);
            postDto.setTitle((String) postObject[1]);
            postDto.setContent((String) postObject[2]);
            postDto.setCreatedAt( formatDateString((String) postObject[3]));
            postDto.setAuthor((String) postObject[4]);
            return postDto;

    }

    public  String formatDateString(String dateString) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("E, d MMM yyyy HH:mm:ss 'GMT'", Locale.ENGLISH);
        LocalDateTime dateTime = LocalDateTime.parse(dateString, inputFormatter);

        return dateTime.format(outputFormatter);
    }

    private CommentDto convertToCommentDto(Object[] commentDetails) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId((int) commentDetails[0]);
        commentDto.setContent((String) commentDetails[1]);
        commentDto.setAuthor((String) commentDetails[2]);
        commentDto.setCreatedAt( formatDateString((String) commentDetails[3]));

        return commentDto;
    }
}

