/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.tienda.repository;


import com.tienda.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProductoRepository extends JpaRepository<Producto, Integer>{
    
    //JPA Ampliada 
    public List<Producto> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup);
    
    // Query( 1-> lista de productos que esta en el rango del precioInf yprecioSup
              //2-> Ordenar por descripcion
    
    //JPQL Ampliada 
    //Ejemplo de método utilizando Consultas con JPQL
    @Query(value="SELECT a FROM Producto a where a.precio BETWEEN :precioInf AND :precioSup ORDER BY a.descripcion ASC")
    public List<Producto> metodoJPQL(@Param("precioInf") double precioInf, @Param("precioSup") double precioSup);
 
    
    
   
   
  //Nativa Ampliada 
   @Query(value = "SELECT * FROM producto WHERE precio BETWEEN "
   + ":precioInf AND :precioSup ORDER BY descripcion ASC",nativeQuery = true)
   public List<Producto> metodoNativoList(@Param("precioInf") double precioInf,
                                          @Param("precioSup") double precioSup);
   
   

   
   
}

     