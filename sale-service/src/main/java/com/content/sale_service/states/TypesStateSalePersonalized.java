package com.content.sale_service.states;

/**
 * Tipos de estados por lo cual puede pasar una venta personalizada.<br>
 * 1.	Emitido (1° Fase del Personalizado)<br>
 * a.	Toda venta personalizada que ingresa al sistema, se asignará el estado de emitido.<br>
 * b.	El cliente debe de seleccionar su producto, y realizar el primer pago 50%<br><br>
 * 2.	En Producción (2° Fase del Personalizado)<br>
 * a.	Es la elaboración del producto<br><br>
 * 3.	Construido (3° Fase del Personalizado)<br>
 * a.	El producto está terminado en su elaboración<br><br>
 * 4.	Pendiente (4° Fase del Personalizado)<br>
 * a.	en espera del 50 %<br><br>
 * 5.	Pagado (5° Fase del Personalizado)<br>
 * a.	Es la cancelación completa del producto:<br>
 * i.	Es toda venta que se completa el pago total (el 50% restante) de un producto personalizado<br>
 * ii.	Es toda venta que ingresa al sistema con pago total de un producto en stock (prefabricado)<br><br>
 * 6.	Entregando (6° Fase del Personalizado)<br>
 * a.	Se coordina el día que el cliente está disponible para hacerle entrega del producto<br><br>
 * 7.	Finalizado (7° Fase del Personalizado)<br>
 * a.	Indica la finalización de la venta<br><br>
 * Cancelado<br>
 * a.	Se canceló la venta por parte del cliente dentro de un rango de días establecidos<br>
 */
public enum TypesStateSalePersonalized {
    ISSUED(1),
    IN_PRODUCTION(2),
    BUILT(3),
    PENDING(4),
    PAID(5),
    DELIVERED(6),
    COMPLETED(7),
    CANCELLED(0);

    private int id;

    TypesStateSalePersonalized(int id) {
        this.id = id;
    }

    /**
     * @return id de la fila de la tabla de bd
     */
    public int getIdRow() {
        return id;
    }
}
