import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PruebamodalComponent } from './pruebamodal.component';

describe('PruebamodalComponent', () => {
  let component: PruebamodalComponent;
  let fixture: ComponentFixture<PruebamodalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PruebamodalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PruebamodalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
