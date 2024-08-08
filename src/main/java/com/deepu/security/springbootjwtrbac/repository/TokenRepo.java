package com.deepu.security.springbootjwtrbac.repository;

import com.deepu.security.springbootjwtrbac.entity.OurUsers;
import com.deepu.security.springbootjwtrbac.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepo extends JpaRepository<Token,Integer> {
    Optional<Token> findByToken(String token);
    List<Token> findByOurUsers(OurUsers ourUsers);
    void deleteTokensByOurUsers(OurUsers ourUsers);
}
