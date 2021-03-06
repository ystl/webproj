package com.szhw.webproj.persistent.repository;

import com.szhw.webproj.persistent.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author HJF
 * @date 2018/11/16 19:12
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
