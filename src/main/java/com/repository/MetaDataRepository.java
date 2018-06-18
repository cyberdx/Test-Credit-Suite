package com.repository;

import com.domain.MetaData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MetaDataRepository extends CrudRepository<MetaData, String> {

    List<MetaData> getAllByAlertIsTrue();
}
