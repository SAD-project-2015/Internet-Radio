package com.example.services;

import com.example.services.SqlContainerServiceImpl;


public class ServiceProvider {

    private ServiceProvider(){
        ;
    
    }
    
    public ContainerService getContainerService(){
        try {
            return new SqlContainerServiceImpl();
        }
        catch(Exception e){
            throw new RuntimeException(e);
        
        }
    }
    private final static ServiceProvider INSTANCE = new ServiceProvider();
    
    public static final ServiceProvider getInstance(){
    
        return INSTANCE;
    
    }
    
}
