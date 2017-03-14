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
		
		String host=request.getRemoteHost();//返回发出请求的客户机的主机名
		int port =request.getRemotePort();//返回发出请求的客户机的端口号。
		
		log.info("remotePort-------------->"+host+"-=-"+port);
		
    	if(request.getHeader("x-forwarded-for") != null){
    		log.info("x-forwarded-for-------------->"+request.getHeader("x-forwarded-for"));
    		return request.getHeader("x-forwarded-for")+"_"+port;
    	}
    	
    	log.info("remoteAddr-------------->"+request.getRemoteAddr());
    	return request.getRemoteAddr()+"_"+port;
		
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
