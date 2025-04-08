import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddProductToCartComponent } from './add-product-to-cart.component';

describe('AddProductToCartComponent', () => {
  let component: AddProductToCartComponent;
  let fixture: ComponentFixture<AddProductToCartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddProductToCartComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddProductToCartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
