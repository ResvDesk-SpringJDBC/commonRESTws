package com.telappoint.commonrestws.wadl.utils;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ParamStyle.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ParamStyle">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="plain"/>
 *     &lt;enumeration value="query"/>
 *     &lt;enumeration value="matrix"/>
 *     &lt;enumeration value="header"/>
 *     &lt;enumeration value="template"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ParamStyle")
@XmlEnum
public enum WadlParamStyle {

    @XmlEnumValue("plain")
    PLAIN("plain"),
    @XmlEnumValue("query")
    QUERY("query"),
    @XmlEnumValue("matrix")
    MATRIX("matrix"),
    @XmlEnumValue("header")
    HEADER("header"),
    @XmlEnumValue("template")
    TEMPLATE("template");
    private final String value;

    WadlParamStyle(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static WadlParamStyle fromValue(String v) {
        for (WadlParamStyle c: WadlParamStyle.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
