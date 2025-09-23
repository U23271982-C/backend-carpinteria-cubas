package com.content.sale_service.states;

public enum TypesStateEntity {
    ACTIVE(1);

    private int id;
    TypesStateEntity(int id) {
        this.id = id;
    }

    /**
     *
     * @return id de la fila de la tabla de bd
     */
    public int getIdRow() {
        return id;
    }
}
