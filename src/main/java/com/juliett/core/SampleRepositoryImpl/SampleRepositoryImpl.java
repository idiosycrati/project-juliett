package com.juliett.core.SampleRepositoryImpl;

import com.juliett.core.Sample.model.SampleModel;
import com.juliett.core.SampleRepository.SampleRepository;
import com.xurpas.development.core.db.DatabaseManager;
import com.xurpas.development.core.exception.XDevServiceException;
import com.xurpas.development.core.repository.impl.AbstractRepositoryImpl;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import static java.util.Objects.nonNull;

public class SampleRepositoryImpl extends AbstractRepositoryImpl<SampleModel> implements SampleRepository {
	private String tableName;

    /**
     * @param databaseManager
     */
    public SampleRepositoryImpl(DatabaseManager databaseManager) {
        super(SampleModel.class, databaseManager);
        this.tableName = "sample_table";

    }
    
    @Override
    public Collection<SampleModel> list() throws ClassNotFoundException, SQLException, NamingException {
    	// TODO Auto-generated method stub
    	
    	 Connection connection = null;
         PreparedStatement statement = null;
         List<SampleModel> items = null;
         ResultSet resultSet = null;

         StringBuilder sql = new StringBuilder("select * from "+this.tableName);
     

         try {

             connection = getConnection();
             statement  = connection.prepareStatement(sql.toString());
             sql(statement.toString());

             resultSet = statement.executeQuery();

             if (resultSet != null) {
                 items = new ArrayList<>();
                 while (resultSet.next()) {
                	 SampleModel sampleModel = new SampleModel(resultSet.getLong("id"));
                	 sampleModel.setDescription(resultSet.getString("description"));
                     items.add(sampleModel);

                 }

             }

         } catch (Exception e) {
             error(e.getMessage());
         } finally {
             close(connection, statement);
         }

         return items;
    
    }
   
}
