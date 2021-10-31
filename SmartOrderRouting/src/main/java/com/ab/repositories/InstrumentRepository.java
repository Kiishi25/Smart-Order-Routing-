package com.ab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ab.entities.Instrument;

@Repository
public interface InstrumentRepository extends JpaRepository<Instrument,String>{

}
