package entities;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class GeneralRepository<T extends EntidadBase> implements IRepository<T> {
    private final AtomicLong idGenerator = new AtomicLong(0);
    private final Map<Long, T> data = new ConcurrentHashMap<>();
    private final Class<T> tipoEntidad;

    public GeneralRepository(Class<T> tipoEntidad) {
        this.tipoEntidad = tipoEntidad;
    }

    @Override
    public T save(T entity) {
        Objects.requireNonNull(entity, "La entidad no puede ser null");
        Objects.requireNonNull(entity.getNombre(), "El nombre de la entidad no puede ser null.");

        if (entity.getId() != null) {
            throw new IllegalArgumentException("La entidad a guardar no puede tener un id, utilice Update");
        }

        Long newId = idGenerator.incrementAndGet();
        entity.setId(newId);
        this.data.put(newId, entity);
        return entity;
    }

    @Override
    public Optional<T> findById(Long id) {
        Objects.requireNonNull(id, "El ID no puede ser nulo");
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public Optional<T> update(Long id, T entityUpdate) {
        Objects.requireNonNull(id, "El ID no puede ser nulo");
        Objects.requireNonNull(entityUpdate, "El objeto de actualización no puede ser nulo");

        return findById(id).map(savedEntity -> {
            entityUpdate.setId(id);
            data.put(id, entityUpdate);
            return entityUpdate;
        });
    }

    @Override
    public Optional<T> findByNombre(String nombre) {
        return data.values().stream()
                .filter(entity -> entity.getNombre().equals(nombre))
                .findFirst();
    }

    @Override
    public Optional<T> delete(Long id) {
        Objects.requireNonNull(id, "El ID no puede ser nulo");
        return Optional.ofNullable(data.remove(id));
    }

    @Override
    public List<T> retriveAll() {
        return data.values().stream().toList();
    }

    @Override
    public String getNombreDelTipo() {
        return (tipoEntidad).getSimpleName();
    }
}