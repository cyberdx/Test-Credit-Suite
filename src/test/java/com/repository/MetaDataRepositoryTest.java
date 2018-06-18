package com.repository;

import com.configuration.TestsConfiguration;
import com.constants.DataState;
import com.constants.DataType;
import com.domain.MetaData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TestsConfiguration.class})
public class MetaDataRepositoryTest {

    @Autowired
    private MetaDataRepository metaDataRepository;

    @Test
    public void test() throws InterruptedException {
        MetaData metaData = new MetaData();

        metaData.setId("scsmbstgrb");
        metaData.setTimestamp(new Timestamp(new Date().getTime()));
        metaData.setHost("test");
        metaData.setType(DataType.APPLICATION_LOG);
        metaData.setState(DataState.FINISHED);
        metaData.setAlert(true);

        metaDataRepository.save(metaData);

        assertEquals(metaDataRepository.count(), 1);

    }
}