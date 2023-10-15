package lv.vaits.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaProducer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
	
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendNotification(String notification) {
		LOGGER.info(String.format("Message sent %s", notification));
		kafkaTemplate.send("test", notification);
	}

}
