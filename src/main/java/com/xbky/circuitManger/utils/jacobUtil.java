package com.xbky.circuitManger.utils;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class jacobUtil {
    static void testMethod(String content,String path){

        ActiveXComponent activeDm = new ActiveXComponent("bpac.Document");
        boolean result = activeDm.invoke("Open", path).getBoolean();
        if (result) {
            activeDm.invoke("Open", path);
//            Variant[] vars = new Variant[]{new Variant("Brother PT-2430PC"),new Variant(false)};
//            activeDm.invoke("setPrinter",vars);
            Dispatch wxId = activeDm.invoke("GetObject","wxId").toDispatch();
            Dispatch.put(wxId,"Text",content);
//            Variant[] vars0 = new Variant[]{new Variant(""),new Variant(0)};
//            boolean bool0 = activeDm.invoke("StartPrint",vars0).getBoolean();
//            System.out.println(bool0);
//            Variant[] vars1 = new Variant[]{new Variant(10),new Variant(800)};
//            boolean bool = activeDm.invoke("PrintOut",vars1).getBoolean();
//            System.out.println(bool);
//            boolean bool2 = activeDm.invoke("EndPrint").getBoolean();
//            System.out.println(bool2);
            Variant[] vars7 = new Variant[]{new Variant("0"),new Variant("hello,1")};
            Boolean bool7 = activeDm.invoke("DoPrint",vars7).getBoolean();
            System.out.println(bool7);
            boolean bool3 =  activeDm.invoke("Close").getBoolean();
            System.out.println(bool3);
            String printerName = activeDm.invoke("GetMediaName").getString();
            System.out.println(printerName);
        }
    }

    public static void main(String[] args) {
        testMethod("WX210907-88888 维修完成","D:\\Program Files\\Brother bPAC3 SDK\\Templates\\wx2.lbx");
    }
}
