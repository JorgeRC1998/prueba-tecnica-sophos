package com.sophos.backendcanvas.Util;

public class ConstantesSwagger {
    
    private ConstantesSwagger(){

    }

    public static final String LOGIN_OK = "{'codigo':'1','mensaje':'Exito','token':'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c3VhcmlvMSIsImlhdCI6MTY0Mjk3NTAzMSwiaWRlbnRpZmljYWNpb24iOiI5OTk5OTkiLCJleHAiOjE2NDMwMTEwMzF9.AD5lFVj5kJCS9ikVMjmEBuHx-HlkmD6OGrLut7UObhITEDfE5VpoNlBujX5nP19356HX1P3S60iDwjc_hnTM-g'}";
    public static final String LOGIN_NOK = "{'descripcion':'Usuario o password no validos','codigo':'0','mensaje':'Error'}";
    public static final String OPERACION_OK = "{'descripcion':'Operacion se completo con exito','codigo':'1','mensaje':'Exito'}";
    public static final String GUARDAR_TAREA_NOK = "{'codigo':'0','mensaje':'Error','errores':['La longitud del campo descripcion debe ser inferior a 50 caracteres']}";
    public static final String CONSULTA_TAREA_OK = "{'codigo':'1','mensaje':'Exito','tareas':[{'id':1,'titulo':'desarrollo hu2','descripcion':'desarrollo de pantalla para home de la aplicacion','estado':'pendiente','idUsuario':null}]}";
    public static final String CONSULTA_TAREA_NOK = "{'codigo':'0','mensaje':'Error','errores':['El estado ingresado no corresponde a pendiente, ejecutando, finalizado']}";
    public static final String ACTUALIZAR_TAREA_NOK = "{'descripcion':'Tarea con titulo o Id: 2 no se encuentra registrada en el sistema','codigo':'0','mensaje':'Error'}";
    public static final String ASIGNAR_TAREA_NOK = "{'descripcion':'Usuario con Id: 2 no se encuentra registrado en el sistema','codigo':'0','mensaje':'Error'}";
    public static final String LIBERAR_TAREA_NOK = "{'descripcion':'No puede liberar la tarea: 1 ya que actualmente no se encuentra asignada a ningun usuario','codigo':'0','mensaje':'Error'}";
    public static final String ELIMINAR_TAREA_NOK = "{'descripcion':'Tarea con titulo o Id: 2 no se encuentra registrada en el sistema','codigo':'0','mensaje':'Error'}";
    public static final String INSERTAR_USUARIO_NOK = "{'descripcion':'Usuario: Pepito Perez ya está registrado en el sistema','codigo':'0','mensaje':'Error'}";
    public static final String CONSULTA_USUARIO_OK = "{'codigo':'1','mensaje':'Exito','usuarios':[{'id':1,'nombre':'usuario1','identificacion':'999999','tipoUsuario':'administrador','usuario':'usuario2022','password':'$2a$12$EPYwTGE9pfGQYvNcZpMFYOFMXUtiRlmeLdouSWu.eKVvG/Xqx/hiG'}]}";
    public static final String CONSULTA_USUARIO_NOK = "{'codigo':'0','mensaje':'Error','errores':['El tipo de usuario ingresado no corresponde a administrador, miembro, invitado']}";
    public static final String ACTUALIZAR_USUARIO_NOK = "{'descripcion':'Usuario con Id: 3 no se encuentra registrado en el sistema','codigo':'0','mensaje':'Error'}";
    public static final String ELIMINAR_USUARIO_NOK = "{'descripcion':'Usuario con Id: 1 no se encuentra registrado en el sistema','codigo':'0','mensaje':'Error'}";
}
