//package entities;
//
//
//
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.atomic.AtomicLong;
//
//public class EmpresaRepository implements IRepository<Empresa> {
//    private final AtomicLong idGenerator = new AtomicLong(0);
//    private final Map<Long, Empresa> empresas = new ConcurrentHashMap<>();
//
//
//
//    public Empresa save(Empresa empresa){
//        //empresa, empresa.Nombre y empresa.cuit no puede ser nulos:
//        Objects.requireNonNull(empresa,"Empresa no puede ser null");
//        Objects.requireNonNull(empresa.getNombre(), "El nombre de la empresa no puede ser null.");
//        Objects.requireNonNull(empresa.getCuit(), "El CUIT de la empresa no puede ser null.");
//
//        if(empresa.getId() != null) {
//            throw new IllegalArgumentException("La empresa a guardar no puede tener un id, utilice Update");
//        }
//
//        Long newId = idGenerator.incrementAndGet();
//        empresa.setId(newId);
//        this.empresas.put(newId, empresa);
//        return (empresa);
//    }
//
//
//    public Optional<Empresa> findById(Long id){
//        //id no puede ser nulo
//        Objects.requireNonNull(id,"El ID no puede ser nulo");
//        return Optional.ofNullable(empresas.get(id));
//    }
//
//
//    public Optional<Empresa> update(Long id, Empresa empresaUpdate){
//        //id, empresa.id, empresa no puede ser nulos:
//        Objects.requireNonNull(id,"El ID no puede ser nulo");
//        Objects.requireNonNull(empresaUpdate,"El objeto empresa no puede ser nulo");
//
//
//
//        return (this.findById(id)).map(savedEmpresa ->
//                {
//                    if(empresaUpdate.getNombre() != null){
//                        savedEmpresa.setNombre(empresaUpdate.getNombre());
//                    }
//                    if(empresaUpdate.getCuit() != null){
//                        savedEmpresa.setCuit(empresaUpdate.getCuit());
//                    }
//                    if(empresaUpdate.getRazonSocial() != null){
//                        savedEmpresa.setRazonSocial(empresaUpdate.getRazonSocial());
//                    }
//                    if(empresaUpdate.getLogo() != null){
//                    savedEmpresa.setLogo(empresaUpdate.getLogo());
//                    }
//                    empresas.put(id, savedEmpresa);
//                    return savedEmpresa;
//                }
//                );
//    }
//
//    public Optional<Empresa> findByNombre (String nombre){
//        return empresas.values().stream().filter(empresa -> empresa.getNombre().equals(nombre))
//                .findFirst();
//    }
//
//
//    public Optional<Empresa> delete(Long id){
//        //id no puede ser nulo
//        Objects.requireNonNull(id,"El ID no puede ser nulo");
//        return Optional.ofNullable(empresas.remove(id));
//    }
//
//
//    public List<Empresa> retriveAll(){
//        return empresas.values().stream().toList();
//    }
//
//}
