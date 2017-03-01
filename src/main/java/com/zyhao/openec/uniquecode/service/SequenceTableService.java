package com.zyhao.openec.uniquecode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyhao.openec.uniquecode.entity.SequenceTable;
import com.zyhao.openec.uniquecode.repository.SequenceTableRepository;

@Service
public class SequenceTableService {

	@Autowired
	SequenceTableRepository sequenceTableRepository;
	
	/**
	 * 创建序列
	 * @param sequence
	 * @return
	 */
	public SequenceTable createSequence (SequenceTable sequence){
		if(sequence == null){
			return null;
		}
		
		sequence.setZyCurrentValue((long) 1 );
		
		if(sequence.getZyIncrement() == null){
			sequence.setZyIncrement((long) 1 );
		}
		
		//查找已经存在的序列
		SequenceTable seq = sequenceTableRepository.findOne(sequence.getZyName());
		
		//创建新序列
		if(seq == null){
			return sequenceTableRepository.save(sequence);
		}
		
		//返回已经存在的序列
		return seq;
		
	}

	/**
	 * 获取序列当前值
	 * @param name
	 * @return
	 */
	public Long getCurrentValue(String name) {
		//查找已经存在的序列
		SequenceTable seq = sequenceTableRepository.findOne(name);
		
		if(seq == null){
			return null;
		}
		return seq.getZyCurrentValue();
	}
	
	/**
	 * 获取序列下个值
	 * @param name
	 * @return
	 */
	public synchronized Long getNextValue(String name) {
		//查找已经存在的序列
		SequenceTable seq = sequenceTableRepository.findOne(name);
		
		if(seq == null){
			return null;
		}
		
		Long currentValue = seq.getZyCurrentValue();
		Long increment = seq.getZyIncrement();
		
		seq.setZyCurrentValue(currentValue+increment);
		
		SequenceTable seq2 = sequenceTableRepository.save(seq);
		
		return seq2.getZyCurrentValue();
	}
	
	/**
	 * 修改序列当前值
	 * @param name
	 * @param currentValue
	 * @return
	 */
	public SequenceTable modifyCurrentValue(String name,Long currentValue) {
		//查找已经存在的序列
		SequenceTable seq = sequenceTableRepository.findOne(name);
		
		if(seq == null || currentValue == null){
			return null;
		}
		
		seq.setZyCurrentValue(currentValue);
		
		return sequenceTableRepository.save(seq);
	}
	
	/**
	 * 修改序列增量值
	 * @param name
	 * @param increment
	 * @return
	 */
	public SequenceTable modifyIncrement(String name,Long increment) {
		//查找已经存在的序列
		SequenceTable seq = sequenceTableRepository.findOne(name);
		
		if(seq == null || increment == null){
			return null;
		}
		
		seq.setZyIncrement(increment);
		
		return sequenceTableRepository.save(seq);
	}
	
}
