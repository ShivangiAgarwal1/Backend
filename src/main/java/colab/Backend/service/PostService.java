package colab.Backend.service;

import colab.Backend.dto.CommentDto;
import colab.Backend.dto.PostDto;
import java.util.List;


public interface PostService {
    public List<PostDto> getAllPosts();
    public List<CommentDto> getCommentsForPost(int postId, int page, int perPage);

}
