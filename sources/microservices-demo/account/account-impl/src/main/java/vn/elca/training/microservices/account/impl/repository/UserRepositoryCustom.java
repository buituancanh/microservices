package vn.elca.training.microservices.account.impl.repository;

import vn.elca.training.microservices.account.impl.model.UserEntity;

/**
 * @author tcb
 */
public interface UserRepositoryCustom {

    UserEntity findUserByName();
}
