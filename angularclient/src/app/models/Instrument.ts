
export class Instrument {

	public code: String;
	public company: String;
	public price: number;
	public volume: number;
	public open: number;
	public high: number;
	public low: number;
	public priceChange: number;

    // TODO set types
	constructor(code: String, company: String, price: number, volume: number, open: number, high: number, 
        low: number, priceChange: number) {
		this.code = code;
		this.company = company;
		this.price = price;
		this.volume = volume;
		this.open = open;
		this.high = high;
		this.low = low;
		this.priceChange = priceChange;
	}
	
}
