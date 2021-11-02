import { Injectable } from "@angular/core";
import { OrderService } from "../services/order.service";

@Injectable({
    providedIn: 'root',
  })
export class OrderController{
    orderService : OrderService;

    constructor(orderService : OrderService){
        this.orderService = orderService;
    }

    async cancelOrder(orderId: any) {
      return await this.orderService.cancelOrder(orderId);
    }

    async getOrdersByUsername(username: String) {
      return await this.orderService.getOrdersByUsername(username);
    }

    public async createOrder(buyOrSell, type, priceLimit, shareQuantity, username, instrumentCode){
      return await this.orderService.createOrder(buyOrSell, type, priceLimit, shareQuantity, username, instrumentCode);
    }
}
