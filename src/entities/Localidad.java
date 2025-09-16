package entities;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper = true)
@SuperBuilder
public class Localidad extends EntidadBase {
    @ToString.Exclude
    private final Provincia provincia;



    @ToString.Include(name = "provincia")
    public String provinciaToString() {
        return this.provincia.getNombre();
    }


}
