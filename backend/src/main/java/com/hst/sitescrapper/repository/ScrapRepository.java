package com.hst.sitescrapper.repository;

import com.hst.sitescrapper.model.entity.ScrapEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapRepository extends JpaRepository<ScrapEntity, String> {
}
