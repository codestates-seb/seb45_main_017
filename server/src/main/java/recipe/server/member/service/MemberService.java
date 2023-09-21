package recipe.server.member.service;

<<<<<<< HEAD
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
>>>>>>> origin/be
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import recipe.server.auth.utils.CustomAuthorityUtils;
import recipe.server.exception.BusinessLogicException;
import recipe.server.exception.ExceptionCode;
<<<<<<< HEAD
import recipe.server.member.entity.Member;
import recipe.server.member.repository.MemberRepository;

=======
import recipe.server.member.dto.MemberDto;
import recipe.server.member.entity.Member;
import recipe.server.member.repository.MemberRepository;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
>>>>>>> origin/be
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
  private final MemberRepository memberRepository;
<<<<<<< HEAD
  private final PasswordEncoder passwordEncoder;
  private final CustomAuthorityUtils authorityUtils;

  public MemberService(MemberRepository memberRepository,
                       PasswordEncoder passwordEncoder,
                       CustomAuthorityUtils authorityUtils) {
=======

  private final PasswordEncoder passwordEncoder;

  private final CustomAuthorityUtils authorityUtils;

  public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, CustomAuthorityUtils authorityUtils) {

>>>>>>> origin/be
    this.memberRepository = memberRepository;
    this.passwordEncoder = passwordEncoder;
    this.authorityUtils = authorityUtils;
  }

  public Member createMember(Member member) {
<<<<<<< HEAD
    verifyExistsEmail(member.getEmail());

    String encryptedPassword = passwordEncoder.encode(member.getPassword());
    member.setPassword(encryptedPassword);

    List<String> roles = authorityUtils.createRoles(member.getEmail());
    member.setRoles(roles);

    Member savedMember = memberRepository.save(member);

    return savedMember;
  }

  public Member updateMember(Member member) {
    Member findMember = findVerifiedMember(member.getMemberId());

    Optional.ofNullable(member.getEmail())
            .ifPresent(email -> findMember.setEmail(email));
    Optional.ofNullable(member.getUsername())
            .ifPresent(name -> findMember.setUsername(name));
    Optional.ofNullable(member.getPassword())
            .ifPresent(password -> findMember.setPassword(password));
    return memberRepository.save(findMember);
  }

  public Member findMember(long memberId) {
    return findVerifiedMember(memberId);
  }

  public void deleteMember(long memberId) {
=======

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

  public Member findMember(Long memberId) {
    return findVerifiedMember(memberId);
  }

  public void deleteMember(Long memberId) {
>>>>>>> origin/be
    Member findMember = findVerifiedMember(memberId);

    memberRepository.delete(findMember);
  }
<<<<<<< HEAD

  public Member findVerifiedMember(long memberId) {
=======
  public Member findVerifiedMember(Long memberId) {
>>>>>>> origin/be
    Optional<Member> optionalMember =
            memberRepository.findById(memberId);
    Member findMember =
            optionalMember.orElseThrow(() ->
                    new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    return findMember;
  }

<<<<<<< HEAD
=======


>>>>>>> origin/be
  private void verifyExistsEmail(String email) {
    Optional<Member> member = memberRepository.findByEmail(email);
    if (member.isPresent())
      throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
  }
<<<<<<< HEAD
=======


  // 로그인 맴버 조회
  public Member findLoginMember() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication.isAuthenticated()) {
      Optional<Member> member = memberRepository.findByEmail(authentication.getName());

      if (member.isPresent()) {
        return member.get();
      }

    }

    throw  new BusinessLogicException(ExceptionCode.MEMBER_NOT_AUTHENTICATED);
  }

>>>>>>> origin/be
}