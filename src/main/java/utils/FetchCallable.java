/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.concurrent.Callable;

/**
 *
 * @author peter
 */
public class FetchCallable implements Callable<String>{
    
    private String url;
    
    public FetchCallable(String url) {
        this.url = url;
    }

    @Override
    public String call() throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        String str = HttpUtil.fetchData(url);
        return str;
        
    }
    
    
    
}
