package recipe.server.member.mapper;

import org.springframework.stereotype.Component;
import recipe.server.member.entity.Member;
import recipe.server.member.dto.MemberDto;

@Component
public interface MemberMapper {
    Member memberPostDtoToMember(MemberDto.MemberPostDto requestBody);
    Member memberPatchDtoToMember(MemberDto.MemberPatchDto requestBody);
    MemberDto.MemberResponseDto memberToMemberResponseDto(Member member);
}
