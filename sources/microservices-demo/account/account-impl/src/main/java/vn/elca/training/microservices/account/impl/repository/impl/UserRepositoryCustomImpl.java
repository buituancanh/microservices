/*
 * Project: ISC-FDJP-IRC
 * ELCA Informatique SA
 */
package vn.elca.training.microservices.account.impl.repository.impl;

import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import vn.elca.training.microservices.account.impl.model.QUserEntity;
import vn.elca.training.microservices.account.impl.model.UserEntity;
import vn.elca.training.microservices.account.impl.repository.UserRepositoryCustom;

/**
 * @author tcb
 */
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public UserEntity findUserByName() {
        return new JPAQuery<UserEntity>(em)
                .from(QUserEntity.userEntity)
                .fetchOne();
    }
}
