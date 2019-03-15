package com.example.utils.json;

import com.example.utils.poi.Country;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Created by Blake on 2018/8/14
 */
public class JacksonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JacksonUtil.class);

    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    /**
     * @return T
     * @throws
     * @description JSON字符串转换成Object
     * @params [json, clazz]
     */
    public static <T> T toObject(String json, Class<T> clazz) {

        try {
            if (Objects.isNull(json) || "".equals(json)) {
                return null;
            }

            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return T
     * @throws
     * @description JSON字符串转换成List
     * @params [json, valueTypeRef]
     */
    public static <T> T toList(String json, TypeReference<T> valueTypeRef) {

        try {
            if (Objects.isNull(json) || "".equals(json)) {
                return null;
            }
            return objectMapper.readValue(json, valueTypeRef);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return java.lang.String
     * @throws
     * @description JavaBean转换成JSON字符串
     * @params [object]
     */
    public static String toJSon(Object object) {

        if (Objects.isNull(objectMapper)) {
            objectMapper = new ObjectMapper();
        }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        // TODO 1 JSON字符串转换成Object
        String jsonCountry = "{\"name\":\"中国\",\"code\":\"CN\"}";
        Country jsonToCountry = JacksonUtil.toObject(jsonCountry, Country.class);

        if (Objects.nonNull(jsonToCountry)) {
            logger.info(" ========== name:{},code:{} ========== \n", jsonToCountry.getName(), jsonToCountry.getCode());
        }

        // TODO 2 JSON字符串转换成List
        String jsonCountries = "[{\"name\":\"中国\",\"code\":\"CN\"},{\"name\":\"美国\",\"code\":\"USA\"}]";
        List<Country> countries = JacksonUtil.toList(jsonCountries, new TypeReference<List<Country>>() {
        });

        logger.info(" ========== countries[0]:{} ========== ", countries.get(0).toString());
        logger.info(" ========== countries[1]:{} ========== \n", countries.get(1).toString());

        // TODO 3 JavaBean转换成JSON字符串
        Country country = new Country();
        country.setCode("CN");
        country.setName("中国");

        String countryToJson = JacksonUtil.toJSon(country);
        logger.info(" ========== countryToJson:{} ========== ", countryToJson);

    }

}

