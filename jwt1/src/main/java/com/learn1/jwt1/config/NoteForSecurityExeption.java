package com.learn1.jwt1.config;

public class NoteForSecurityExeption {


    /*
********************************************************************************************************
                          Spring Security Exception
********************************************************************************************************
    *) Authentication Exception
       - UsernameNotFoundException - if user not in server
       - BadCreadntailsException
       - AccountStatusException
       - Invalid barrier token - if barrier token is wrong
    *)  Authorization Exception
          - AccessDeniedException -if user have no access to handle to this api

         we have to use HandlerExceptionResolver to handle the exception because if we will not use we do not get the proper exception .


     */
}
