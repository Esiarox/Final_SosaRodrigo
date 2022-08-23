/*import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pruebamodal',
  templateUrl: './pruebamodal.component.html',
  styleUrls: ['./pruebamodal.component.css']
})
export class PruebamodalComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}*/

import { Component , OnInit} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
     
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { Experiencia } from 'src/app/modelos/experiencia';
import { ExperienciaService } from 'src/app/services/experiencia.service';
     
@Component({
  selector: 'app-pruebamodal',
  templateUrl: './pruebamodal.component.html',
  styleUrls: ['./pruebamodal.component.css']
})
export class PruebamodalComponent implements OnInit  {
  title = 'appBootstrap';
  experiencia!: Experiencia;
  closeResult: string = '';
     
  /*------------------------------------------
  --------------------------------------------
  Created constructor
  --------------------------------------------
  --------------------------------------------*/
  constructor(private experienciaService: ExperienciaService, private activatedRouter: ActivatedRoute, private router: Router,private modalService: NgbModal) { }

  ngOnInit(): void {
    const id = this.activatedRouter.snapshot.params['id'];
    this.experienciaService.buscarExp(id).subscribe(
      data =>{
        this.experiencia = data;
      }, err =>{
        alert("Ha ocurrido un error al modificar la experiencia laborals");
        this.router.navigate(['']);
      }
    )
  }

  onUpdate(): void{
    const id = this.activatedRouter.snapshot.params['id'];
    this.experienciaService.editar(id, this.experiencia).subscribe(
      data => {
        this.router.navigate(['']);
      }, err =>{
         alert("Ha ocurrido un error al modificar la experiencia laboral");
         this.router.navigate(['']);
      }
    )
  }

  /**
   * Write code on Method
   *
   * @return response()
   */
  open(content:any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  } 
     
  /**
   * Write code on Method
   *
   * @return response()
   */
  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }
}