import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetOrdersCompositeComponent } from './get-orders-composite.component';

describe('GetOrdersCompositeComponent', () => {
  let component: GetOrdersCompositeComponent;
  let fixture: ComponentFixture<GetOrdersCompositeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GetOrdersCompositeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetOrdersCompositeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
