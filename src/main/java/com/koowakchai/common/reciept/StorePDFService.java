package com.koowakchai.common.reciept;

import cn.hutool.core.date.DateUtil;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.koowakchai.hibernate.entity.TTotalOrderEntity;
import com.koowakchai.store.dao.TLogisticsOrderDao;
import com.koowakchai.user.dao.TAddressBookDao;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class StorePDFService {

    @Autowired
    private TAddressBookDao tAddressBookDao;

    @Autowired
    private TLogisticsOrderDao tLogisticsOrderDao;

    public synchronized String createPdf(List<TTotalOrderEntity> tTotalOrderEntityList, OutputStream outputStream) throws Exception{

        String randomKey = RandomStringUtils.randomAlphanumeric(8).toUpperCase();
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter writer=null;
        if(outputStream==null){
            String os = System.getProperty("os.name");
            //PDF保存路径   //windows下的路径
            if(os.toLowerCase().startsWith("win")){
                writer = PdfWriter.getInstance(document,new FileOutputStream("d:Koowakchai_Store.pdf"));
            }else {
                //linux下
                File dir = new File("~/Documents/Koowakchai_Customer/Receipt");
                if(!dir.exists()){
                    //创建目录
                    dir.mkdirs();
                }
                writer = PdfWriter.getInstance(document,new FileOutputStream("/Users/oliverxu/Documents/Koowakchai_Customer/Receipt/Koowakchai_Store_" + randomKey + ".pdf"));
            }
        }else{
            //使用流下载pdf
            writer  = PdfWriter.getInstance(document,outputStream);
        }
        //设置pdf加密，只读
        writer.setEncryption(null, null, PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);
        //设置作者等信息
        document.addAuthor("Zhe Xu");
        document.addCreator("Koowakchai");
        document.addCreationDate();
        document.addKeywords("Store Receipt");
        document.addSubject("Receipt");
        document.addTitle("Koowakchai Store Confirmation Receipt");

        document.open();
        //中文字体,解决中文不能显示问题
        BaseFont bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
        Font textFont = new Font(bfChinese,11,Font.NORMAL); //正常
        Font blackFont = new Font(bfChinese,15,Font.BOLD);//标题字体
        blackFont.setColor(BaseColor.BLACK);

        //标题
        Paragraph p= new Paragraph("Your Koowakchai Store Confirmation Receipt", blackFont);
        p.setAlignment(Element.ALIGN_CENTER);
        document.add(p);

        //证书时间
        PdfPTable head = new PdfPTable(1);
        head.setTotalWidth(new float[]{520}); //设置列宽
        head.setLockedWidth(true); //锁定列宽
        head.setSpacingBefore(10f); // 前间距
        head.setSpacingAfter(10f); // 后间距
        PdfPCell cell1 = new PdfPCell(new Phrase( DateUtil.now(), textFont));
        cell1.setBorderWidth(0);
        cell1.setMinimumHeight(20); //设置单元格高度
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER); //设置水平居中
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE); //设置垂直居中
        head.addCell(cell1);
        document.add(head);


        PdfPTable table = new PdfPTable(2);

        int pageCnt = 0;

        for (TTotalOrderEntity tTotalOrderEntity : tTotalOrderEntityList){

            maneuverPDFTable(tTotalOrderEntity, table,textFont, document);

            pageCnt++;
            if (pageCnt != tTotalOrderEntityList.size()){
                document.newPage();
                table = new PdfPTable(2);
            }
        }
        document.close();
        writer.close();

        return "/Users/oliverxu/Documents/Koowakchai_Customer/Receipt/Koowakchai_Store_" + randomKey + ".pdf";
    }


    private void maneuverPDFTable(TTotalOrderEntity tTotalOrderEntity, PdfPTable table, Font textFont,Document document) throws Exception{
        // 数据表格
        table = new PdfPTable(2);
        table.setWidthPercentage(100); // 宽度100%填充
        //每一行列表
        ArrayList<PdfPRow> listRow = table.getRows();
        //设置列宽
        float[] columnWidths = { 1f,4f};
        table.setWidths(columnWidths);

        //第1行 Order Id
        addRowContent("Order ID", Long.toString(tTotalOrderEntity.getId()), textFont, listRow);

        //第2行 Paid Time
        addRowContent("Order Date", tTotalOrderEntity.getPaidDatetime().toString(), textFont, listRow);

        //第3行 Customer name
        PdfPCell cells3[]= new PdfPCell[2];
        PdfPRow row3 = new PdfPRow(cells3);

        cells3[0] = new PdfPCell(new Paragraph("Paying Customer",textFont));//单元格内容
        cells3[0].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        cells3[0].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中

        String customerName = null;
        customerName = tTotalOrderEntity.getCustomerName();
        //        Integer userid = donation.getUserid();
        if(customerName==null) {
            cells3[1] = new PdfPCell(new Paragraph("Anonymous",textFont));
        }else {
            cells3[1] = new PdfPCell(new Paragraph(customerName,textFont));
        }
        cells3[1].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        cells3[1].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        listRow.add(row3);

        //第4行 Email
        addRowContent("Customer Email", tTotalOrderEntity.getCustomerEmail(), textFont, listRow);

        //第5行
        addRowContent("Product", tTotalOrderEntity.getProductName(), textFont, listRow);

        //第6行 Quantity
        addRowContent("Quantity", Integer.toString(tTotalOrderEntity.getQuantity()), textFont, listRow);

//            Integer type = donation.getType();
//            if(type.equals(PayTypeConstants.WEI_XIN)) {
//                cells4[1] = new PdfPCell(new Paragraph("微信支付",textFont));
//            }else if(type.equals(PayTypeConstants.XIAN_JIN)){
//                cells4[1] = new PdfPCell(new Paragraph("现金支付",textFont));
//            }else if(type.equals(PayTypeConstants.YING_HANG_KA)) {
//                cells4[1] = new PdfPCell(new Paragraph("银行卡支付",textFont));
//            }else if(type.equals(PayTypeConstants.ZHI_FU_BAO)) {
//                cells4[1] = new PdfPCell(new Paragraph("支付宝支付",textFont));
//            }else {
//                cells4[1] = new PdfPCell(new Paragraph("其他方式",textFont));
//            }

        //第7行 Total Price
        addRowContent("Total Price", Double.toString(tTotalOrderEntity.getPricePaid()), textFont, listRow);

        //第8行 Recipient
        addRowContent("Recipient", tTotalOrderEntity.getRecipientName(), textFont, listRow);


        //第9行 Recipient Address
        addRowContent("Recipient Address", tTotalOrderEntity.getShippingAddr(), textFont, listRow);


        //第10行 Recipient Phone Number
        String phoneNum = (tAddressBookDao.getTAddressBookEntity(tTotalOrderEntity.getAddrId())).getPhoneNum();
        addRowContent("Recipient Phone Numer", phoneNum, textFont, listRow);


        //第11行 Order Status
        addRowContent("Order Status", tTotalOrderEntity.getStatus().toUpperCase(), textFont, listRow);

        //Optional
        if (tTotalOrderEntity.getStatus().toUpperCase().equals("SHIPPED")){
            String logisticsCompany =tLogisticsOrderDao.getTLogisticsOrderEntity(tTotalOrderEntity.getTrackingNumber()).getLogisticsCompany();
            long staffPhone = tLogisticsOrderDao.getTLogisticsOrderEntity(tTotalOrderEntity.getTrackingNumber()).getStaffPhone();
            addRowContent("Logistics Company", logisticsCompany, textFont, listRow);
            addRowContent("Tracking Number", tTotalOrderEntity.getTrackingNumber(), textFont, listRow);
            addRowContent("Staff Phone", Long.toString(staffPhone), textFont, listRow);
        }

        //第12行 Customer Remark
        addRowContent("Customer Remark", tTotalOrderEntity.getRemark(), textFont, listRow);

        //把表格添加到文件中
        document.add(table);
    }

    private void addRowContent(String rowName, String rowData, Font textFont, ArrayList<PdfPRow> listRow) throws Exception {
        PdfPCell cells1[]= new PdfPCell[2];
        PdfPRow row1 = new PdfPRow(cells1);
        cells1[0] = new PdfPCell(new Paragraph(rowName,textFont));
        cells1[0].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        cells1[0].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中

        cells1[1] = new PdfPCell(new Paragraph(rowData, textFont));
        cells1[1].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        cells1[1].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        listRow.add(row1);

    }

}
