package com.content.trabajador_servicio.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetMapper <T>{
    T mapDeResultSet(ResultSet rs) throws SQLException;
}
