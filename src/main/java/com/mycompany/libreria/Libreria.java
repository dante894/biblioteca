/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.libreria;

import entidades.Autor;
import entidades.Cliente;
import entidades.Editorial;
import entidades.Libro;
import java.util.List;
import java.util.Scanner;
import servicio.ServicioAutor;
import servicio.ServicioCliente;
import servicio.ServicioEditorial;
import servicio.ServicioLibro;
import servicio.ServicioPrestamo;

/**
 *
 * @author dnt_t
 */
public class Libreria {
        static Scanner sc = new Scanner(System.in);
        static ServicioAutor servicioAutor = new ServicioAutor();
        static ServicioEditorial servicioEditorial = new ServicioEditorial();
        static ServicioLibro servicioLibro = new ServicioLibro();
        static ServicioCliente servicioCliente = new ServicioCliente();
        static ServicioPrestamo servicioPrestamo = new ServicioPrestamo();
        
        public static void main(String[] args) {
                  int opcion = 0;
                  String entrada;
        do {
            System.out.println("\n--- MENÚ LIBRERÍA ---");
            System.out.println("1. Crear autor");
            System.out.println("2. Crear editorial");
            System.out.println("3. Crear libro");
            System.out.println("4. Listar autores");
            System.out.println("5. Listar editoriales");
            System.out.println("6. Listar libros");
            System.out.println("7. Buscar libro por título");
            System.out.println("8. Buscar libros por autor");
            System.out.println("9. Buscar libros por editorial");
            System.out.println("10. Crear cliente");
            System.out.println("11. Listar clientes");
            System.out.println("12. Prestar un libro");
            System.out.println("13. Devolver un libro");
            System.out.println("14. Ver préstamos de un cliente");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
           
          entrada = sc.nextLine();
          
            try {
                opcion = Integer.parseInt(entrada);
                switch (opcion) {
                    case 1 -> crearAutor();
                    case 2 -> crearEditorial();
                    case 3 -> crearLibro();
                    case 4 -> servicioAutor.listarTodos().forEach(System.out::println);
                    case 5 -> servicioEditorial.listarTodas().forEach(System.out::println);
                    case 6 -> servicioLibro.listarTodos().forEach(System.out::println);
                    case 7 -> buscarPorTitulo();
                    case 8 -> buscarPorAutor();
                    case 9 -> buscarPorEditorial();
                    case 10 -> crearCliente();
                    case 11 -> servicioCliente.listarTodos().forEach(System.out::println);
                    case 12 -> prestarLibro();
                    case 13 -> devolverLibro();
                    case 14 -> verPrestamosDeCliente();
                    case 0 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingresá un número válido.");
                opcion = -1; // para que el while no explote y vuelva a mostrar el menú
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (opcion != 0);
        
    }   // Fin del Main

    static void crearAutor() {
        System.out.print("Nombre del autor: ");
        String nombre = sc.nextLine();
        servicioAutor.crear(nombre);
        System.out.println("Autor creado.");
    }

    static void crearEditorial() {
        System.out.print("Nombre de la editorial: ");
        String nombre = sc.nextLine();
        servicioEditorial.crear(nombre);
        System.out.println("Editorial creada.");
    }

    static void crearLibro() {
        System.out.print("ISBN: ");
        Long isbn = Long.valueOf(sc.nextLine());
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("Año: ");
        Integer anio = Integer.valueOf(sc.nextLine());
        System.out.print("Ejemplares: ");
        Integer ejemplares = Integer.valueOf(sc.nextLine());

        System.out.print("Nombre del autor (debe existir): ");
        String nombreAutor = sc.nextLine();
        List<Autor> autores = servicioAutor.buscarPorNombre(nombreAutor);
        if (autores.isEmpty()) {
            System.out.println("Autor no encontrado.");
            return;
        }

        System.out.print("Nombre de la editorial (debe existir): ");
        String nombreEditorial = sc.nextLine();
        List<Editorial> editoriales = servicioEditorial.listarTodas().stream()
                .filter(e -> e.getNombre().equalsIgnoreCase(nombreEditorial))
                .toList();
        if (editoriales.isEmpty()) {
            System.out.println("Editorial no encontrada.");
            return;
        }

        servicioLibro.crear(isbn, titulo, anio, ejemplares, autores.get(0), editoriales.get(0));
        System.out.println("Libro creado.");
    }

    static void buscarPorTitulo() {
        System.out.print("Título a buscar: ");
        Libro libro = servicioLibro.buscarPorTitulo(sc.nextLine());
        System.out.println(libro != null ? libro : "No encontrado.");
    }

    static void buscarPorAutor() {
        System.out.print("Nombre del autor: ");
        servicioLibro.buscarPorAutor(sc.nextLine()).forEach(System.out::println);
    }

    static void buscarPorEditorial() {
        System.out.print("Nombre de la editorial: ");
        servicioLibro.buscarPorEditorial(sc.nextLine()).forEach(System.out::println);
    }  
    
    static void crearCliente() {
    System.out.print("Documento: ");
    Long documento = Long.valueOf(sc.nextLine());
    System.out.print("Nombre: ");
    String nombre = sc.nextLine();
    System.out.print("Apellido: ");
    String apellido = sc.nextLine();
    System.out.print("Teléfono: ");
    String telefono = sc.nextLine();

    servicioCliente.crear(documento, nombre, apellido, telefono);
    System.out.println("Cliente creado.");
}

static void prestarLibro() {
    System.out.print("ISBN del libro: ");
    Long isbn = Long.valueOf(sc.nextLine());
    System.out.print("Documento del cliente: ");
    Long documento = Long.valueOf(sc.nextLine());

    List<Cliente> clientes = servicioCliente.buscarPorDocumento(documento);
    if (clientes.isEmpty()) {
        System.out.println("Cliente no encontrado.");
        return;
    }

    servicioPrestamo.registrarPrestamo(isbn, clientes.get(0));
    System.out.println("Préstamo registrado.");
}

static void devolverLibro() {
    System.out.print("ID del préstamo a devolver: ");
    Integer idPrestamo = Integer.valueOf(sc.nextLine());
    servicioPrestamo.devolverLibro(idPrestamo);
    System.out.println("Devolución registrada.");
}

static void verPrestamosDeCliente() {
    System.out.print("Documento del cliente: ");
    Long documento = Long.valueOf(sc.nextLine());
    servicioPrestamo.buscarPorCliente(documento).forEach(System.out::println);
}
                   
} // Fin del Libreria

