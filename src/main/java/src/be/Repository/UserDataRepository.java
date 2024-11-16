package src.be.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import src.be.Models.UserData;

import java.util.List;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Integer> {
    public UserData findByUsername(String username);
    public UserData findByEmail(String email);

    public boolean existsByEmail(String email);
    public boolean existsByUsername(String username);
}
