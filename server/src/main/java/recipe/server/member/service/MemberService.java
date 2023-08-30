package recipe.server.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipe.server.member.dto.MemberDto;
import recipe.server.member.entity.Member;
import recipe.server.member.repository.MemberRepository;

@Service
public class MemberService {

  private final MemberRepository memberRepository;

  @Autowired
  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  // 멤버 생성 메서드
  public Member createMember(MemberDto memberDto) {
    Member member = new Member();
    member.setUsername(memberDto.getUsername());
    member.setPassword(memberDto.getPassword());
    return memberRepository.save(member);
  }

  // 멤버 조회 메서드
  public Member getMember(Long memberId) {
    return memberRepository.findById(memberId).orElse(null);
  }

  // 멤버 수정 메서드
  public Member updateMember(Long memberId, MemberDto memberDto) {
    Member existingMember = memberRepository.findById(memberId).orElse(null);
    if (existingMember != null) {
      existingMember.setUsername(memberDto.getUsername());
      existingMember.setPassword(memberDto.getPassword());
      return memberRepository.save(existingMember);
    }
    return null;
  }

  // 멤버 삭제 메서드
  public void deleteMember(Long memberId) {
    memberRepository.deleteById(memberId);
  }
}
