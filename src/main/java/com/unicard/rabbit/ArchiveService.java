package com.unicard.rabbit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	
	public List<Inner> getAllArray(Date start, Date end, Integer page) {
		Pageable paging = PageRequest.of(page, PAGE_SIZE);

		Page<UniCardMongoBean> mbs = beanRepository.findAllByDate(start, end, paging);

		if (mbs == null || mbs.isEmpty()) return null;

		List<Inner> rbs = new ArrayList<Inner>();
		int index = 0;

		for (UniCardMongoBean mb: mbs) {
			Inner rb = new Inner();
			rb.setId(mb.getId());
			rb.setCustomerId(mb.getCustomerId());
			rb.setUsedDate(mb.getUsedDate());
			rb.setXmlData(mb.getXmlData());
			rbs.add(rb);
		}
		return rbs;
	}

}
