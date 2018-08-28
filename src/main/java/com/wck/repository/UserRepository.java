package com.wck.repository;

import com.wck.domain.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

    //User findByUserName(String userName);

    User findByUserNameOrEmail(String userName, String email);

    @Override
    <S extends User> Page<S> findAll(Example<S> example, Pageable pageable);

    Page<User> findByUserName(String userName, Pageable pageable);

    /**
     * 在SQL的查询方法上面使用@Query注解，
     * 如涉及到删除和修改在需要加上@Modifying.
     * 也可以根据需要添加 @Transactional 对事物的支持，查询超时的设置等
     * @param id
     */
    @Transactional(rollbackFor = {}, timeout = 10)
    @Modifying
    @Query("delete from User where id = ?1")
    void deleteByUserId(Long id);

    /**
     * 通过email获取user
     * rollbackFor ?
     *
     * @param emailAddress email
     * @return User
     */
    @Transactional(timeout = 10, rollbackFor = Throwable.class)
    User findByEmailAddress(String emailAddress);
}
