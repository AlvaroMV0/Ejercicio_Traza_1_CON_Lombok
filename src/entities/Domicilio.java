package entities;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper = true)
@SuperBuilder
public class Domicilio extends EntidadBase {

    private final Integer numero;
    private final Integer cp;
    @ToString.Exclude
    private Localidad localidad;



    @ToString.Include(name = "localidad")
    public String localidadToString() {
        return super.getNombre();
    }

    //package-private
    void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }
}
