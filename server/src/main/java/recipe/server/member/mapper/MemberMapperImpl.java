package recipe.server.member.mapper;

import org.springframework.stereotype.Component;
import recipe.server.member.dto.MemberDto;
import recipe.server.member.entity.Member;

@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member memberPostDtoToMember(MemberDto.MemberPostDto requestBody) {
        Member member = new Member();
        member.setEmail(requestBody.getEmail());
        member.setUsername(requestBody.getUsername());
        member.setPassword(requestBody.getPassword());
        return member;
    }

    @Override
    public Member memberPatchDtoToMember(MemberDto.MemberPatchDto requestBody) {
        Member member = new Member();
        member.setEmail(requestBody.getEmail());
        member.setUsername(requestBody.getUsername());
        member.setPassword(requestBody.getPassword());
        return member;
    }

    @Override
    public MemberDto.MemberResponseDto memberToMemberResponseDto(Member member) {
        MemberDto.MemberResponseDto responseDto = new MemberDto.MemberResponseDto();
        responseDto.setMemberId(member.getMemberId());
        responseDto.setEmail(member.getEmail());
        responseDto.setUsername(member.getUsername());
        return responseDto;
    }
}