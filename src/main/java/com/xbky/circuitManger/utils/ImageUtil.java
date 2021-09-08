package com.xbky.circuitManger.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.awt.SunHints;

import javax.imageio.ImageIO;
import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.OrientationRequested;
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

    public static BufferedImage getImageUrl(String content) {
        BufferedImage image = new BufferedImage(500, 100, BufferedImage.TYPE_INT_RGB);
        Graphics2D title = image.createGraphics();
        //设置区域颜色
        title.setColor(new Color(255, 255, 255));
        //填充区域并确定区域大小位置
        title.fillRect(0, 0, 500, 100);
        //设置字体颜色，先设置颜色，再填充内容
        title.setColor(Color.black);
        //设置字体
        Font titleFont = new Font("仿宋", Font.BOLD, 32);
        title.setFont(titleFont);
        int x = 55;
        if(content.length() == 20) {
            x = 37;
        }
        title.setRenderingHint(SunHints.KEY_TEXT_ANTIALIASING, SunHints.VALUE_TEXT_ANTIALIAS_ON);
        title.setRenderingHint(SunHints.KEY_ANTIALIASING,SunHints.VALUE_ANTIALIAS_ON);
        title.drawString(content, x, 61);
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
        printToLabelV2("WX210907-234323 维修完毕");

    }

    public static void printToLabel(String content){
        String path = saveBmp(getImageUrl(content),"images/"+System.currentTimeMillis()+".png");
        qrCodePrint(path,590,840,590,840);
    }

    public static void printToLabelV2(String content) {
        String path = saveBmp(getImageUrl(content),"images/"+System.currentTimeMillis()+".png");
        drawImage(null,path,100,true);
    }

    public static void qrCodePrint(String path, int pageWidth, int pageHeight, int showWidth, int showHeight) {
        // 通俗理解就是书、文档
        Book book = new Book();
        // 设置成竖打
        PageFormat pf = new PageFormat();
        pf.setOrientation(PageFormat.PORTRAIT);
        // 通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。
        Paper p = new Paper();
        p.setSize(pageWidth,pageHeight);//纸张大小 A4(595x842)
        p.setImageableArea(0,0, pageWidth,pageHeight);//打印区域
        pf.setPaper(p);
        // 把 PageFormat 和 Printable 添加到书中，组成一个页面
        book.append((graphics, pageFormat, pageIndex) -> {//通过一个匿名内部内实现Printable接口，不懂的自行查看jdk8的新特性
            try {
                URL url = new URL(path);//也可以通过file构建一个本地图片File对象传递给ImageIO.read()方法
                Image image= null;
                image = ImageIO.read(url);
            //将图片绘制到graphics对象中（为什么把需要打印的内容drawImage就可以实现打印自己取看值传递一引用传递的区别）
            graphics.drawImage(image,0,0,showWidth,showHeight,null);
            } catch (IOException e) {
                logger.error("",e);
            }
            return 0;//返回0（PAGE_EXISTS）则执行打印，返回1（NO_SUCH_PAGE）则不执行打印
        }, pf);
        // 获取打印服务对象
        PrinterJob job = PrinterJob.getPrinterJob();
        // 设置打印类
        job.setPageable(book);
        try {
            //可以用printDialog显示打印对话框，在用户确认后打印；也可以直接打印
            boolean a=job.printDialog();
            if(a){
                job.print();
            }else{
                job.cancel();
            }
        } catch (PrinterException e) {
            logger.error("",e);
        }
    }

    public static void drawImage(String printerName,String fileName, int h, boolean aleartPrint){
        if ((fileName == null) || (fileName.trim() == "")) {
            throw new RuntimeException("文件名为空");

        }
        try{
            DocFlavor dof = null;

            if (fileName.endsWith(".gif")) {
                dof = DocFlavor.INPUT_STREAM.GIF;

            } else if (fileName.endsWith(".jpg")) {
                dof = DocFlavor.INPUT_STREAM.JPEG;

            } else if (fileName.endsWith(".png")) {
                dof = DocFlavor.INPUT_STREAM.PNG;

            }
            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            pras.add(OrientationRequested.PORTRAIT);
            pras.add(new Copies(1));
            DocAttributeSet das = new HashDocAttributeSet();
            das.add(new MediaPrintableArea(0, 0, 100, h, 1000));
            FileInputStream fin = new FileInputStream(fileName);
            Doc doc = new SimpleDoc(fin, dof, das);
            print(printerName, aleartPrint, dof, pras, doc);
            fin.close();

        }catch (IOException ie){
            ie.printStackTrace();

        }catch (PrintException pe){
            pe.printStackTrace();

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



