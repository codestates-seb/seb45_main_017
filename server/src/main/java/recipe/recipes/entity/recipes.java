package recipe.recipes.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class recipes {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long recipesId;

    //memberId -> manytoone

    //api 가져오기
}
