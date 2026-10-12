package sigym_rest.mapper;

import org.springframework.stereotype.Component;
import sigym_rest.dto.PagoDto;
import sigym_rest.model.Pago;

@Component
public class PagoMapper {

    public PagoDto toDto(Pago pago) {

        PagoDto dto = new PagoDto();

        dto.setIdPago(pago.getIdPago());
        dto.setMonto(pago.getMonto());
        dto.setFechaPago(pago.getFechaPago());
        dto.setMetodoPago(pago.getMetodoPago());

        if (pago.getMembresia() != null) {

            dto.setIdMembresia(
                    pago.getMembresia().getIdMembresia()
            );

            if (pago.getMembresia().getProspecto() != null) {

                dto.setNombreProspecto(
                        pago.getMembresia()
                                .getProspecto()
                                .getNombre()
                );
            }
        }

        return dto;
    }

    public Pago toEntity(PagoDto dto) {

        Pago pago = new Pago();

        pago.setIdPago(dto.getIdPago());
        pago.setMonto(dto.getMonto());
        pago.setFechaPago(dto.getFechaPago());
        pago.setMetodoPago(dto.getMetodoPago());

        return pago;
    }
}
