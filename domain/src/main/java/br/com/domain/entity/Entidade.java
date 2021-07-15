package br.com.domain.entity;

import java.io.Serializable;

public interface Entidade extends Serializable {
    Long getId();
    AbstractCampo field();
}
