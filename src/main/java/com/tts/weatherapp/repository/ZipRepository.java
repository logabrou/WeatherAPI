package com.tts.weatherapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tts.weatherapp.model.Zip;


@Repository
public interface ZipRepository extends CrudRepository<Zip, Long>{
	List<Zip> findAll();
	Zip findByZipCode(String zipCode);
	
}
