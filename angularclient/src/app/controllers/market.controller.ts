import { Injectable } from "@angular/core";
import { Instrument } from "../models/Instrument";
import { MarketService } from "../services/market.service";

@Injectable({
    providedIn: 'root',
  })
export class MarketController{

    marketService : MarketService;

    constructor(marketService : MarketService){
        this.marketService = marketService;
    }

    public async loadInstruments() : Promise<Instrument[]>{
        let instruments = await this.marketService.loadInstruments();

        return instruments;
    } 
}