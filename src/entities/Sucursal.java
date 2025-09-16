package entities;


import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;


@Getter
@SuperBuilder
public class Sucursal extends EntidadBase {

    private final Domicilio domicilio;
    private final LocalTime horarioApertura;
    private final LocalTime horarioCierre;
    private final boolean es_Casa_Matriz;


    @Override
    public String toString() {
        return "Sucursal \"" + super.getNombre() + "\" :\n" +
                "{ id = '" + String.format("%03d", this.getId()) + "'" +
                ", domicilio = '" + domicilio.getNombre()
                + " " + domicilio.getNumero() + "'" +
                ", horarioApertura = '" + horarioApertura + "'" +
                ", horarioCierre = '" + horarioCierre + "'" +
                ", es_Casa_Matriz = '" + es_Casa_Matriz + "'" +
                '}';
    }


}
