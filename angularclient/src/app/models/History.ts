import { Order } from "./Order";

export class History{

	historyID: number;
  order: Order;
  orderTradingWith: Order;
  shareQuantity: number;
  value: number;
  timeStamp: string;
}
