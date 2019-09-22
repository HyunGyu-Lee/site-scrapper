package com.hst.sitescrapper.repository;

import com.hst.sitescrapper.model.entity.ScrapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScrapRepository extends JpaRepository<ScrapEntity, Long> {
	List<ScrapEntity> findAllByOrderByIdDesc();
}
