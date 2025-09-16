package entities;

import java.util.List;
import java.util.Optional;

// Ahora la interfaz también es genérica con el mismo límite que la clase
public interface IRepository<T extends EntidadBase> {

    // Guarda una nueva entidad que aún no tiene un ID.
    T save(T entity);

    // Busca una entidad por su ID.
    Optional<T> findById(Long id);

    //Actualiza una entidad existente identificada por su ID.
    Optional<T> update(Long id, T entityUpdate);

    // Busca una entidad por su Nombre.
    Optional<T> findByNombre(String nombre);

    //Elimina una entidad por su ID.
    Optional<T> delete(Long id);

    //Devuelve una lista de todas las entidades en el repositorio.
    List<T> retriveAll();

    String getNombreDelTipo();


}