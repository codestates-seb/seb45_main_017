package recipe.server.myPage.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import recipe.server.member.entity.Member;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MypageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long saveHeartId;

    private boolean saveHeart = false;

    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Member member;

   // @ManyToOne(targetEntity = )
}
