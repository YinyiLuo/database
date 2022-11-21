package com.dbdev.music;

import com.dbdev.music.core.RestResult;
import com.dbdev.music.rest.SongBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.Map;

@SpringBootTest
public class RestTest {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${python-web.url}")
    private String PYTHON_URL;

    @Test
    void testGetAll() throws Exception {
        RestResult result = restTemplate.getForObject(PYTHON_URL + "/getAll", RestResult.class);
        assert result != null;
        for (var o : result.getData()) {
            SongBody songBody = new SongBody();
            songBody.setName(o.get("name"));
            songBody.setUrl(o.get("url"));
            System.out.println(songBody);
        }
    }
}
