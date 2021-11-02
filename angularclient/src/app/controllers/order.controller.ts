import { Injectable } from "@angular/core";
import { OrderService } from "../services/order.service";

@Injectable({
    providedIn: 'root',
  })
export class OrderController{
    async cancelOrder(orderId: any) {
      return await this.orderService.cancelOrder(orderId);
    }

    async getOrdersByUsername(username: String) {
      return await this.orderService.getOrdersByUsername(username);
    }

    orderService : OrderService;

    constructor(orderService : OrderService){
        this.orderService = orderService;
    }

    
}
