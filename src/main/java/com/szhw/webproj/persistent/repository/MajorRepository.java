package com.szhw.webproj.persistent.repository;

import com.szhw.webproj.persistent.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author HJF
 * @date 2018/12/15 14:27
 */
public interface MajorRepository extends JpaRepository<Major,Integer> {
}
