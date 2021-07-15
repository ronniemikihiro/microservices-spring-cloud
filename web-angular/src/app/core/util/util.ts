import { EmailInvalidoErro } from './../errors/email-invalido.erro';
import { CampoObrigatorioErro } from './../errors/campo-obrigatorio.erro';
import { FormGroup } from '@angular/forms';

export class Util {
    static isVazio = value => {
        if (value === undefined || value === null || value === '') return true
        else if (typeof value === 'number') return false
        else if (typeof value === 'string') return value.trim().length === 0
        else if (Array.isArray(value)) return value.length === 0
        else if (typeof value === 'object') return value == null || Object.keys(value).length === 0
        else if (typeof value === 'boolean') return false
        else return !value
    }

    static validarPropriedadesForm(form: FormGroup) {
        Object.keys(form.controls).forEach((property) => {
          const field = form.get(property);
    
          if (field.invalid) {
            const element = (<HTMLElement>document.getElementById(property));
            let placeholder;

            if(element) {
              const dataplaceholder = element.getAttribute('data-placeholder');
              placeholder = dataplaceholder ? dataplaceholder : element.getAttribute('placeholder');
            } else {
              console.warn("O componente vinculado com formControlName='" + property + "' não possui id='" 
                + property + "' informado. Favor informar! Obs.: O formControlName deve ser igual ao id.");
            }

            if (field.hasError('required')) {
              throw new CampoObrigatorioErro(placeholder ? placeholder : property);
            }
            if (field.hasError('email')) {
              throw new EmailInvalidoErro();
            }
          }
        });
    }

    static formParaEntidade(form: FormGroup, obj: any) {
        const propertiesObj = Object.getOwnPropertyNames(obj);
    
        propertiesObj.forEach((property) => {
          try {
            const value = form.get(property).value;
            
            if (value) {
              obj[property] = value;
            }
          } catch (e) {
            console.warn("Form não possui a propriedade " + property);
          }
        });
    
        return obj;
    }
}

export class ObjectUtil {
    
}

export class StringUtil {
    static primeiraLetraMaiuscula(string): string {
        return string ? string.charAt(0).toUpperCase() + string.slice(1) : undefined;
    }
}

export class NumberUtil {
    
}

export class ArrayUtil {
    
}