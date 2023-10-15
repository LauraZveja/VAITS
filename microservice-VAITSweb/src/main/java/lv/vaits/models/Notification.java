package lv.vaits.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "notification_table")
@Entity
@Setter
@Getter
@NoArgsConstructor
public class Notification {
	
	@Setter(value = AccessLevel.NONE)
    @Id
    @Column(name = "Idn")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idn;

    @Column(name = "Notification_text")
    private String notificationText;

    @Column(name = "Kafka_topic")
    private String topic; 

}
