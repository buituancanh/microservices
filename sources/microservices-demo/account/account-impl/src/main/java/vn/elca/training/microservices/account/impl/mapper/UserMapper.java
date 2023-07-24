package vn.elca.training.microservices.account.impl.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.elca.training.microservices.account.dto.GetUserResponse;
import vn.elca.training.microservices.account.impl.model.UserEntity;

/**
 * @author tcb
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

     @Mapping(target = "basicInfo.email", source = "email")
     @Mapping(target = "basicInfo.name", source = "name")
     @Mapping(target = "address.city", source = "userAddress.city")
     @Mapping(target = "address.street", source = "userAddress.street")
     GetUserResponse toUserResponse(UserEntity userEntity);
}
