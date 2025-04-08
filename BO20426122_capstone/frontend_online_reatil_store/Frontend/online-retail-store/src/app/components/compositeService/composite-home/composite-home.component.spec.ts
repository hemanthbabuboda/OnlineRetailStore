import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompositeHomeComponent } from './composite-home.component';

describe('CompositeHomeComponent', () => {
  let component: CompositeHomeComponent;
  let fixture: ComponentFixture<CompositeHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CompositeHomeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CompositeHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
