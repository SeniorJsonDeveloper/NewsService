package project.newtrying.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.newtrying.models.entitites.User;

import java.util.List;
import java.util.Optional;

public interface DataBaseUserRepository extends JpaRepository<User,Long> {
    @Query(value = "select*from Project_2.user where user_name = ?",nativeQuery = true)
    Optional<User> findByName(String userName);
}
