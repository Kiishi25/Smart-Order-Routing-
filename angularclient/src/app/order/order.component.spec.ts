import { TestBed, async } from '@angular/core/testing';
import { OrderComponent } from './order.component';
describe('OrderComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        OrderComponent
      ],
    }).compileComponents();
  }));
  it('should create the order', async(() => {
    const fixture = TestBed.createComponent(OrderComponent);
    const order = fixture.debugElement.componentInstance;
    expect(order).toBeTruthy();
  }));
  it(`should have as title 'order'`, async(() => {
    const fixture = TestBed.createComponent(OrderComponent);
    const order = fixture.debugElement.componentInstance;
    expect(order.title).toEqual('order');
  }));
});
