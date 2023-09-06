package recipe.server.member.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MemberDto {

  @Getter
  @Setter
  public static class MemberPostDto {
    @NotBlank
    @Email
    public String email;

    @NotBlank(message = "이름은 공백이 아니어야 합니다.")
    public String username;

    @NotBlank
    public String password;
  }

  @Getter
  @Setter
  public static class MemberPatchDto {
    public long memberId;
    public String email;
    public String username;
    public String password;
  }

  @Getter
  @Setter
  public static class MemberResponseDto{
    private long memberId;
    private String email;
    private String username;
  }

  @Getter
  @Setter
  public static class MemberLoginDto {
    private String username;
    private String password;
  }

}
