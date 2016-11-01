package com.zyhao.openec.uniquecode.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyhao.openec.uniquecode.entity.UniqueCode;
import com.zyhao.openec.uniquecode.repository.UniqueCodeRepository;

@Service
public class UniqueCodeService {

	private final Log log = LogFactory.getLog(UniqueCodeService.class);
	
    @Autowired  
    HttpServletRequest request;
    
    @Autowired  
    UniqueCodeRepository uniqueCodeRepository;
    
    @Autowired  
    SequenceTableService sequenceTableService;
    
	private String getRemoteIP(HttpServletRequest request) {
    	if(request.getHeader("x-forwarded-for") != null){
    		log.info("x-forwarded-for-------------->"+request.getHeader("x-forwarded-for"));
    		return request.getHeader("x-forwarded-for");
    	}
    	
    	log.info("remoteAddr-------------->"+request.getRemoteAddr());
    	return request.getRemoteAddr();
		
	}
	
	public UniqueCode uniqueCode() {
		String ip = getRemoteIP(request);
		
		UniqueCode uc = uniqueCodeRepository.findOne(ip);
		
		if(uc != null){
			return uc;
		}
		
		uc = new UniqueCode();
		
		uc.setRemoteIp(ip);
		uc.setCode(sequenceTableService.getNextValue("unique_code_sequence"));
		
		return uniqueCodeRepository.save(uc);
	}

}
