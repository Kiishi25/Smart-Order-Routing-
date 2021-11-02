package com.ab.services;

import org.apache.log4j.BasicConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.ab.entities.Instrument;
import com.ab.repositories.InstrumentRepository;

@Service
public class InstrumentService {
	//private static final Logger logger = LogManager.getLogger(UserService.class);

	@Autowired
	private InstrumentRepository instrumentRep;
	
	public InstrumentService() {
		BasicConfigurator.configure();
	}
	
    public List<Instrument> findInstruments() {
        return instrumentRep.findAll();
    }

    
}
