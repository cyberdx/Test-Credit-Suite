package com.service;

import com.domain.MetaData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repository.MetaDataRepository;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Scanner;

@Service
public class MetaDataServiceImp implements MetaDataService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetaDataServiceImp.class);
    private LinkedHashMap<String, MetaData> hashMap;
    private MetaDataRepository metaDataRepository;

    @Autowired
    private void setMetaDataRepository(MetaDataRepository metaDataRepository) {this.metaDataRepository = metaDataRepository;}
    
    @Autowired
    private ObjectMapper mapper;

    @PostConstruct
    private void init() {hashMap = new LinkedHashMap<String, MetaData>();}

    public void runner(String path) {
        try (Scanner stdin = new Scanner(new File(path), "UTF-8")) {
            while (stdin.hasNextLine()) {
                addRecords(jsonMapper(stdin.nextLine()));
            }
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private MetaData jsonMapper(String line) {
        try {
            return mapper.readValue(line.replace("host", "\"host\"").replace("type", "\"type\""), MetaData.class);
        } catch (IOException e) {
            LOGGER.error(line + e.getMessage(), e);
        }
        return new MetaData();
    }

    private void addRecords(MetaData metaData) {
        if (StringUtils.isBlank(metaData.getId()) && !hashMap.containsKey(metaData.getId())) {
            hashMap.put(metaData.getId(), metaData);

        } else {
            if (Math.abs(hashMap.get(metaData.getId()).getTimestamp().getTime() - metaData.getTimestamp().getTime()) > 4)
                metaData.setAlert(true);

            hashMap.remove(metaData.getId());
            new Thread(() -> {save(metaData);}).start();
        }
    }

    @Transactional
    @Async
    void save(MetaData metaData) {
        metaDataRepository.save(metaData);
    }
}
