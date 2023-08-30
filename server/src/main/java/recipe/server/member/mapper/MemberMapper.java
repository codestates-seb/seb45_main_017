package recipe.server.member.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import recipe.server.member.entity.Member;
import recipe.server.member.dto.MemberDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
    Member memberPostDtoToMember(MemberDto.MemberPostDto requestBody);
    Member memberPatchDtoToMember(MemberDto.MemberPatchDto requestBody);
    MemberDto.MemberResponseDto memberToMemberResponse(Member member);
}
