import { Component, OnInit } from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

import { Experiencia } from 'src/app/modelos/experiencia';
import { ExperienciaService } from 'src/app/services/experiencia.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-experiencia',
  templateUrl: './experiencia.component.html',
  styleUrls: ['./experiencia.component.css']
})
export class ExperienciaComponent implements OnInit {
  experiencias: Experiencia[] = [];
  experiencia!: Experiencia;
  //constructor(private experienciaService: ExperienciaService, private tokenService: TokenService) { }
  constructor(private experienciaService: ExperienciaService, private tokenService: TokenService, private activatedRouter: ActivatedRoute, private router: Router,private modalService: NgbModal) { }

  isLogged = false;
  ngOnInit(): void {
    this.cargarExperiencia();
    if (this.tokenService.getToken()) {
      this.isLogged = true;
    } else {
      this.isLogged = false;
    }
  }

  cargarExperiencia():void{
    this.experienciaService.listaExp().subscribe(data => {this.experiencias = data});
  }

  xx2(exp: Experiencia): void {
    const id = this.activatedRouter.snapshot.params['id'];

    this.experienciaService.buscarExp(id).subscribe(
      data =>{
        this.experiencia = data;
      }, err =>{
        alert("Ha ocurrido un error al modificar la experiencia laboral");
        this.router.navigate(['']);
      }
    )
  }

  xx(exp: Experiencia): void {
    //const id = this.activatedRouter.snapshot.params['id'];

    this.experiencia = exp;
  }

  onUpdate(): void{
    //const id = this.activatedRouter.snapshot.params['id'];

    const id = this.experiencia.id
    this.experienciaService.editar(id, this.experiencia).subscribe(
      data => {
        this.router.navigate(['']);
      }, err =>{
         alert("Ha ocurrido un error al modificar la experiencia laboral");
         this.router.navigate(['']);
      }
    )
  }

  borrar(id: number|undefined){
    if(id != undefined){
      this.experienciaService.borrar(id).subscribe(
        data => {
          this.cargarExperiencia();
        }, err => {
          alert("Ha ocurrido un error al borrar la experiencia laboral");
        }
      )
    }
  }


  closeResult: string = '';
  /**
   * Write code on Method
   *
   * @return response()
   */
   open(content:any, exp: Experiencia) {

    this.xx(exp);
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
