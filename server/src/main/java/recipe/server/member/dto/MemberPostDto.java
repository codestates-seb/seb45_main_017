package recipe.server.member.dto;

import javax.validation.constraints.NotBlank;

public class MemberPostDto {
  @NotBlank(message = "아이디는 공백이 아니어야 합니다.")
  private String memberId;

  @NotBlank(message = "비밀번호는 공백이 아니어야 합니다.")
  private String password;

  @NotBlank(message = "이름은 공백이 아니어야 합니다.")
  private String username;
}
