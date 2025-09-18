package com.content.sale_service.service.abstractService;

import com.content.sale_service.dto.Request.SaleRequestDTO;
import com.content.sale_service.dto.Response.SaleResponseDTO;
import jakarta.transaction.Transactional;

/**
 * Agrupación de métodos que se deben implementar en el servicio del modelo
 * @param <DRE> Response DTO
 */
public interface ServiceAbs<DRQ,DRE>
        extends Creatable<DRQ,DRE>,
        Readable<DRE>,
        Updatable<DRQ,DRE>,
        Removable,
        Listable<DRE>{

}
