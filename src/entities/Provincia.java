package entities;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Provincia extends EntidadBase {
    @ToString.Exclude
    private final Pais pais; // pertenece a



    @ToString.Include(name = "pais")
    public String paisToString() {
        return pais.getNombre();
    }

}
