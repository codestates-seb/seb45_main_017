package recipe.server.member.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import recipe.server.auth.utils.CustomAuthorityUtils;
import recipe.server.exception.BusinessLogicException;
import recipe.server.exception.ExceptionCode;
import recipe.server.member.entity.Member;
import recipe.server.member.repository.MemberRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Slf4j
@Service
public class MemberService extends DefaultOAuth2UserService {

  private final MemberRepository memberRepository;

  private PasswordEncoder memberPasswordEncoder;


  private CustomAuthorityUtils authorityUtils;

  public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, CustomAuthorityUtils authorityUtils) {

    this.memberRepository = memberRepository;
    this.authorityUtils = authorityUtils;
  }

  public Member createMember(Member member) {

    // 이미 등록된 이메일인지 확인
    verifyExistsEmail(member.getEmail());


    // password 암호화
    String encryptedPassword = memberPasswordEncoder.encode(member.getPassword());
    member.setPassword(encryptedPassword);

    //
    List<String> roles = authorityUtils.createRoles(member.getEmail());
    member.setRoles(roles);


    Member createMember = memberRepository.save(member);

    return createMember;
  }


  public Member updateMember(Member member) {
    Member findMember = findVerifiedMember(member.getMemberId());

    Optional.ofNullable(member.getEmail())
            .ifPresent(email -> findMember.setEmail(email));
    Optional.ofNullable(member.getUsername())
            .ifPresent(username -> findMember.setUsername(username));
    Optional.ofNullable(member.getPassword())
            .ifPresent(password -> findMember.setPassword(password));

    findMember.setModifiedAt(LocalDateTime.now());

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

  @Autowired
  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

    Map<String, Object> attributes = super.loadUser(userRequest).getAttributes();
    log.info("ATTR INFO : {}", attributes.toString());

    String email = null;
    String oauthType = userRequest.getClientRegistration().getRegistrationId();

    OAuth2User user2 = super.loadUser(userRequest);

    if ("naver".equals(oauthType.toLowerCase())) {
      email = ((Map<String, Object>) attributes.get("naver_account")).get("email").toString();
    } else if ("kakao".equals(oauthType.toLowerCase())) {
      email = ((Map<String, Object>) attributes.get("kakao_account")).get("email").toString();
    }

    if (getUserByEmailAndOAuthType(email, oauthType) == null) {
      log.info("{}({}) NOT EXISTS. REGISTER", email, oauthType);
      Member member = new Member();
      member.setEmail(email);
      member.setOauthType(oauthType);

      save(member);
    }

    return super.loadUser(userRequest);
  }

  public void save(Member member) {memberRepository.save(member);
  }

  public Member getUserByEmailAndOAuthType(String email, String oauthType) {
    return memberRepository.findByEmailAndOauthType(email, oauthType).orElse(null);
  }
}