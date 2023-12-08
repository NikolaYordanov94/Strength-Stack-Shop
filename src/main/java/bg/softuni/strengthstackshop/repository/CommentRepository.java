package bg.softuni.strengthstackshop.repository;

import bg.softuni.strengthstackshop.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<List<Comment>> findAllByCommentDateBefore(LocalDateTime dateBefore);

}
