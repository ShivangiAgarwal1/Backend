package colab.Backend.serviceImpl;

import colab.Backend.dto.CommentDto;
import colab.Backend.dto.PostDto;
import colab.Backend.repository.CommentRepository;
import colab.Backend.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceImplTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private PostServiceImpl postService;

    @Test
    public void testGetAllPosts() {
        // Mock data
        Object[] postObject1 = {1, "Title 1", "Content 1", "2004-08-19 16:21:06", "Author 1"};
        Object[] postObject2 = {2, "Title 2", "Content 2", "2004-02-19 16:21:06", "Author 2"};
        List<Object[]> postList = Arrays.asList(postObject1, postObject2);

        Object[] commentObject1 = {1, 2, "User1,User2,User3"};
        Object[] commentObject2 = {2, 3, "User4,User5,User6"};
        List<Object[]> commentDetails = Arrays.asList(commentObject1, commentObject2);

        // Mock repository methods
        when(postRepository.findAllPostsWithUsernames()).thenReturn(postList);
        when(commentRepository.countCommentsAndUserPreview()).thenReturn(commentDetails);

        // Invoke service method
        List<PostDto> result = postService.getAllPosts();

        // Assertions
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(3, result.get(1).getCommentCount());
        assertEquals("User1", result.get(0).getUserPreview()[0]);
    }

    @Test
    public void testGetCommentsForPost() {
        // Mock data
        int postId = 1;
        int pageNumber = 0;
        int pageSize = 2;
        Object[] commentObject1 = {1, "Content 1", "Author 1", "2004-08-19 16:21:06"};
        Object[] commentObject2 = {2, "Content 2", "Author 2", "2004-02-19 16:21:06"};
        List<Object[]> commentDetails = Arrays.asList(commentObject1, commentObject2);

        // Mock repository method
        when(commentRepository.findCommentsByPostIdWithUsernames(postId, pageNumber, pageSize)).thenReturn(commentDetails);

        // Invoke service method
        List<CommentDto> result = postService.getCommentsForPost(postId, pageNumber, pageSize);

        // Assertions
        assertEquals(2, result.size());
        assertEquals("Content 1", result.get(0).getContent());
        assertEquals("Author 2", result.get(1).getAuthor());
    }
}