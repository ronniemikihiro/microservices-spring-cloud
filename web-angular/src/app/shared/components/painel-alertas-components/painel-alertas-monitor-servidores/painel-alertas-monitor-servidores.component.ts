import { Observable, timer } from 'rxjs';
import { MonitorServidor } from './../../../../core/entities/util/monitor-servidor';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { AbstractPainelAlertasComponent } from '../abstract-painel-alertas.component';
import { MatExpansionPanel } from '@angular/material/expansion';

@Component({
  selector: 'app-painel-alertas-monitor-servidores',
  templateUrl: './painel-alertas-monitor-servidores.component.html',
  styleUrls: ['./painel-alertas-monitor-servidores.component.scss']
})
export class PainelAlertasMonitorServidoresComponent extends AbstractPainelAlertasComponent<MonitorServidor> {
  
  @ViewChild('panel1') MyDOMElement: ElementRef;
  @ViewChild(MatExpansionPanel ) expansion: MatExpansionPanel;

  fullScreen = false;
  private timer: Observable<number> = timer(0, 30000);

  constructor() { 
    super();
  }

  public init(): void {
    this.iniciarMonitoramento();
  }

  public destruir(destruir: boolean): void {
    //throw new Error('Method not implemented.');
  }

  iniciarMonitoramento() {
    /*this.monitoringService.getMessages().subscribe((message) => {
      if (this.monitoringService.topic === message.topic) {
        let index = -1;
        let found = false;
        for (const server of  this.items.data) {
          index++;
          if (server.serverId === message.message.serverId) {
            server.started = true;
            found = true;
            if (message.message.monitoring && message.message.active) {
              server.connectivity = message.message.connectivity;
              server.lastCheck = message.message.lastCheck;
              server.name = message.message.name;
              this.getDiff(server);
            } else {
              this.items.data.splice(index, 1);
            }
            break;
          }
        }
        if (!found && message.message.active && message.message.monitoring) {
          this.items.data.push(message.message);
        }
      }
    });*/
  }

}
