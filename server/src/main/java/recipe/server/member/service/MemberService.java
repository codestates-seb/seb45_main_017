package recipe.server.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import recipe.server.auth.utils.CustomAuthorityUtils;
import recipe.server.exception.BusinessLogicException;
import recipe.server.exception.ExceptionCode;
import recipe.server.member.dto.MemberDto;
import recipe.server.member.entity.Member;
import recipe.server.member.repository.MemberRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
  private final MemberRepository memberRepository;

  private final PasswordEncoder passwordEncoder;

  private final CustomAuthorityUtils authorityUtils;

  public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, CustomAuthorityUtils authorityUtils) {

    this.memberRepository = memberRepository;
    this.passwordEncoder = passwordEncoder;
    this.authorityUtils = authorityUtils;
  }

  public Member createMember(Member member) {

    // 이미 등록된 이메일인지 확인
    verifyExistsEmail(member.getEmail());


    // password 암호화
    String encryptedPassword = passwordEncoder.encode(member.getPassword());
    member.setPassword(encryptedPassword);

    //
    List<String> roles = authorityUtils.createRoles(member.getEmail());
    member.setRoles(roles);


    Member createMember = memberRepository.save(member);

    return createMember;
  }


  public Member updateMember(Member member) {
    Member findMember = findVerifiedMember(member.getMemberId());

    Optional.ofNullable(member.getUsername())
            .ifPresent(name -> findMember.setUsername(name));

//    findMember.setModifiedAt(LocalDateTime.now());

    return memberRepository.save(findMember);
  }

  public Member findMember(long memberId) {
    return findVerifiedMember(memberId);
  }

  public void deleteMember(long memberId) {
    Member findMember = findVerifiedMember(memberId);

    memberRepository.delete(findMember);
  }
  public Member findVerifiedMember(long memberId) {
    Optional<Member> optionalMember =
            memberRepository.findById(memberId);
    Member findMember =
            optionalMember.orElseThrow(() ->
                    new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    return findMember;
  }

  private void verifyExistsEmail(String email) {
    Optional<Member> member = memberRepository.findByEmail(email);
    if (member.isPresent())
      throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
  }
}
//@Service
//public class MemberService {
//
//  private final MemberRepository memberRepository;
//
//  @Autowired
//  public MemberService(MemberRepository memberRepository) {
//    this.memberRepository = memberRepository;
//  }
//
//  // 멤버 생성 메서드
//  public Member createMember(MemberDto.MemberPostDto memberPostDto) {
//    Member member = new Member();
//    member.setUsername(memberPostDto.getUsername());
//    member.setPassword(memberPostDto.getPassword());
//    return memberRepository.save(member);
//  }
//
//  // 멤버 조회 메서드
//  public Member findMember(Long memberId) {
//    return memberRepository.findById(memberId).orElse(null);
//  }
//
//  // 멤버 수정 메서드
//  public Member updateMember(Long memberId, MemberDto.MemberPatchDto memberPatchDto) {
//    Member existingMember = memberRepository.findById(memberId).orElse(null);
//    if (existingMember != null) {
//      existingMember.setUsername(memberPatchDto.getUsername());
//      existingMember.setPassword(memberPatchDto.getPassword());
//      return memberRepository.save(existingMember);
//    }
//    return null;
//  }
//
//  // 멤버 삭제 메서드
//  public void deleteMember(Long memberId) {
//    memberRepository.deleteById(memberId);
//  }
//}