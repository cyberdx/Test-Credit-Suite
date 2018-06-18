package com.service;

import com.configuration.TestsConfiguration;
import com.domain.MetaData;
import com.repository.MetaDataRepository;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TestsConfiguration.class})
public class MetaDataServiceImpTest {

    @Autowired
    private MetaDataService metaDataService;

    @Autowired
    private MetaDataRepository metaDataRepository;

    @Test
    public void test(){
        String path = new File("src/test/resources/test.json").getAbsolutePath();
        metaDataService.runner(path);

        List<MetaData> resultList = metaDataRepository.getAllByAlertIsTrue();

        Assert.assertEquals(resultList.size(), 2);
        Assert.assertEquals(resultList.get(0).getId(), "scsmbstgra");
        Assert.assertTrue(resultList.get(1).isAlert());
        Assert.assertNull(resultList.get(1).getTimestamp());

        List<MetaData> all = (List<MetaData>) metaDataRepository.findAll();
        Assert.assertEquals(all.size(), 3);
    }
}