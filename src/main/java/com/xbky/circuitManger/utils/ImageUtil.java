package com.xbky.circuitManger.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.awt.SunHints;
import sun.font.FontDesignMetrics;

import javax.imageio.ImageIO;
import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.*;
import javax.print.event.PrintJobEvent;
import javax.print.event.PrintJobListener;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.print.*;
import java.io.*;
import java.net.URL;

public class ImageUtil {
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    private static int img_wight = 320;
    private static int img_height = 64;

    public static BufferedImage getImageUrl(String content) {
        BufferedImage image = new BufferedImage(img_wight, img_height, BufferedImage.TYPE_INT_RGB);
        Graphics2D title = image.createGraphics();
        //设置区域颜色
        title.setColor(new Color(255, 255, 255));
        //填充区域并确定区域大小位置
        title.fillRect(0, 0, img_wight, img_height);
        //设置字体颜色，先设置颜色，再填充内容
        title.setColor(Color.black);
        //设置字体
        Font titleFont = new Font("微软雅黑", Font.BOLD, 22);
        title.setFont(titleFont);

        title.setRenderingHint(SunHints.KEY_TEXT_ANTIALIASING, SunHints.VALUE_TEXT_ANTIALIAS_ON);
        title.setRenderingHint(SunHints.KEY_ANTIALIASING,SunHints.VALUE_ANTIALIAS_ON);
        FontDesignMetrics metrics = FontDesignMetrics.getMetrics(titleFont);
        int x = (img_wight-metrics.stringWidth(content))/2;
        int y = (img_height-metrics.getHeight())/2+metrics.getAscent();
        title.drawString(content, x, y);
        return image;
    }

    // /image/image
    private static String saveBmp( final BufferedImage bi, final String path ) {
        try {
            File rootFile = new File("images/");
            if(!rootFile.exists()){
                rootFile.mkdir();
            }
           RenderedImage rendImage = bi;
            File file = new File(path);
            ImageIO.write(rendImage, "bmp", new File(path));
            return file.getAbsolutePath();
        } catch (IOException e) {
            logger.error("",e);

        }
        return "";
    }

    public static void main(String[] args) throws IOException {
//        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        String[] fontName = e.getAvailableFontFamilyNames();
//        for (int i = 0; i < fontName.length; i++) {
//            System.out.println(fontName[i]);
//        }
//        BufferedImage image = ImageUtil.getImageUrl("WX210907-234323 维修完毕");
//        String path = "D:\\workspace\\2.png";
//        saveBmp(image,path);
/*        BufferedImage image = ImageUtil.getImageUrl("WX210907-234323 维修完毕");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "png", os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        printQRCode(is);*/
        printToLabel("WX210907-23432 维修完成");

    }


    public static void printToLabel(String content) {
        String path = saveBmp(getImageUrl(content),"images/"+System.currentTimeMillis()+".png");
        //Brother PT-2430PC
        drawImage("Brother PT-2430PC",path,false);
    }


    public static void drawImage(String printerName,String fileName, boolean aleartPrint){
        if ((fileName == null) || (fileName.trim() == "")) {
            throw new RuntimeException("文件名为空");

        }
        try {
            DocFlavor dof = null;

            if (fileName.endsWith(".gif")) {
                dof = DocFlavor.INPUT_STREAM.GIF;

            } else if (fileName.endsWith(".jpg")) {
                dof = DocFlavor.INPUT_STREAM.JPEG;

            } else if (fileName.endsWith(".png")) {
                dof = DocFlavor.INPUT_STREAM.PNG;

            } else if (fileName.endsWith(".bmp")) {
                dof = new DocFlavor.INPUT_STREAM("image/bmp");
            }
            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            pras.add(OrientationRequested.LANDSCAPE);
            pras.add(new Copies(1));
            DocAttributeSet das = new HashDocAttributeSet();
            MediaPrintableArea mp = new MediaPrintableArea(0, 0, 320, 64, MediaPrintableArea.MM);
            das.add(mp);
            System.out.println(mp.getWidth(Size2DSyntax.MM) + " " + mp.getHeight(Size2DSyntax.MM));
            MediaSizeExp ms = new MediaSizeExp(45, 9, Size2DSyntax.MM);// 45,9
            pras.add(ms);
            FileInputStream fin = new FileInputStream(fileName);
            Doc doc = new SimpleDoc(fin, dof, das);
            print(printerName, aleartPrint, dof, pras, doc);
            fin.close();

        } catch (IOException ie) {
            ie.printStackTrace();

        } catch (PrintException pe) {
            pe.printStackTrace();

        } catch (Exception e) {
            logger.error("", e);
        }

    }
    private static void print(String printerName, boolean aleartPrint, DocFlavor dof, PrintRequestAttributeSet pras, Doc doc) throws PrintException{
        //PrintService service = PrintServiceLookup.lookupDefaultPrintService();//默认打印机
        // 定位打印服务
        PrintService service = null;
        if (printerName != null) {
            //获得本台电脑连接的所有打印机
            PrintService[] printServices = PrinterJob.lookupPrintServices();
            if(printServices == null || printServices.length == 0) {
                System.out.print("打印失败，未找到可用打印机，请检查。");
                return ;
            }
            //匹配指定打印机
            for (int i = 0;i < printServices.length; i++) {
                if (printServices[i].getName().contains(printerName)) {
                    service = printServices[i];
                    break;
                }
            }
            if(service==null){
                System.out.print("打印失败，未找到名称为" + printerName + "的打印机，请检查。");
                return ;
            }
        }
        if (aleartPrint){
            PrintService[] printServices = PrintServiceLookup.lookupPrintServices(dof, pras);
            service = ServiceUI.printDialog(null, 400, 400, printServices, service, dof, pras);
        }
        if (service != null){
            DocPrintJob job = service.createPrintJob();
            job.addPrintJobListener(new PrintJobListener(){
                @Override
                public void printJobRequiresAttention(PrintJobEvent arg0)
                {
                    System.out.println("printJobRequiresAttention");
                }
                @Override
                public void printJobNoMoreEvents(PrintJobEvent arg0)
                {
                    System.out.println("打印机已接收");
                }
                @Override
                public void printJobFailed(PrintJobEvent arg0)
                {
                    System.out.println("打印机无法完成作业,必须重新提交");
                }
                @Override
                public void printJobCompleted(PrintJobEvent arg0)
                {
                    System.out.println("打印结束");
                }
                @Override
                public void printJobCanceled(PrintJobEvent arg0)
                {
                    System.out.println("作业已被用户或者程序取消");
                }
                @Override
                public void printDataTransferCompleted(PrintJobEvent arg0)
                {
                    System.out.println("数据已成功传输打印机");
                }
            });
            try{
                job.print(doc, pras);
            }catch (PrintException pe){
                pe.printStackTrace();
            }
        }else{
            if (aleartPrint) {
                throw new RuntimeException("打印机未连接,请选择打印机");
            }
            throw new RuntimeException("请设置默认打印机");
        }
    }

}



