package br.com.domain.entity.dto;

import br.com.domain.entity.Entidade;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.ParameterizedType;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SuppressWarnings("unchecked")
public abstract class DTO<T> {

    @JsonIgnore private final Class<T> classEntity;

    public abstract Long getId();
    public abstract void setId(Long id);
    public abstract T toEntidade();
    @JsonIgnore public abstract RowMapper<? extends DTO> getRowMapper();

    protected DTO() {
        this.classEntity = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @JsonIgnore
    public Entidade getEntidade() {
        try {
            return (Entidade) this.classEntity.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected DTO getDtoPorId(Long id, Class<?> clazz) {
        DTO dto = null;

        if(id != null) {
            try {
                dto = (DTO) clazz.getConstructor().newInstance();
                dto.setId(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dto;
    }

    protected static LocalDate getLocalDate(final Date data) {
        return data == null ? null : data.toLocalDate();
    }

    protected static LocalDateTime getLocalDateTime(final Timestamp timestamp) {
        return timestamp == null ? null : timestamp.toLocalDateTime();
    }

}
