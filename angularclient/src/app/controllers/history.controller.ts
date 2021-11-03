import { Injectable } from "@angular/core";
import { HistoryService } from "../services/history.service";

@Injectable({
  providedIn: 'root',
})
export class HistoryController {

    historyService : HistoryService;

    constructor(historyService : HistoryService){
        this.historyService = historyService;
    }

    public async getAll(){
      return await this.historyService.getAll();
    }
}
