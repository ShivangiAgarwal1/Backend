package colab.Backend.repository;

import colab.Backend.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    @Query(value = "SELECT c.post_id, COUNT(c.id) AS commentCount, GROUP_CONCAT(DISTINCT u.username) AS userPreview FROM Comment c JOIN User u ON c.user_id = u.id GROUP BY c.post_id", nativeQuery = true)
    List<Object[]> countCommentsAndUserPreview();


    @Query(value = "SELECT c.id, c.content, u.username, strftime('%Y-%m-%d %H:%M:%S', datetime(c.created_at)) AS formatted_date, c.post_id FROM Comment c JOIN User u ON c.user_id = u.id WHERE c.post_id = :postId LIMIT:perPage OFFSET (:pageNumber-1)* :perPage", nativeQuery =true)
    List<Object[]> findCommentsByPostIdWithUsernames(int postId, int pageNumber, int perPage);

}
