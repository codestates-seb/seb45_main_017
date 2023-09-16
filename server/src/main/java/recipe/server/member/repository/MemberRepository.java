package recipe.server.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import recipe.server.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
  
    Optional<Member> findByEmailAndOauthType(String email, String oauthType);

}