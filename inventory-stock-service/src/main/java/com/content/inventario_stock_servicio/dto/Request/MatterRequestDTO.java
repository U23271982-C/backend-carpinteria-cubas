package com.content.inventario_stock_servicio.dto.Request;

/**
 * Peticiones del Cliente.
 * Crea o Actualiza Materia Prima.
 */
public class MatterRequestDTO {
    //Codigo de la materia prima
    private Integer id;
    //Nombre de la materia prima
    private String matter_name;
    //Descripcion de la materia prima
    private String matter_description;
    //Costo de la materia prima
    private double cost;
    //Unidad de medida de la materia prima
    private double unit_measure;
    //Tipo de materia prima (Materia Prima, Producto Prefabricado, Producto Personalizado)
    private Integer matter_type_id;
    //Estado de la materia prima (Disponible, No Disponible, Descontinuado)
    private Integer matter_state_id;
    //Estado del registro (Activo, Inactivo)
    private Integer state_id;

}
