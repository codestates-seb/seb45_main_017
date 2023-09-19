package recipe.server.member.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import recipe.server.member.dto.SingleResponseDto;
import recipe.server.member.dto.MemberDto;
import recipe.server.member.entity.Member;
import recipe.server.member.mapper.MemberMapper;
import recipe.server.member.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/member")
@Validated
@Slf4j
public class MemberController {
  private final MemberService memberService;
  private final MemberMapper mapper;

  public MemberController(MemberService memberService, MemberMapper mapper) {
    this.memberService = memberService;
    this.mapper = mapper;
  }

  @PostMapping
  public ResponseEntity postMember(@RequestBody MemberDto.MemberPostDto memberDto) {
    Member member = mapper.memberPostDtoToMember(memberDto);
    Member crated = memberService.createMember(member);

    MemberDto.MemberResponseDto responseDto = mapper.memberToMemberResponseDto(crated);

    return new ResponseEntity(responseDto, HttpStatus.CREATED);
  }

  @PatchMapping("/{member-id}")
  public ResponseEntity patchMember(
          @PathVariable("member-id") @Positive long memberId, @RequestBody MemberDto.MemberPatchDto memberPatchDto) {
    memberPatchDto.setMemberId(memberId);

    Member member = memberService.updateMember(mapper.memberPatchDtoToMember(memberPatchDto));

    return new ResponseEntity<>(new SingleResponseDto<>(mapper.memberToMemberResponseDto(member)), HttpStatus.OK);
  }

  @GetMapping("/{member-id}")
  public ResponseEntity getMember(
          @PathVariable("member-id") @Positive long memberId) {
    Member member = memberService.findMember(memberId);
    return new ResponseEntity<>(new SingleResponseDto<>(mapper.memberToMemberResponseDto(member)), HttpStatus.OK);
  }

  @DeleteMapping("/{member-id}")
  public ResponseEntity deleteMember(
          @PathVariable("member-id") @Positive long memberId) {
    memberService.deleteMember(memberId);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}