package com.userimran.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userimran.entity.CountryEntity;

public interface CountryRepo extends JpaRepository<CountryEntity, Integer>{

}
