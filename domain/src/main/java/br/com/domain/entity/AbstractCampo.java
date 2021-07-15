package br.com.domain.entity;

import org.springframework.util.ReflectionUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCampo {

    public List<String> values() {
        final List<String> values = new ArrayList<>();
        ReflectionUtils.doWithFields(this.getClass(), field -> values.add(String.valueOf(field.get(this))));
        return values;
    }
}
