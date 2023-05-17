package co.kevinl.forumapirestful.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "user", schema = "foro_restful")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    @OneToMany(mappedBy = "idUser")
    private Collection<TopicEntity> topicsById;
    @OneToMany(mappedBy = "idUser")
    private Collection<AnswerEntity> answerById;

}
