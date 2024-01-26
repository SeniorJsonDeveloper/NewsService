package project.newtrying.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.newtrying.models.entitites.User;

import java.util.List;

@Repository
public interface DataBaseUserRepository extends JpaRepository<User,Long> {
    @Override
    @EntityGraph("news")
    @NonNull
    List<User> findAll();
}
