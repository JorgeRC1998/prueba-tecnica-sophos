package com.sophos.backendcanvas.Controladores;

import com.sophos.backendcanvas.Servicios.AutenticacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/auth")
public class AutenticacionContoller {
    
    @Autowired
    AutenticacionService autenticacionService;
}
