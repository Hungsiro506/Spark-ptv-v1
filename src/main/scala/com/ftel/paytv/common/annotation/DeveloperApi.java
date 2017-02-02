package com.ftel.paytv.common.annotation;
import java.lang.annotation.*;
/**
 * Created by vdh on 25/01/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD,
        ElementType.PARAMETER, ElementType.CONSTRUCTOR, ElementType.LOCAL_VARIABLE,
        ElementType.PACKAGE})
public @interface DeveloperApi {

}
