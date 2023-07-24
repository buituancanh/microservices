package vn.elca.training.microservices.account.impl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.elca.training.microservices.account.impl.model.UserEntity;

import java.util.List;

/**
 * @author tcb
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, QuerydslPredicateExecutor<UserEntity>, UserRepositoryCustom {
    @Query("select usr from UserEntity usr left join fetch usr.addresses where usr.number = :number")
    UserEntity findByNumber(@Param("number") String accountNumber);

    List<UserEntity> findByNameContains(String partialName);

    @Query("select count(usr) from UserEntity usr")
    int countAccounts();
}
