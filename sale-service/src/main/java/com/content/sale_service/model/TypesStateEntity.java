package com.content.sale_service.model;

public enum TypeStateEntity {
    DELETED(0),
    ACTIVE(1),
    INACTIVE(2);

    private int id;
    TypeStateEntity(int id) {
        this.id = id;
    }

    /**
     *
     * @return id de la fila de la tabla
     */
    public int getIdRow() {
        return id;
    }
}
