package com.rodrigo.ProdManager.resources.utils;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class URL {

    public static String decodeString(String s){
        return URLDecoder.decode(s, StandardCharsets.UTF_8);
    }
    public static List<Long> decodeUrl(String s){
        String[] numeros = s.split(",");
        List<Long> numerosLista = new ArrayList<>();
        for (int x = 0; x < numeros.length; x++ ){
            var num = Long.parseLong(numeros[x]);
            numerosLista.add(num);
        }
        return numerosLista;
    }
}
