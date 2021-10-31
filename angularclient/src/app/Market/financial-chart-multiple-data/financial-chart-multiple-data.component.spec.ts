import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FinancialChartMultipleDataComponent } from './financial-chart-multiple-data.component';

describe('FinancialChartMultipleDataComponent', () => {
  let component: FinancialChartMultipleDataComponent;
  let fixture: ComponentFixture<FinancialChartMultipleDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FinancialChartMultipleDataComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FinancialChartMultipleDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
