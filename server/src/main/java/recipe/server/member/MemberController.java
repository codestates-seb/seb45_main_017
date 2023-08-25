package recipe.server.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

  @Autowired
  private MemberRepository memberRepository;

  @GetMapping("/{memberId}")
  public Member getMember(@PathVariable Long memberId) {
    return memberRepository.findById(memberId).orElse(null);
  }

  @GetMapping("/list")
  public List<Member> getAllMembers() {
    return memberRepository.findAll();
  }

  @PostMapping("/")
  public Member createMember(@RequestBody Member member) {
    return memberRepository.save(member);
  }

  @PutMapping("/{memberId}")
  public Member updateMember(@PathVariable Long memberId, @RequestBody Member member) {
    member.setId(memberId);
    return memberRepository.save(member);
  }

  @DeleteMapping("/{memberId}")
  public void deleteMember(@PathVariable Long memberId) {
    memberRepository.deleteById(memberId);
  }
}