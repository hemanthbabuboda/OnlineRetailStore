import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateOrderCompositeComponent } from './create-order-composite.component';

describe('CreateOrderCompositeComponent', () => {
  let component: CreateOrderCompositeComponent;
  let fixture: ComponentFixture<CreateOrderCompositeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CreateOrderCompositeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateOrderCompositeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
