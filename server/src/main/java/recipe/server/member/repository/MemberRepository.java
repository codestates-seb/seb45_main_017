package recipe.server.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import recipe.server.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}