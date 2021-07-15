import { MatPaginatorIntl } from "@angular/material/paginator";

const portuguesRangeLabel = (page: number, pageSize: number, length: number) => {
  if (length === 0 || pageSize === 0) { 
    return `0 de ${length}`; 
  }

  length = Math.max(length, 0);
  const startIndex = page * pageSize;
  const endIndex = startIndex < length ? Math.min(startIndex + pageSize, length) : startIndex + pageSize;

  return `${startIndex + 1} - ${endIndex} de ${length}`;
};


export function getPortuguesePaginatorIntl() {
  const paginatorIntl = new MatPaginatorIntl();

  paginatorIntl.itemsPerPageLabel = 'Itens por página:';
  paginatorIntl.firstPageLabel = 'Primeira página';
  paginatorIntl.previousPageLabel = 'Página anterior';
  paginatorIntl.nextPageLabel = 'Página seguinte';
  paginatorIntl.lastPageLabel = 'Última página';
  paginatorIntl.getRangeLabel = portuguesRangeLabel;

  return paginatorIntl;
}