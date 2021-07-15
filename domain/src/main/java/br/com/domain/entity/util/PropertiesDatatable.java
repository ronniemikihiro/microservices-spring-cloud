package br.com.domain.entity.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class PropertiesDatatable {

    private Integer page;
    private Integer size;
    private String orderProperty;
    private String orderDirection;
    private String filterField;
    private String filterValue;
    private Pageable pageable;

    public PropertiesDatatable(Integer page, Integer size, String orderProperty, String orderDirection, String filterField, String filterValue) {
        this.page = page;
        this.size = size;
        this.orderProperty = orderProperty;
        this.orderDirection = orderDirection;
        this.filterField = filterField;
        this.filterValue = filterValue;
    }

    public Pageable getPageable() {
        if(pageable == null) {
            final Sort sort = Sort.by(orderDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                    Sort.Direction.ASC : Sort.Direction.DESC, orderProperty);
            pageable = PageRequest.of(page, size, sort);
        }
        return pageable;
    }

    public String getOrder(String alias) {
        return " ORDER BY " + alias + "." + orderProperty + " " + orderDirection;
    }
}
