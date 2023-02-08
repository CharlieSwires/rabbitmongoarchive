package com.unicard.rabbit;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.UniCardMongoBean;
import com.mongodb.UniCardMongoBeanRepository;
import com.unicard.rabbit.RequestBean.Inner;


@Service
public class ArchiveService {

	private static final int PAGE_SIZE = 10;
	@Autowired
	private UniCardMongoBeanRepository beanRepository;

	@Transactional
	public Long save(List<Inner> inputs, Long messageId) {
		assert(inputs != null && messageId !=null);
		for(Inner input : inputs) {
			delete(input.getCustomerId(),input.getUsedDate());
			UniCardMongoBean mb  = new UniCardMongoBean();
			mb.setCustomerId(input.getCustomerId());
			mb.setUsedDate(input.getUsedDate());
			mb.setXmlData(input.getXmlData());
			beanRepository.save(mb); 	
		}

		return messageId;
	}

	@Transactional
	public Boolean delete(String customerId, Date date) {
		UniCardMongoBean mb = beanRepository.findByCustomerIdDate(customerId, date);
		if (mb == null) return false;
		beanRepository.deleteById(mb.getId());
		return true;
	}
	

}
