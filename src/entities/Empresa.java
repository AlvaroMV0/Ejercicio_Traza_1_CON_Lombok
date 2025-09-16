package entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@SuperBuilder
public class Empresa extends EntidadBase {

    @Builder.Default
    private final Set<Sucursal> sucursales = new HashSet<>();
    private String razonSocial;
    private String cuit;
    private String logo;



    @Override
    public String toString() {
        String idStr = (this.getId() == null) ? "null" : String.format("%03d", this.getId());
        String sucursalesStr = sucursales.stream()
                .map(EntidadBase::getNombre)
                .collect(Collectors.joining(", "));

        return String.format(
                "Empresa [ID: %s, Nombre: '%s', CUIT: '%s', Razón Social: '%s', Sucursales: [%s]]",
                idStr, this.getNombre(), this.cuit, this.razonSocial, sucursalesStr
        );
    }

    public void addSucursal(Sucursal sucursal) {
        Objects.requireNonNull(sucursal, "La sucursal que se está intentado agregar está vacía");

        // Si la sucursal a agregar está marcada como matriz, primero revisamos si ya existe una
        if (sucursal.isEs_Casa_Matriz()) {
            Optional<Sucursal> casaMatrizExistente = this.findCasaMatriz();
            if (casaMatrizExistente.isPresent()) {
                throw new IllegalArgumentException(
                        "No puede existir más de una casa matriz. La empresa ya tiene asignada la sucursal: " +
                                casaMatrizExistente.get().getNombre()
                );
            }
        }

        this.sucursales.add(sucursal);
    }

    public Optional<Sucursal> findCasaMatriz() {
        return sucursales.stream()
                .filter(Sucursal::isEs_Casa_Matriz)
                .findFirst();
    }
}