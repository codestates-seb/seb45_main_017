package recipe.server.member.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import recipe.server.member.dto.SingleResponseDto;
import recipe.server.member.dto.MemberDto;
import recipe.server.member.entity.Member;
import recipe.server.member.mapper.MemberMapper;
import recipe.server.member.service.MemberService;
import recipe.server.utils.UriCreator;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;

@RestController
@RequestMapping("/member")
@Validated
@Slf4j
public class MemberController {
  private final static String MEMBER_DEFAULT_URL = "/member";
  private final MemberService memberService;
  private final MemberMapper mapper;

  public MemberController(MemberService memberService, MemberMapper mapper) {
    this.memberService = memberService;
    this.mapper = mapper;
  }

  @PostMapping
  public ResponseEntity postMember(@Valid @RequestBody MemberDto.MemberPostDto requestBody) {
    Member member = mapper.memberPostDtoToMember(requestBody);

    Member createdMember = memberService.createMember(member);
    URI location = UriCreator.createUri(MEMBER_DEFAULT_URL, createdMember.getMemberId());

    return ResponseEntity.created(location).build();
  }

  @PatchMapping("/{member-id}")
  public ResponseEntity patchMember(
          @PathVariable("member-id") @Positive long memberId,
          @Valid @RequestBody MemberDto.MemberPatchDto requestBody) {
    requestBody.setMemberId(memberId);

    Member member =
            memberService.updateMember(mapper.memberPatchDtoToMember(requestBody));

    return new ResponseEntity<>(
            new SingleResponseDto<>(mapper.memberToMemberResponse(member)),
            HttpStatus.OK);
  }

  @GetMapping("/{member-id}")
  public ResponseEntity getMember(
          @PathVariable("member-id") @Positive long memberId) {
    Member member = memberService.findMember(memberId);
    return new ResponseEntity<>(
            new SingleResponseDto<>(mapper.memberToMemberResponse(member))
            , HttpStatus.OK);
  }

  @DeleteMapping("/{member-id}")
  public ResponseEntity deleteMember(
          @PathVariable("member-id") @Positive long memberId) {
    memberService.deleteMember(memberId);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}