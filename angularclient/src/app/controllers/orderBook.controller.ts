import { Injectable } from "@angular/core";
import { OrderBookService } from "../services/orderBook.service";

@Injectable({
  providedIn: 'root',
})
export class OrderBookController{

    orderBookService : OrderBookService;

    constructor(orderBookService : OrderBookService){
        this.orderBookService = orderBookService;
    }

    public async getAll(){
      return await this.orderBookService.getAll();
    }
    public async createOrder(buyOrSell, orderType, priceLimit, quantity, username, orderBookId){
      return await this.orderBookService.createOrder(buyOrSell, orderType, priceLimit, quantity, username, orderBookId);

    }
}
