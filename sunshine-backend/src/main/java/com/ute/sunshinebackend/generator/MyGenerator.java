package com.ute.sunshinebackend.generator;

import com.ute.sunshinebackend.repository.Contribution.ContributionRepository;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.*;
import java.util.Properties;
import java.util.stream.Stream;
import java.io.Serializable;

public class MyGenerator implements IdentifierGenerator {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        Connection connection = session.connection();
        String query = "SELECT dbo.AUTO_IDC()";

        try (
                CallableStatement cstm = connection.prepareCall(query);
        ) {
            ResultSet rs = cstm.executeQuery();
            if (rs.next()){
                String generateContributionId = rs.getString(1);
                System.out.println("id generateee: " + generateContributionId);
                return generateContributionId;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

}
