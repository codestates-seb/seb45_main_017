package recipe.server.member.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
=======
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
>>>>>>> origin/be
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import recipe.server.member.dto.SingleResponseDto;
import recipe.server.member.dto.MemberDto;
import recipe.server.member.entity.Member;
import recipe.server.member.mapper.MemberMapper;
import recipe.server.member.service.MemberService;
<<<<<<< HEAD
import recipe.server.utils.UriCreator;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
=======

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
>>>>>>> origin/be

@RestController
@RequestMapping("/member")
@Validated
@Slf4j
public class MemberController {
<<<<<<< HEAD
  private final static String MEMBER_DEFAULT_URL = "/member";
=======
>>>>>>> origin/be
  private final MemberService memberService;
  private final MemberMapper mapper;

  public MemberController(MemberService memberService, MemberMapper mapper) {
    this.memberService = memberService;
    this.mapper = mapper;
  }

  @PostMapping
<<<<<<< HEAD
  public ResponseEntity postMember(@Valid @RequestBody MemberDto.MemberPostDto requestBody) {
    Member member = mapper.memberPostDtoToMember(requestBody);

    Member createdMember = memberService.createMember(member);
    URI location = UriCreator.createUri(MEMBER_DEFAULT_URL, createdMember.getMemberId());

    return ResponseEntity.created(location).build();
=======
  public ResponseEntity postMember(@RequestBody MemberDto.MemberPostDto memberDto) {
    Member member = mapper.memberPostDtoToMember(memberDto);
    Member crated = memberService.createMember(member);

    MemberDto.MemberResponseDto responseDto = mapper.memberToMemberResponseDto(crated);

    return new ResponseEntity(responseDto, HttpStatus.CREATED);
>>>>>>> origin/be
  }

  @PatchMapping("/{member-id}")
  public ResponseEntity patchMember(
<<<<<<< HEAD
          @PathVariable("member-id") @Positive long memberId,
          @Valid @RequestBody MemberDto.MemberPatchDto requestBody) {
    requestBody.setMemberId(memberId);

    Member member =
            memberService.updateMember(mapper.memberPatchDtoToMember(requestBody));

    return new ResponseEntity<>(
            new SingleResponseDto<>(mapper.memberToMemberResponse(member)),
            HttpStatus.OK);
=======
          @PathVariable("member-id") @Positive long memberId, @RequestBody MemberDto.MemberPatchDto memberPatchDto) {
    memberPatchDto.setMemberId(memberId);

    Member member = memberService.updateMember(mapper.memberPatchDtoToMember(memberPatchDto));

    return new ResponseEntity<>(new SingleResponseDto<>(mapper.memberToMemberResponseDto(member)), HttpStatus.OK);
>>>>>>> origin/be
  }

  @GetMapping("/{member-id}")
  public ResponseEntity getMember(
          @PathVariable("member-id") @Positive long memberId) {
    Member member = memberService.findMember(memberId);
<<<<<<< HEAD
    return new ResponseEntity<>(
            new SingleResponseDto<>(mapper.memberToMemberResponse(member))
            , HttpStatus.OK);
=======
    return new ResponseEntity<>(new SingleResponseDto<>(mapper.memberToMemberResponseDto(member)), HttpStatus.OK);
>>>>>>> origin/be
  }

  @DeleteMapping("/{member-id}")
  public ResponseEntity deleteMember(
          @PathVariable("member-id") @Positive long memberId) {
    memberService.deleteMember(memberId);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}