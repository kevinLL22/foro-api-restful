package co.kevinl.forumapirestful.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;


@Entity
@Table(name = "answer", schema = "foro_restful")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private UserEntity idUser;
    @ManyToOne
    @JoinColumn(name = "id_topic", referencedColumnName = "id")
    private TopicEntity idTopic;
    private String message;
    private Timestamp date;
    private String author;
    private Integer votes;
    private boolean solve;

}
