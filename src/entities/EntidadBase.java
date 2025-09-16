package entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
public abstract class EntidadBase {

    @Builder.Default
    private Long id = null;
    private String nombre;


}
