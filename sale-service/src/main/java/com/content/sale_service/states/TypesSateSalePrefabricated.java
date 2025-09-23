package com.content.sale_service.states;
/**
 * Tipos de estado de una venta prefabricada.<br><br>
 * 1.	Pagado (1° Fase de Prefabricado)<br>
 * a.	Es la cancelación completa del producto:<br>
 * i.	Es toda venta que se completa el pago total (el 50% restante) de un producto personalizado<br>
 * ii.	Es toda venta que ingresa al sistema con pago total de un producto en stock (prefabricado)<br><br>
 * 2.	Entregando (2° Fase de Prefabricado)<br>
 * a.	Se coordina el día que el cliente está disponible para hacerle entrega del producto<br><br>
 * 3.	Finalizado (3° Fase de Prefabricado)<br><br>
 * Cancelado<br>
 * a. Se canceló la venta por parte del cliente dentro de un rango de días establecidos<br>
 */
public enum TypesSateSalePrefabricated {
    PAID(5),
    DELIVERED(6),
    COMPLETED(7),
    CANCELLED(0);;

    private int id;

    TypesSateSalePrefabricated(int id) {
        this.id = id;
    }

    /**
     * @return id de la fila de la tabla de bd
     */
    public int getIdRow() {
        return id;
    }
}
