package com.ab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.ab.entities.Instrument;
import com.ab.services.InstrumentService;


@CrossOrigin
@RestController
public class InstrumentController {

    @Autowired
    private InstrumentService stockService;

    @GetMapping("/instruments")
    List<Instrument> findStocks() {
        return stockService.findInstruments();
    }

}
