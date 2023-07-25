package com.learn1.jwt1.config;

public class FilterNote {
    /*

        *) Servlet Filter are special web component of java web application that are capable of traping either
           all request-responses or multiple request_response to apply the common , additional and optional services
           without disturbing the other web component of the web application.

        *)  servlet filter trapping the request apply pre request processing logic.
        *) servlet filter trapping the response and applying post-response  generation logic.

   => Always to use filters to apply additional, common and optional pre Request processing logic
      and post-response generation logic by trapping the request-response
      == ==========================================================================================================
      3) 3 Type of filters are here
         1).request filter
         2). response filter
         3).request-response filter

         1). request filter
             => contains only pre request processing logic
                ex . Authentication filter
                      Authorization filter
                      requestDumber filter
                      browser checking filter
                      Treading checking filter

         2). response filter
             =>contains only post response processing logic
                 1).ImageFormat conversion filter
                 2). Locale change filter(language convert filter)
                 3).Signature adding filter

         3)request-response filter
           contains both pre-request processing and post requesting processing generation logic
            ex.  performance Test filter(trapes request gets start time-- trap response gets end time the difference between end  time start time  will be use to evaluate yhe performance )
=======================================================================================================
=> servlet filter or filters can be mapped 1 or more web component  of the web application
=> Every filer must be cfg linked with other web comp using @WebFilter(annotation driven cfg)
===============================================================================================
=> Every Filter must be implements Filter(Interface) javax.servlet.filter or jakarata.servlet.filter directly or indirectly
    This interface contains 3 default methods as life
      1) public void init(FilterConfig)throws SE for filter's instantiation event
      2) public void doFilter(request,response,FilterChain chain )throws SE,IoE  for when filter traps request,response
      3) public void destroy(request,response,FilterChain chain )throws SE,IoE  for when filter's Destruction event raises




     */
}
