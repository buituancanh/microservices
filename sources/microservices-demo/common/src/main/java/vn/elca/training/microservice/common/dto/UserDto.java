/*
 * Project: ISC-FDJP-IRC
 * ELCA Informatique SA
 */
package vn.elca.training.microservice.common.dto;

import lombok.Data;

/**
 * @author tcb
 */

@Data
public class UserDto {
    private long id;
    private String name;
    private String number;
    private String email;
}
