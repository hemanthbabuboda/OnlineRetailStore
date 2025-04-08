import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddProductCompositeComponent } from './add-product-composite.component';

describe('AddProductCompositeComponent', () => {
  let component: AddProductCompositeComponent;
  let fixture: ComponentFixture<AddProductCompositeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddProductCompositeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddProductCompositeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
