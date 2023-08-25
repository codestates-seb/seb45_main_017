package recipe.server.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MemberService {

  private final MemberRepository memberRepository;

  @Autowired
  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  public MemberDto getMemberById(Long memberId) {
    Member member = memberRepository.findById(memberId).orElse(null);
    if (member == null) {
      return null;
    }

    MemberDto memberDto = new MemberDto();
    memberDto.setId(member.getId());
    memberDto.setUsername(member.getUsername());
    memberDto.setPassword(member.getPassword());

    return memberDto;
  }

  public List<MemberDto> getAllMembers() {
    List<Member> members = memberRepository.findAll();

    List<MemberDto> memberDtos = members.stream()
            .map(member -> {
              MemberDto memberDto = new MemberDto();
              memberDto.setId(member.getId());
              memberDto.setUsername(member.getUsername());
              memberDto.setPassword(member.getPassword());

              return memberDto;
            })
            .collect(Collectors.toList());

    return memberDtos;
  }
}