import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
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
  closeResult: string = '';
  nombreExp: string = '';
  descripcion: string = '';
  isLogged = false;
  isNew = false;

  constructor(private experienciaService: ExperienciaService, private tokenService: TokenService,
    private activatedRouter: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

  ngOnInit(): void {
    this.cargarExperiencia();
    if (this.tokenService.getToken()) {
      this.isLogged = true;
    } else {
      this.isLogged = false;
    }
  }

  cargarExperiencia(): void {
    this.experienciaService.listaExp().subscribe(data => { this.experiencias = data });
  }

  seleccionarExp(exp: Experiencia): void {
    this.experiencia = exp;
  }

  onCreate(): void {
    const exp = new Experiencia(this.nombreExp, this.descripcion);
    this.experienciaService.crear(exp).subscribe(
      data => {
        alert("Nueva Experiencia Laboral añadida");
        this.router.navigate(['']);
      }, err => {
        alert("Error al agregar la nueva Experiencia Laboral");
        this.router.navigate(['']);
      }
    )
  }

  onSubmit(): void {
    if (this.isNew) {
      const expe = new Experiencia(this.nombreExp, this.descripcion);
      this.experienciaService.crear(expe).subscribe(
        data => {
          alert("Nueva Experiencia Laboral añadida");
          this.router.navigate(['']);
        }, err => {
          alert("Error al agregar la nueva Experiencia Laboral");
          this.router.navigate(['']);
        }
      )
    } else {
      const id = this.experiencia.id
      this.experienciaService.editar(id, this.experiencia).subscribe(
        data => {
          this.router.navigate(['']);
        }, err => {
          alert("Ha ocurrido un error al modificar la experiencia laboral");
          this.router.navigate(['']);
        }
      )
    }
  }

  borrar(id: number | undefined) {
    if (id != undefined) {
      this.experienciaService.borrar(id).subscribe(
        data => {
          this.cargarExperiencia();
        }, err => {
          alert("Ha ocurrido un error al eliminar la experiencia laboral");
        }
      )
    }
  }

  open(content: any) {
    /*if (!tipo) {
      this.seleccionarExp(exp);
      this.isNew = false;
    } else {
      this.nombreExp = '';
      this.descripcion= '';
      const expe = new Experiencia(this.nombreExp, this.descripcion);
      this.isNew = true;
    }*/

    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

}
