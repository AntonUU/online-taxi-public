package cn.anton.internalcommon.dao;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author 丶Anton
 * @since 2023-10-01
 */
@Data
public class DriverCarBindingRelationship implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long driverId;

    private Long carId;

    private Integer state;

    private LocalDateTime bindingTime;

    private LocalDateTime unBindingTime;
}
