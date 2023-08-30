package recipe.server.member.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class MemberDto {

  @Getter
  @Setter
  public static class MemberPostDto {

    @NotBlank(message = "이메일은 공백이 아니어야 합니다.")
    @Pattern(regexp = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+$")
    public String email;

    @NotBlank(message = "이름은 공백이 아니어야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9_]*$")
    public String username;

    @NotBlank(message = "비밀번호는 공백이 아니어야 합니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
    public String password;
  }

  @Getter
  @Setter
  public static class MemberPatchDto {
    public long memberId;

    @NotBlank(message = "이메일은 공백이 아니어야 합니다.")
    @Pattern(regexp = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+$")
    public String email;

    @NotBlank(message = "이름은 공백이 아니어야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9_]*$")
    public String username;

    @NotBlank(message = "비밀번호는 공백아 아니어야 합니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
    public String password;
  }

  @Getter
  @Setter
  public static class MemberResponseDto {
    private long memberId;

    @NotBlank(message = "이메일은 공백이 아니어야 합니다.")
    @Pattern(regexp = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+$")
    private String email;

    @NotBlank(message = "이름은 공백이 아니어야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9_]*$")
    private String username;
  }
}