import entities.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        //  ---  ---  ---  --- (Punto 4) ---  ---  ---  ---

        // --- Crear un país (Argentina) ---
        Pais argentina = Pais.builder().nombre("Argentina").build();

        // --- Crear Provincias y relacionarlas con el país ---
        Provincia buenosAires = Provincia.builder().nombre("Buenos Aires").pais(argentina).build();
        Provincia cordoba = Provincia.builder().nombre("Córdoba").pais(argentina).build(); // Corregido: Mendoza -> Córdoba

        // --- Crear Localidades de Buenos Aires y sus domicilios ---
        Localidad caba = Localidad.builder().nombre("CABA").provincia(buenosAires).build();
        Domicilio domicilioCaba = Domicilio.builder().nombre("Av. Corrientes").numero(123).localidad(caba).cp(1043).build();

        Localidad laPlata = Localidad.builder().nombre("La Plata").provincia(buenosAires).build();
        Domicilio domicilioLaPlata = Domicilio.builder().nombre("Calle 7").numero(456).localidad(laPlata).cp(1900).build();

        // --- Crear Localidades de Córdoba y sus domicilios ---
        Localidad cordobaCapital = Localidad.builder().nombre("Córdoba Capital").provincia(cordoba).build();
        Domicilio domicilioCordobaCapital = Domicilio.builder().nombre("Av. Colón").numero(789).localidad(cordobaCapital).cp(5000).build();

        Localidad villaCarlosPaz = Localidad.builder().nombre("Villa Carlos Paz").provincia(cordoba).build();
        Domicilio domicilioVillaCarlosPaz = Domicilio.builder().nombre("Av. San Martín").numero(101).localidad(villaCarlosPaz).cp(5152).build();

        // --- Crear las Sucursales ---
        // La instrucción "Crear la Sucursal2 y relacionarla con el domicilio de Villa Carlos Paz" es un error tipográfico en el enunciado.
        // Se asume que se refiere a la Sucursal 4.
        Sucursal sucursal1 = Sucursal.builder().nombre("Sucursal CABA").domicilio(domicilioCaba).horarioApertura(LocalTime.of(9, 0)).horarioCierre(LocalTime.of(18, 0)).es_Casa_Matriz(true).build();
        Sucursal sucursal2 = Sucursal.builder().nombre("Sucursal La Plata").domicilio(domicilioLaPlata).horarioApertura(LocalTime.of(9, 0)).horarioCierre(LocalTime.of(18, 0)).build();
        Sucursal sucursal3 = Sucursal.builder().nombre("Sucursal Córdoba Capital").domicilio(domicilioCordobaCapital).horarioApertura(LocalTime.of(8, 30)).horarioCierre(LocalTime.of(17, 30)).es_Casa_Matriz(true).build();
        Sucursal sucursal4 = Sucursal.builder().nombre("Sucursal Villa Carlos Paz").domicilio(domicilioVillaCarlosPaz).horarioApertura(LocalTime.of(10, 0)).horarioCierre(LocalTime.of(20, 0)).build();

        // --- Crear las Empresas y relacionarlas con las sucursales ---
        Empresa empresa1 = Empresa.builder().nombre("TecnoCorp").cuit("30-11223344-5").razonSocial("TecnoCorp S.A.").build();
        empresa1.addSucursal(sucursal1);
        empresa1.addSucursal(sucursal2);

        Empresa empresa2 = Empresa.builder().nombre("LogiSolutions").cuit("30-55667788-9").razonSocial("LogiSolutions S.R.L.").build();
        empresa2.addSucursal(sucursal3);
        empresa2.addSucursal(sucursal4);

        // --- Guardar empresas en el repositorio ---
        IRepository<Empresa> repositorioEmpresa = new GeneralRepository<>(Empresa.class);
        repositorioEmpresa.save(empresa1);
        repositorioEmpresa.save(empresa2);


        System.out.println("\n--- DEMOSTRACIÓN DE RESULTADOS (Punto 5) ---\n");

        // a. Mostrar todas las empresas.
        System.out.println("a. Mostrando todas las empresas:");
        List<Empresa> todasLasEmpresas = repositorioEmpresa.retriveAll();
        todasLasEmpresas.forEach(System.out::println);
        System.out.println("\n\n-------------------------------------\n\n");


        // b. Buscar una empresa por su Id.
        System.out.println("b. Buscando empresa con ID = 2:");
        Optional<Empresa> empresaPorId = repositorioEmpresa.findById(2L);
        empresaPorId.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("No se encontró la empresa con ID 2.")
        );
        System.out.println("\n\n-------------------------------------\n\n");


        // c. Buscar una empresa por nombre.
        System.out.println("c. Buscando empresa con nombre = 'TecnoCorp':");
        Optional<Empresa> empresaPorNombre = repositorioEmpresa.findByNombre("TecnoCorp");
        empresaPorNombre.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("No se encontró la empresa con nombre 'TecnoCorp'.")
        );
        System.out.println("\n\n-------------------------------------\n\n");


        // d. Actualizar los datos de una empresa buscando por su ID.
        System.out.println("d. Actualizando el CUIT de la empresa con ID = 1:");
        Empresa datosUpdate = Empresa.builder().cuit("30-99999999-1").nombre("TecnoCorp Global").build(); // Solo creamos un objeto con los datos a cambiar
        Optional<Empresa> empresaActualizada = repositorioEmpresa.update(1L, datosUpdate);
        System.out.println("Resultado de la actualización:");
        empresaActualizada.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("No se pudo actualizar la empresa con ID 1.")
        );


        System.out.println("\n\n-------------------------------------\n\n");


        // e. Eliminar una empresa buscando por su ID.
        System.out.println("e. Eliminando la empresa con ID = 1:");
        Optional<Empresa> empresaEliminada1 = repositorioEmpresa.delete(1L);
        empresaEliminada1.ifPresentOrElse(
                emp -> System.out.println("Se eliminó con éxito la empresa: " + emp.getNombre()),
                () -> System.out.println("No se pudo eliminar la empresa con ID 1.")
        );


        System.out.println("\nLista de empresas después de la eliminación:");

        imprimirLista(repositorioEmpresa.retriveAll(), repositorioEmpresa);

        System.out.println("\n\n-------------------------------------\n\n");


        System.out.println("e. Eliminando la empresa con ID = 2:");
        Optional<Empresa> empresaEliminada2 = repositorioEmpresa.delete(2L);
        empresaEliminada2.ifPresentOrElse(
                emp -> System.out.println("Se eliminó con éxito la empresa: " + emp.getNombre()),
                () -> System.out.println("No se pudo eliminar la empresa con ID 2.")
        );

        System.out.println("\nLista de empresas después de la segunda eliminación:");

        imprimirLista(repositorioEmpresa.retriveAll(), repositorioEmpresa);

        System.out.println("\n\n-------------------------------------\n\n");

    }


    public static <T extends EntidadBase> void imprimirLista(List<T> list, IRepository<T> repo) {
        if (list.isEmpty()) {
            System.out.println("No se han encontrado " + repo.getNombreDelTipo() + "s ");
        } else {
            IntStream.range(0, list.size()) // Crea un stream de números desde 0 hasta el tamaño de la lista - 1
                    .forEach(i -> { // Itera sobre cada número 'i'
                        System.out.println((i + 1) + ". " + list.get(i));
                    });
        }
    }
}