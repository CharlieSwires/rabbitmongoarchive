package com.unicard.rabbit;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicard.rabbit.RequestBean.Inner;

@Service
public class RabbitMQConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @Autowired
    private RabbitMQProducerBack producer;
    @Autowired
    private ArchiveService service;

    @RabbitListener(queues = {"${rabbitmq.queue1.name}"})
    public void consume(String message){
    	if (message == null) return;
    	ObjectMapper mapper = new ObjectMapper();
    	RequestBean bean = null;
		try {
			bean = mapper.readValue(message, RequestBean.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Inner> result = null;
		if (bean.getDateStart() != null) { //date set must be a get not post
	    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    	try {
				result = service.getAllArray(df.parse(bean.getDateStart()), df.parse(bean.getDateEnd()), bean.getPsge());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	bean.setList(result);
		} else {
			bean.setMessageId(service.save(bean.getList(), bean.getMessageId()));

		}
		String jsonStr = null;
		try {
			jsonStr = mapper.writeValueAsString(bean);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     	producer.sendMessage(jsonStr);

        LOGGER.info(String.format("Received message -> %s", jsonStr.toString()));
    }
}