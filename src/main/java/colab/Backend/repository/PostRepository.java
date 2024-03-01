package colab.Backend.repository;

import colab.Backend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(value = "SELECT p.id,p.title, p.content, strftime('%Y-%m-%d %H:%M:%S', datetime(p.created_at)) AS formatted_date, u.username AS author FROM Post p JOIN User u ON p.user_id = u.id", nativeQuery = true)
    List<Object[]> findAllPostsWithUsernames();
}

