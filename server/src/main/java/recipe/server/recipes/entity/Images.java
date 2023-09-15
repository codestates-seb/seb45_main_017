package recipe.server.recipes.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import recipe.server.member.entity.Member;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column(length = 500, nullable = false)
    private String originImageName;

    @Column(length = 500, nullable = false)
    private String imageName;

    @Column(length = 1000, nullable = false)
    private String imagePath;


    @ManyToOne
    @JoinColumn(name = "RECIPES_ID")
    private Recipes recipes;

//    @Column(length = 10, nullable = true)
//    private String imageType;

    @Builder
    public Images(String originImageName, String imageName, String imagePath) {
        this.originImageName = originImageName;
        this.imageName = imageName;
        this.imagePath = imagePath;

    }
}
