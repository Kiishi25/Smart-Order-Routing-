import { TestBed, async } from '@angular/core/testing';
import { TradeHistoryComponent } from './tradeHistory.component';
describe('TradeHistoryComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        TradeHistoryComponent
      ],
    }).compileComponents();
  }));
  it('should create the trade history', async(() => {
    const fixture = TestBed.createComponent(TradeHistoryComponent);
    const tradeHistory = fixture.debugElement.componentInstance;
    expect(tradeHistory).toBeTruthy();
  }));
  it(`should have as title 'trade history'`, async(() => {
    const fixture = TestBed.createComponent(TradeHistoryComponent);
    const tradeHistory = fixture.debugElement.componentInstance;
    expect(tradeHistory.title).toEqual('Trade History');
  }));
});
