import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderBookComponent } from './orderBook.component';

describe('OrderBookComponent', () => {
  let component: OrderBookComponent;
  let fixture: ComponentFixture<OrderBookComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrderBookComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
