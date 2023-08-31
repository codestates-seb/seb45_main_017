package recipe.server.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
  private Long memberId;
  private String username;
  private String password;
}
