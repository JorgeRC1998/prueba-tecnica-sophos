package com.sophos.backendcanvas.Servicios;

import com.sophos.backendcanvas.Dao.AutenticacionDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService {
    
    @Autowired
    AutenticacionDao autenticacionDao;
}
