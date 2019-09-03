package com.hst.sitescrapper.repository;

import com.hst.sitescrapper.model.entity.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapRepository extends JpaRepository<Scrap, String> {
}
