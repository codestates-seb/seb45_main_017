package recipe.server.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import recipe.server.member.dto.MemberDto;
import recipe.server.member.entity.Member;
import recipe.server.member.repository.MemberRepository;
import recipe.server.member.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

  private final MemberService memberService;

  @Autowired
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @PostMapping
  public Member createMember(@RequestBody MemberDto memberDto) {
    return memberService.createMember(memberDto);
  }

  @GetMapping("/{memberId}")
  public Member getMember(@PathVariable Long memberId) {
    return memberService.getMember(memberId);
  }

  @PatchMapping("/{memberId}")
  public Member updateMember(@PathVariable Long memberId, @RequestBody MemberDto memberDto) {
    return memberService.updateMember(memberId, memberDto);
  }

  @DeleteMapping("/{memberId}")
  public void deleteMember(@PathVariable Long memberId) {
    memberService.deleteMember(memberId);
  }
}