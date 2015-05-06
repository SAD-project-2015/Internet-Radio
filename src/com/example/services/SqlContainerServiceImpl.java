package com.example.services;

import com.vaadin.data.Container;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SqlContainerServiceImpl implements ContainerService{

    private final TableQuery usersTable;
//    private final TableQuery pcTable;
//    private final TableQuery commentsTable;
    
    
    
    public SqlContainerServiceImpl() throws SQLException{
        JDBCConnectionPool conn = new SimpleJDBCConnectionPool(
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/radiodb",
                "root",
                "123456"
        );
    
    this.usersTable = new TableQuery("users", conn);
//     this.pcTable = new TableQuery("pc", pool);
//     this.commentsTable = new TableQuery("comments", pool);
    
    }
    
    private SQLContainer constructContainer(TableQuery query){
        try{
        SQLContainer container = new SQLContainer(query);
        container.setAutoCommit(true);
        return container;
        }
    catch(SQLException e){
        e.printStackTrace();
        return null; 
    }
    
    
    }
    
    
    
    @Override
    public Container getUsersContainer() {
        try {
            return new SQLContainer(this.usersTable);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

//    @Override
//        public Container getPcContainer() {
//      // return null;
//    try{
//        SQLContainer container = new SQLContainer(this.pcTable);
//		container.addReference((SQLContainer) this.getUsersContainer(), "user_id", "pc_id");
//		return container;
//    }
//    catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    
//    }

//    @Override
//    public Container getCommentsConatainer() {
//       // return null;
//    
//        try {
//           SQLContainer container = new SQLContainer(this.commentsTable);
//        container.addReference((SQLContainer) this.getUsersContainer(), "user_id", "comment_id");
//		container.addReference((SQLContainer) this.getPcContainer(), "pc_id", "comment_id");
//		return container;
//        
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
		    
//    }

    
    
}
