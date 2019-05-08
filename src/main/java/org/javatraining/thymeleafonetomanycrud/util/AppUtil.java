package org.javatraining.thymeleafonetomanycrud.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class AppUtil {
    @Autowired
    private Environment env;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    public void doFillDatabase() {
        Map<String, String> map = getSqlScriptNames();
        String queryString = getResourceFileAsString(map.get("SCHEME")) + "\n" +
                getResourceFileAsString(map.get("DATA"));

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createNativeQuery(queryString);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    private Map<String, String> getSqlScriptNames() {
        String SQL_FILE_FOR_H2_SCHEME = "/sql/schemeH2.sql";
        String SQL_FILE_FOR_POSTGRES_SCHEME = "/sql/schemePostgreSql.sql";
        String SQL_FILE_DATA = "/sql/data.sql";

        Map<String, String> result = new HashMap<>();
        switch (env.getProperty("spring.profiles.active")) {
            case "h2db":
                result.put("SCHEME", SQL_FILE_FOR_H2_SCHEME);
                break;
            case "postgres-local":
            case "postgres-heroku":
                result.put("SCHEME", SQL_FILE_FOR_POSTGRES_SCHEME);
                break;
            default:
                throw new RuntimeException("Not found appropriate profile");
        }
        result.put("DATA", SQL_FILE_DATA);
        return result;
    }

    private String getResourceFileAsString(String fileName) {
        InputStream inputStream = this.getClass().
                getResourceAsStream(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,
                    StandardCharsets.UTF_8));
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }


}
