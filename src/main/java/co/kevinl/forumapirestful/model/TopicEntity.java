package co.kevinl.forumapirestful.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "topic", schema = "foro_restful")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class TopicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    private UserEntity idUser;
    @ManyToOne
    @JoinColumn(name = "id_course", referencedColumnName = "id", nullable = false)
    private CourseEntity idCourse;
    private String title;
    private String message;
    private Timestamp date;
    private boolean status;
    private String author;
    private String course;
    @OneToMany(mappedBy = "idTopic")
    private Collection<AnswerEntity> answerById;

}
